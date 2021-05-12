package games;

import org.slf4j.Logger;

import java.io.IOException;
import java.util.Scanner;

public class Choice {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(Choice.class);

    static final Scanner s= new Scanner(System.in);

    static char getCharacterFromUser(){
        return s.next().charAt(0);
    }

    public static void main(String... args) throws IOException {

        logger.info("Выберите игру:\n1 - \"однорукий бандит\", 2 - \"пьяница\", 3 - \"21 очко\"");
        switch (getCharacterFromUser()) {
            case '1': Slot.main(); break;
            case '2': Drunkard.main(); break;
            case '3': BlackJack.main(); break;
            default: logger.info("Игры с таким номером нет!");
        }
    }
}
