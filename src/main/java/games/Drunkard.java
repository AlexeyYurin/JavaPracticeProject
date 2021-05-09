package games;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Drunkard {
    private static final int PARS_TOTAL_COUNT = Par.values().length; //9
    private static final int CARDS_TOTAL_COUNT = PARS_TOTAL_COUNT * Suit.values().length; //36
    private static final int FIRST_PLAYER = 0;
    private static final int SECOND_PLAYER = 1;

    private static int[][] playersCards = new int[2][CARDS_TOTAL_COUNT];
    private static int[] playerCardTails = new int[2];
    private static int[] playerCardHeads = new int[2];
    private static Integer[] cards = new Integer[CARDS_TOTAL_COUNT];
    private static int countIteration = 1;
    private static int lastWinPlayer;

    public static void main(String... args) {
        System.out.println(toString(35));
        distributionOfCards();

        /*System.out.println("Карты первого игрока");
        for (int card:playersCards[0]) {
        System.out.println("card = " + card);
        }
        System.out.println("Карты второго игрока");
        for (int card:playersCards[1]) {
        System.out.println("card = " + card);
        }*/

        while (!(playerCardsIsEmpty(FIRST_PLAYER)&&!(playerCardsIsEmpty(SECOND_PLAYER)))) {
        //while (countIteration <= 36) {
            int firstPlayerCard = getTopCard(FIRST_PLAYER);
            int secondPlayerCard = getTopCard(SECOND_PLAYER);
            System.out.println("Итерация №" + countIteration + "; игрок №1 карта: " + toString(firstPlayerCard) +
                               "; игрок №2 карта: " + toString(secondPlayerCard) + ".");
            int compareCards = compare(getPar(firstPlayerCard), (getPar(secondPlayerCard)));
            if (compareCards > 0) {
                //добавляем карты первому игроку
                System.out.println("Выиграл игрок 1!");
                lastWinPlayer = FIRST_PLAYER;
                addCard(FIRST_PLAYER, firstPlayerCard);
                addCard(FIRST_PLAYER, secondPlayerCard);
            } else if (compareCards < 0) {
                //добавляем карты второму игроку
                System.out.println("Выиграл игрок 2!");
                lastWinPlayer = SECOND_PLAYER;
                addCard(SECOND_PLAYER, firstPlayerCard);
                addCard(SECOND_PLAYER, secondPlayerCard);
            } else if (compareCards == 0) {
                //добавляем карты и первому и второму игроку
                System.out.println("Ничья!");
                addCard(FIRST_PLAYER, firstPlayerCard);
                addCard(SECOND_PLAYER, secondPlayerCard);
            }
            countIteration++;
        }

        System.out.println("Игры выиграл игрок №" + (lastWinPlayer + 1));

    }

    private static int getTopCard(int playerIndex) {
        playerCardTails[playerIndex] = incrementIndex(playerCardTails[playerIndex]);
        int tail = playerCardTails[playerIndex];
        //System.out.println("Игрок №" + (playerIndex + 1) + " tail = " + tail);
        return playersCards[playerIndex][tail];
    }

    private static void addCard(int playerIndex, int cardValue) {
        playerCardHeads[playerIndex] = incrementIndex(playerCardHeads[playerIndex]);
        int head = playerCardHeads[playerIndex];
        //System.out.println("Игрок №" + (playerIndex + 1) + " head = " + head);
        playersCards[playerIndex][head] = cardValue;
    }

    private static Suit getSuit(int cardNumber) {
        return Suit.values()[cardNumber / PARS_TOTAL_COUNT];
    }

    private static Par getPar(int cardNumber) {
        return Par.values()[cardNumber % PARS_TOTAL_COUNT];
    }

    private static String toString(int cardNumber) {
        return getPar(cardNumber) + " " + getSuit(cardNumber);
    }

    private static int incrementIndex(int i) {
        return (i + 1) % CARDS_TOTAL_COUNT;
    }

    private static boolean playerCardsIsEmpty(int playerIndex) {
        int tail = playerCardTails[playerIndex];
        int head = playerCardHeads[playerIndex];

        return tail == head;
    }

    private static void distributionOfCards() {
        createCards();
        shuffleCards();

        //testCreateCards();
        for (int i = 0; i < CARDS_TOTAL_COUNT; i++) {
            if (i < CARDS_TOTAL_COUNT / 2) {
                playersCards[FIRST_PLAYER][i] = cards[i];
            } else {
                playersCards[SECOND_PLAYER][i - CARDS_TOTAL_COUNT / 2] = cards[i];
            }
        }

        playerCardTails[FIRST_PLAYER] = 0;
        playerCardHeads[FIRST_PLAYER] = CARDS_TOTAL_COUNT / 2 - 1;
        playerCardTails[SECOND_PLAYER] = 0;
        playerCardHeads[SECOND_PLAYER] = CARDS_TOTAL_COUNT / 2 - 1;
    }

    private static Integer[] createCards() {
        for (int i = 0; i < cards.length; i++) {
            cards[i] = i;
        }
        return cards;
    }

    private static Integer[] testCreateCards() {
        cards[0] = 2;
        cards[1] = 2;
        cards[2] = 2;
        cards[3] = 2;
        cards[4] = 2;
        cards[5] = 2;
        cards[6] = 2;
        cards[7] = 2;
        cards[8] = 2;
        cards[9] = 2;
        cards[10] = 2;
        cards[11] = 2;
        cards[12] = 2;
        cards[13] = 2;
        cards[14] = 2;
        cards[15] = 2;
        cards[16] = 2;
        cards[17] = 2;
        cards[18] = 9;
        cards[19] = 9;
        cards[20] = 9;
        cards[21] = 9;
        cards[22] = 9;
        cards[23] = 9;
        cards[24] = 9;
        cards[25] = 9;
        cards[26] = 9;
        cards[27] = 9;
        cards[28] = 9;
        cards[29] = 9;
        cards[30] = 9;
        cards[31] = 9;
        cards[32] = 9;
        cards[33] = 9;
        cards[34] = 9;
        cards[35] = 9;

        return cards;
    }

    private static void shuffleCards() {
        List<Integer> cardsList = Arrays.asList(cards);

        Collections.shuffle(cardsList);

        cardsList.toArray(cards);
    }

    public static int compare(Par par1, Par par2) {
        if (getValueAnInt(par1) > getValueAnInt(par2) && !(getValueAnInt(par1) == 9 && getValueAnInt(par2) == 1)){
            return 1;
        } else if (getValueAnInt(par1) == getValueAnInt(par2)){
            return 0;
        } else {
            return -1;
        }
    }

    public static int getValueAnInt(Par par){
        switch (par) {
            case SIX: return 1;
            case SEVEN: return 2;
            case EIGHT: return 3;
            case NINE: return 4;
            case TEN: return 5;
            case JACK: return 6;
            case QUEEN: return 7;
            case KING: return 8;
            case ACE: return 9;
            default: return -1;
        }
    }
}

enum Suit {
    SPADES, // пики
    HEARTS, // червы
    CLUBS, // трефы
    DIAMONDS // бубны
}

enum Par {
    SIX,
    SEVEN,
    EIGHT,
    NINE,
    TEN,
    JACK, // Валет
    QUEEN, // Дама
    KING, // Король
    ACE // Туз
    ;
}