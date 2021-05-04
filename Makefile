.DEFAULT_GOAL := compile-run

compile-run: compile run

compile: clean
	mkdir -p ./target/classes
	javac -d ./target/classes ./src/main/java/games/Slot.java

run:
	java -cp ./target/classes main.java.games.Slot

clean:
	rm -rf ./target