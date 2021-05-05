.DEFAULT_GOAL := build-run

build-run: build run

run:
	java -jar ./target/JavaPracticeProject-1.0-SNAPSHOT-jar-with-dependencies.jar

build:
	mvnw clean package

update:
	mvnw versions:display-plugin-updates
