package com.szoftlab4.java9esharomnegyed;

import com.szoftlab4.java9esharomnegyed.Utility.LogHelper;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;



public class Arena {
    //Arena osztály mezői
    private Dimension size;             // Arena size
    private List<Obstacle> obstacles;   // a List where the arena register all the placed Obstacle
    private List<Wall> walls;           // a List where the arena register all the walls from init
    private List<Robot> robots;         // Playable robot list
    private List<CleanerRobot> cleaners;    // cleaner robot list
    private ArrayList<String> map;              // temporary place for the source txt

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

        // tárolók törlése, hogy minden betöltéskor tiszta lappal induljunk
        robots = new ArrayList<Robot>();
        cleaners = new ArrayList<CleanerRobot>();
        obstacles = new ArrayList<Obstacle>();
        walls = new ArrayList<Wall>();


        if(this.map != null){
            for(int i = 0; i < this.map.size(); i++)
                for(int j = 0; j < this.map.get(0).length(); j++){
                    element = this.map.get(i).charAt(j);    // csupán rövidítésnek vettem fel
                    // legenerálja a TILE_SIZE-nak megfelelő pozíciókat beszúrásra
                    place = new Dimension(startX + (j * tile), startY + (i * tile));

                    switch (element) {
                        case 'w': walls.add(new Wall(place));   // W falat jelöl a txt-ben
                            //LogHelper.inline("obstacleAdded type: WALL  pos: [" + place.width + "; " + place.height +"]");
                            break;
                        case 'o': obstacles.add(new OilSpot(place));    // O olajat jelöl a txt-ben
                            //LogHelper.inline("obstacleAdded type: OIL  pos: [" + place.width + "; " + place.height +"]");
                            break;
                        case 'p': obstacles.add(new PuttySpot(place));  // P ragacsot jelöl a txt-ben
                            //LogHelper.inline("obstacleAdded type: PUTTY  pos: [" + place.width + "; " + place.height + "]");
                            break;
                        case '_': break;    // _ üres mezőt jelent a txt-ben
                        default: break;
                    }

                    if(element >= '0' && element <= '9'){   // ha szám karakter robotot jelent a txt-ben
                        //addRobot("player" + element, place, Config.DIR_RIGHT, Integer.valueOf(Integer.valueOf(element)-'0'));
                        addRobot("player" + element, place, Config.DIR_RIGHT, robots.size()); // így megegyezik a robot id a robots-beli helyével
                    }
                }
            //LogHelper.inline("arenaInited");    // kimenet generálás

        }
    }

    // feladata beolvasni az arénát tartalmazó txt-t majd visszaadni a beolvasott sortömbböt
    public void loadMap(String fileName) throws IOException {
        ArrayList<String> temp = new ArrayList<String>();
        String line;

        FileReader fr = null;
        BufferedReader br = null;

        try {
            InputStream is = this.getClass().getResourceAsStream(Config.ARENA_FOLDER + fileName);
            InputStreamReader ir = new InputStreamReader(is);
            br = new BufferedReader(ir);

            while ((line = br.readLine()) != null)
                temp.add(new String(line.toLowerCase()));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) br.close();
            if (fr != null) fr.close();
        }

        this.map = temp;
        //LogHelper.inline("arenaLoaded src: " + fileName);

        // size beállítása
        if(this.map != null)
            this.size = new Dimension(this.map.get(0).length() * Config.TILE_SIZE, this.map.size() * Config.TILE_SIZE);
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
            if(onSpot(dest, obstacles.get(i).getPosition())) {
                return obstacles.get(i);
            }
        }
        return null;
    }

    //Egy adott pozíció alapján esetleges itt lévő fal elkérése
    public Wall getWall(Dimension dest){
        for(int i = 0; i < walls.size(); i++){
            if(onSpot(dest, walls.get(i).getPosition())) {
                return walls.get(i);
            }
        }
        return null;
    }

    //cleaner robot ezt hívja meg az akadály eltüntetésére
    public void removeObstacle(Obstacle obstacle){
        obstacles.remove(obstacle);
    }

    //Akadály felvétele a listába, visszatérési értéke, hogy sikerült e lehelyezni foltot, amit csak akkor lehet, ha még nem volt ott
    public boolean addObstacle(Obstacle o) {
        boolean ret = true;
        for(Obstacle temp : obstacles)
            if(onSpot(o.getPosition(), temp.getPosition()))
                ret = false;
        if(ret)
            obstacles.add(o);

        return ret;
    }

    //Új Takarító Robotot ad a játékhoz
    public void addCleanerRobot(Dimension pos, int dir, int id){
        CleanerRobot roboC = new CleanerRobot(this, pos, dir, id);
        //LogHelper.inline("cleanerRobotAdded id: " + id + " pos: [" + pos.width + "; " + pos.height + "]");
        cleaners.add(roboC);
    }

    public CleanerRobot getCleanerRobot(int id){
        return cleaners.get(id);
    }

    //Vissza adja az adott pozícióban lévő Takarító Robotot
    public CleanerRobot getCleanerRobot(Dimension pos){
        CleanerRobot cRobo = null;
        for(int i = 0; i < cleaners.size(); i++)
            if(onSpot(pos, cleaners.get(i).getPosition())) {
                cRobo = cleaners.get(i);
            }
        return cRobo;
    }

    //Vissza adja az adott pozícióban lévő és ID-val rendelkező Takarító Robotot
    public CleanerRobot getCleanerRobot(Dimension pos, int id){
        CleanerRobot cRobo = null;
        for(int i = 0; i < cleaners.size(); i++)
            if(onSpot(pos, cleaners.get(i).getPosition()) && cleaners.get(i).getID() != id) {
                cRobo = cleaners.get(i);
            }
        return cRobo;
    }

    // tesztesethez addRobot
    public void addRobot(String name, Dimension pos, int dir, int id){
        if(robots.size() < 2) {
            Robot robo = new Robot(this, name, pos, dir, id);
            //LogHelper.inline("robotAdded id: " + id + " name: \"" + name + "\" pos: [" + pos.width + "; " + pos.height + "]");
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
        return temp;
    }

    //Vissza adja az adott pozícióban lévő és ID-val rendelkező Robotot
    public Robot getRobot(Dimension pos, int id){
        Robot robo = null;
        for(int i = 0; i < robots.size(); i++)
            if(onSpot(pos, robots.get(i).getPosition()) && robots.get(i).getID() != id) {
                robo = robots.get(i);
            }
        return robo;
    }

    //Ütközésdetekció
    public Dimension collision(Robot r, Dimension d) {
        Obstacle w = null;
        Dimension temp;
        int dir = r.getDirection();
        double speed = r.getSpeed();
        double plot = speed < 1? speed : 1;
        if(speed != 0) {
            for (temp = r.getPosition(); w == null && !temp.equals(d); ) {
                if (dir % 2 == 0) {
                    dir = (dir - 1) * (-1);                              // 0: (0 - 1) = -1; (-1) * (-1) = +1;  => X+
                    temp.height += Math.round(dir * plot * Config.TILE_SIZE);   // 2: (2 - 1) = +1;   1  * (-1) = -1;  => X-
                } else {
                    dir = (dir - 2) * (-1);                              // 1: (1 - 2) = -1; (-1) * (-1) = +1;  => Y+
                    temp.width += Math.round(dir * plot * Config.TILE_SIZE);    // 3: (3 - 2) = +1;   1  * (-1) = -1;  => Y-
                }
                w = getWall(temp);
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
        //LogHelper.inline("robotMoved id: " + r.getID() + " pos: [" + r.getPosition().width + "; " + r.getPosition().height + "]");
        if(isOutOfArena(fin)) {                 // fin a végső pozíció ahova ugrottunk
            r.die();                            // ha pályán kívül van akkor kinyírjuk a robotot
        } else {
            CleanerRobot cRobo = getCleanerRobot(fin);  // ha ráléptünk takarítóra
            if (cRobo != null)                           // nyírjuk ki
                cRobo.die();
            Robot robo = getRobot(fin, r.getID());
            if (robo != null && !robo.dead) {
                if (robo.getSpeed() > r.getSpeed())      // sebesség alapján nyírjuk ki a megfelelőt
                    r.die();
                else
                    robo.die();
            }

            if(!r.dead) {
                Obstacle on = getObstacle(fin);
                if (on != null)
                    on.effect(r);                   // ha akdályra lép akkor hasson rá
                else
                    r.clearEffects();               // ha nem lép semmire töröljük az eddigi hatásokat
            }
        }
    }

    //Irányításkezelő fgv
    public void movementControl(int e) {
        //A két robot mozgását külön gombokkal kezeljük, ezek megnyomása közvetlen a robot
        //megfelelő fgvének meghívását vonja maga után
        switch (e) {
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

    //meg kellett tartani az (int e)-s alakot, hogy a prototype müködőképes maradjon
    public void movementControl(KeyEvent e){
        movementControl(e.getKeyCode());
    }

    //A játék óraütéshez igazításához hazsnált
    public void tick() {
        //LogHelper.inline("tick");
        for (int j = 0; j < cleaners.size(); j++) {
            cleaners.get(j).move();
        }

        for (int j = 0; j < robots.size(); j++) {
            robots.get(j).move();
            robots.get(j).dropObstacle();
        }

        if(remainingRobots() < 2)
            Game.gameOver();
        else {
            for (int j = 0; j < obstacles.size(); ) {
                //ha az olaj felszáradt, vagy ragacs elkopott, töröljük a pályáról, egyébként az olajat öregítjük
                if (obstacles.get(j).getAge() == Config.AGE_LIMIT) {
                    //LogHelper.inline("obstacleRemoved pos: [" + obstacles.get(j).getPosition().width + "; " + obstacles.get(j).getPosition().height + "]");
                    obstacles.remove(j);
                } else if (obstacles.get(j).getDecay() == Config.DECAY_LIMIT) {
                    //LogHelper.inline("obstacleRemoved pos: [" + obstacles.get(j).getPosition().width + "; " + obstacles.get(j).getPosition().height + "]");
                    obstacles.remove(j);
                } else {
                    obstacles.get(j).age();
                    j++;
                }
            }
        }
    }

    // igazat ad ha a dest(ination) pozíció a spot közelében van. (azaz ha a robot tényleg folton áll)
    private boolean onSpot(Dimension dest, Dimension spot){
        boolean ret = false;
        int tile = Config.TILE_SIZE/2; // csupán rövidítés

        if(dest.width < spot.width + tile && dest.width > spot.width - tile
                && dest.height < spot.height + tile && dest .height > spot.height - tile){
            ret = true;
        }

        return ret;
    }

    // visszaadja mennyi robot él még
    private int remainingRobots(){
        int ret = robots.size();

        for(int i = 0; i < robots.size(); i++){
            if(robots.get(i).dead)
                ret--;
        }
        return ret;
    }

    // visszaadja a teljes obstacle listát
    public List<Obstacle> getObstacleList() {
        return obstacles;
    }

    //Vissza adja a Falakat tartalmazó listát
    public List<Wall> getWallList(){
        return walls;
    }

    //Vissza adja a Robotokat tartalmazó listát
    public List<Robot> getRobotList(){
        return robots;
    }

    //Vissza adja a Takarító Robotokat tartalmazó listát
    public List<CleanerRobot> getCleanersList(){
        return cleaners;
    }

    //Vissza adja a Pálya méretét
    public Dimension getSize() {
        return size;
    }
}
