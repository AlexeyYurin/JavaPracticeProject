package games;

import org.slf4j.Logger;

import static java.lang.Math.*;
import static java.lang.String.*;

public class Slot {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(Slot.class);

    public static void main(String... args) {
        var yourMoney = 100;
        var rateOnSpin = 10;
        var size = 7;
        var jackpot = 1000;

        int firstSpin;
        int secondSpin;
        int thirdSpin;

        logger.info(format("У Вас %d$, ставка - %d$", yourMoney, rateOnSpin));

        while (yourMoney > 0){
            yourMoney -= rateOnSpin;
            firstSpin = (int) ((round(random() * 100)) % size) + 1;
            secondSpin = (int) ((round(random() * 100)) % size) + 1;
            thirdSpin = (int) ((round(random() * 100)) % size) + 1;
            logger.info("Крутим барабаны!Розыгрыш принёс следующие результаты:");
            logger.info(format("первый барабан - %d, второй - %d, третий - %d", firstSpin, secondSpin, thirdSpin));
            if ((firstSpin == secondSpin)&&(secondSpin == thirdSpin)) {
                yourMoney += jackpot;
                logger.info(format("Вы выиграли! Ваш капитал теперь составляет: %d$", yourMoney));
            } else {
                logger.info(format("Проигрыш %d$, ваш капитал теперь составляет: %d$", rateOnSpin, yourMoney));
            }
        }
    }
}
