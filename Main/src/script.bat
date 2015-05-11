javac com/szoftlab4/java9esharomnegyed/*.java com/szoftlab4/java9esharomnegyed/Utility/*.java com/szoftlab4/java9esharomnegyed/Prototype/*.java com/szoftlab4/java9esharomnegyed/View/*.java

jar cvfm Game.jar manifest.txt com\szoftlab4\java9esharomnegyed\*.class com\szoftlab4\java9esharomnegyed\Utility\*.class com\szoftlab4\java9esharomnegyed\Prototype\*.class com\szoftlab4\java9esharomnegyed\View\*.class assets\initPanel\*.png assets\arenas\*.txt

java -jar Game.jar
