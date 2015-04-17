package com.szoftlab4.java9esharomnegyed;

import com.szoftlab4.java9esharomnegyed.Utility.LogHelper;
import sun.rmi.runtime.Log;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


// TODO: more comments. Soul.
public class Arena {
    //Arena osztály mezői
    private Dimension size;             // Arena size
    private List<Obstacle> obstacles;   // a List where the arena register all the placed Obstacle
    private List<Wall> walls;
    private List<Robot> robots;
    private List<CleanerRobot> cleaners;
    ArrayList<String> map;

    //Üres konstruktor az elemek megfelelő inicializálásához
    public Arena(){
        robots = new ArrayList<Robot>();
        cleaners = new ArrayList<CleanerRobot>();
        obstacles = new ArrayList<Obstacle>();
        walls = new ArrayList<Wall>();
    }

    // feladata a már beolvasott map-ből elhelyezni a szükséges robotokat akadályokat
    public void initArena(){
        int startX = Config.TILE_SIZE / 2;
        int startY = Config.TILE_SIZE / 2;
        int tile = Config.TILE_SIZE;
        Dimension place;
        char element;

        if(this.map != null){
            for(int i = 0; i < this.size.height; i++)
                for(int j = 0; j < this.size.width; j++){
                    element = this.map.get(i).charAt(j);
                    place = new Dimension(startX + (j * tile), startY + (i * tile));

                    switch (element) {
                        case 'w': walls.add(new Wall(place));
                            break;
                        case 'o': obstacles.add(new OilSpot(place));
                            break;
                        case 'p': obstacles.add(new PuttySpot(place));
                            break;
                        case '_': break;
                        default: break;
                    }

                    if(element >= '0' && element <= '9'){
                        addRobot("player"+element, place, Config.DIR_UP, Integer.valueOf(element));
                    }
                }
            LogHelper.inline("arenaInited");
        }
    }

    // feladata beolvasni az arénát tartalmazó txt-t majd visszaadni a beolvasott sortömbböt
    public void loadMap(String fileName) {
        ArrayList<String> temp = new ArrayList<String>();
        String line = null;

        FileReader fr = null;
        BufferedReader br = null;

        try {
            fr = new FileReader(Config.ARENA_FOLDER + fileName);
            br = new BufferedReader(fr);

            while ((line = br.readLine()) != null)
                temp.add(new String(line.toLowerCase()));

            if (br != null) br.close();
            if (fr != null) fr.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.map = temp;
        LogHelper.inline("arenaLoaded src: " + fileName);

        if(this.map != null)
            this.size = new Dimension(this.map.get(0).length(), this.map.size());
        else
            this.size = new Dimension(Config.DEF_MAPSIZE, Config.DEF_MAPSIZE);
    }

    //Robot nevét beállító fgv
    public void setRobotName(String name, int player){
        robots.get(player).setName(name);
    }

    //Egy adott pozíció alapján esetleges ott lévő akadály elkérése
    public Obstacle getObstacle(Dimension dest) {
        for(int i = 0; i < obstacles.size(); i++){
            if(obstacles.get(i).getPosition().width == dest.width
                    && obstacles.get(i).getPosition().height == dest.height) {
                return obstacles.get(i);
            }
        }
        return null;
    }

    //Egy adott pozíció alapján esetleges itt lévő fal elkérése
    public Wall getWall(Dimension dest){
        for(int i = 0; i < walls.size(); i++){
            if(walls.get(i).getPosition().width == dest.width
                    && walls.get(i).getPosition().height == dest.height) {
                return walls.get(i);
            }
        }
        return null;
    }

    //cleaner robot ezt hívja meg az akadály eltüntetésére
    public void removeObstacle(Obstacle obstacle){
        obstacles.remove(obstacle);
    }

    //Akadály felvétele a listába
    public void addObstacle(Obstacle o) {
        obstacles.add(o);
    }

    public void addCleanerRobot(Dimension pos, int dir, int id){
        CleanerRobot roboC = new CleanerRobot(this, pos, dir, id);
        cleaners.add(roboC);
    }

    public CleanerRobot getCleanerRobot(int id){
        return cleaners.get(id);
    }

    public void addRobot(String name, Dimension pos, int dir, int id){
        if(robots.size() < 2) {
            Robot robo = new Robot(this, name, pos, dir, id);
            //TODO: LogHelper.kimenet. Soul.
            robots.add(robo);
        } else {
            LogHelper.error("There isn't room for another robot!");
        }
    }

    //Robot objektum visszaadása
    public Robot getRobot(int id) {
        Robot temp = null;
        for(int i = 0; i < robots.size(); i++)
            if(robots.get(i).getID() == id)
                temp = robots.get(i);
        if(temp == null)
            LogHelper.error("There is no such Robot with id: " + id + "!");
        else
            ;//TODO: LogHelper. kimenet. Soul
        return temp;
    }

    public List<Robot> getRobotList(){
        return robots;
    }

    //Ütközésdetekció
    public Dimension collision(Robot r, Dimension d) {
        Obstacle w = null;
        Dimension temp;
        int dir = r.getDirection();
        for (temp = r.getPosition() ; w == null && temp != d;) {
            w = getWall(temp);
            if(dir % 2 == 0) {
                dir = (dir - 1) * (-1);                 // 0: (0 - 1) = -1; (-1) * (-1) = +1;  => X+
                temp.width += dir * Config.TILE_SIZE;   // 2: (2 - 1) = +1;   1  * (-1) = -1;  => X-
            } else {
                dir = (dir - 2) * (-1);                 // 1: (1 - 2) = -1; (-1) * (-1) = +1;  => Y+
                temp.height += dir * Config.TILE_SIZE;  // 3: (3 - 2) = +1;   1  * (-1) = -1;  => Y-
            }
        }

        if (w != null)
            w.collide(r);
        else
            r.setPosition(d);
        return r.getPosition();
    }

    //Pályáról kilépés érzékelése
    public boolean isOutOfArena(Dimension d) {
        boolean rBool = (d.width < 0 || d.width > size.width) || (d.height < 0 || d.height > size.height);
        // akkor van pályán kívül ha bármelyik koordináta nagyobb mint a pálya méretében vagy kissebb mint nulla
        return rBool;
    }

    //Effekt érvényesítése egy adott roboton egy adott pozícióban
    public void takeEffect(Robot r, Dimension dest) {
        Dimension fin = collision(r, dest);     // először megnézi falbe ütközött-e
        if(isOutOfArena(fin)) {                 // fin a végső pozíció ahova ugrottunk
            r.die();                            // ha pályán kívül van akkor kinyírjuk a robotot
            if(robots.size() == 1)
                Game.gameOver();
        } else {
            Obstacle on = getObstacle(fin);
            if(on != null)
                on.effect(r);
            else
                r.clearEffects();               // ha nem lép semmire töröljük az eddigi hatásokat
        }
    }

    //Irányításkezelő fgv
    public void movementControl(int e) {
        //Key event lesz majd az 'int e'-ből
        //int key = e.getKeyCode()
        int key = e;
        //A két robot mozgását külön gombokkal kezeljük, ezek megnyomása közvetlen a robot
        //megfelelő fgvének meghívását vonja maga után
        switch (key) {
            case Config.MOV_P1_UP:
                robots.get(0).speedUp();
                break;
            case Config.MOV_P1_DOWN:
                robots.get(0).slowDown();
                break;
            case Config.MOV_P1_LEFT:
                robots.get(0).turnLeft();
                break;
            case Config.MOV_P1_RIGHT:
                robots.get(0).turnRight();
                break;
            case Config.MOV_P1_OIL:
                robots.get(0).dropOil();
                break;
            case Config.MOV_P1_PUTTY:
                robots.get(0).dropPutty();
                break;
            case Config.MOV_P2_UP:
                robots.get(1).speedUp();
                break;
            case Config.MOV_P2_DOWN:
                robots.get(1).slowDown();
                break;
            case Config.MOV_P2_LEFT:
                robots.get(1).turnLeft();
                break;
            case Config.MOV_P2_RIGHT:
                robots.get(1).turnRight();
                break;
            case Config.MOV_P2_OIL:
                robots.get(1).dropOil();
                break;
            case Config.MOV_P2_PUTTY:
                robots.get(1).dropPutty();
                break;
        }
    }

    public void tick() {
        for (int j = 0; j < robots.size(); j++) {
            robots.get(j).move();
        }
        for (int j = 0; j < cleaners.size(); j++) {
            cleaners.get(j).move();
        }
        for (int j = 0; j < obstacles.size(); j++) {
            obstacles.get(j).age();
        }

    }

    public List<Obstacle> getObstacles() {
        return obstacles;
    }

}
