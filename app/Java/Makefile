all: Game.class

Game.class: Game.java
	javac -classpath java Game.java

run: Game.class
	java -classpath .:java -Djava.library.path=jniLibs Game

clean:
	-rm -f Game.class
