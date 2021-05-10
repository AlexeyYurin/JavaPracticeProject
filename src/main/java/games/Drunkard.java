package games;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static games.CardUtils.*;
import static games.Choice.getCharacterFromUser;

public class Drunkard {
    private static final int FIRST_PLAYER = 0;
    private static final int SECOND_PLAYER = 1;

    private static final int[][] playersCards = new int[2][CARDS_TOTAL_COUNT];
    private static final int[] playerCardTails = new int[2];
    private static final int[] playerCardHeads = new int[2];
    private static int countIteration = 1;
    private static int lastWinPlayer;

    public static void main(String... args) {
        distributionOfCards();

        while (!(playerCardsIsEmpty(FIRST_PLAYER)&&!(playerCardsIsEmpty(SECOND_PLAYER)))) {
            var firstPlayerCard = getTopCard(FIRST_PLAYER);
            var secondPlayerCard = getTopCard(SECOND_PLAYER);
            System.out.println("Итерация №" + countIteration + "; игрок №1 карта: " + CardUtils.toString(firstPlayerCard) +
                               "; игрок №2 карта: " + CardUtils.toString(secondPlayerCard) + ".");
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
            } else {
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
        return playersCards[playerIndex][tail];
    }

    private static void addCard(int playerIndex, int cardValue) {
        playerCardHeads[playerIndex] = incrementIndex(playerCardHeads[playerIndex]);
        int head = playerCardHeads[playerIndex];
        playersCards[playerIndex][head] = cardValue;
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

        for (var i = 0; i < CARDS_TOTAL_COUNT; i++) {
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

    private static void createCards() {
        for (var i = 0; i < cards.length; i++) {
            cards[i] = i;
        }
    }
}

