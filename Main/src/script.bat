del %2

javac com/szoftlab4/java9esharomnegyed/*.java com/szoftlab4/java9esharomnegyed/Utility/*.java com/szoftlab4/java9esharomnegyed/Prototype/*.java

jar cvfm Game.jar manifest.txt com\szoftlab4\java9esharomnegyed\*.class com\szoftlab4\java9esharomnegyed\Utility\*.class com\szoftlab4\java9esharomnegyed\Prototype\*.class

java -jar Game.jar %1 %2 "../data/tests/" "../data/arenas/"

echo N | comp "../data/expected/"%1 %2
