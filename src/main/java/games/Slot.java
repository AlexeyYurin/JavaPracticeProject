package games;

import static java.lang.Math.*;


/*
У Вас 100$, ставка - 10$
Крутим барабаны!Розыгрыш принёс следующие результаты:
первый барабан - 3, второй - 3, третий - 6
Проигрыш 10$, ваш капитал теперь составляет: 90$
У Вас 90$, ставка - 10$
Крутим барабаны!Розыгрыш принёс следующие результаты:
первый барабан - 4, второй - 3, третий - 2
Проигрыш 10$, ваш капитал теперь составляет: 80$
...
У Вас 20$, ставка - 10$
Крутим барабаны!Розыгрыш принёс следующие результаты:
первый барабан - 6, второй - 0, третий - 3
Проигрыш 10$, ваш капитал теперь составляет: 10$
У Вас 10$, ставка - 10$
Крутим барабаны!Розыгрыш принёс следующие результаты:
первый барабан - 4, второй - 1, третий - 4
Проигрыш 10$, ваш капитал теперь составляет: 0$
* */

public class Slot {
    public static void main(String... args) {
        int yourMoney = 100;
        int rateOnSpin = 10;
        int size = 7;

        int firstSpin;
        int secondSpin;
        int thirdSpin;

        System.out.println("У Вас " + yourMoney + "$, ставка - " + rateOnSpin + "$");

        while (yourMoney > 0){
            yourMoney -= rateOnSpin;
            firstSpin = (int) ((round(random() * 100)) % size) + 1;
            secondSpin = (int) ((round(random() * 100)) % size) + 1;
            thirdSpin = (int) ((round(random() * 100)) % size) + 1;
            System.out.println("Крутим барабаны!Розыгрыш принёс следующие результаты:");
            System.out.println("первый барабан - " + firstSpin + ", второй - " + secondSpin+ ", третий - " + thirdSpin);
            if ((firstSpin == secondSpin)&&(secondSpin == thirdSpin)) {
                yourMoney += 1000;
                System.out.println("Вы выиграли! Ваш капитал теперь составляет: " + yourMoney + "$");
            } else {
                System.out.println("Проигрыш " + rateOnSpin + "$, ваш капитал теперь составляет: " + yourMoney + "$");
            }
        }
    }
}
