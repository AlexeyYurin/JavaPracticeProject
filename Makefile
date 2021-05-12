.DEFAULT_GOAL := build-run

build-run: build run

run:
	java -Dfile.encoding="cp866" -jar ./target/JavaPracticeProject-1-jar-with-dependencies.jar

build:
	mvnw clean package

update:
	mvnw versions:display-plugin-updates
