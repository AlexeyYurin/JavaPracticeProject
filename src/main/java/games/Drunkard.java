package games;

import org.slf4j.Logger;

import static games.CardUtils.*;
import static java.lang.String.*;

public class Drunkard {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(Drunkard.class);
    
    private static final int FIRST_PLAYER = 0;
    private static final int SECOND_PLAYER = 1;

    private static final int[][] playersCards = new int[2][CARDS_TOTAL_COUNT];
    private static final int[] playerCardTails = new int[2];
    private static final int[] playerCardHeads = new int[2];
    private static int countIteration = 1;
    private static int lastWinPlayer;

    public static void main(String... args) {
        distributionCards();

        while (!(playerCardsIsEmpty(FIRST_PLAYER)&&!(playerCardsIsEmpty(SECOND_PLAYER)))) {
            var firstPlayerCard = getTopCard(FIRST_PLAYER);
            var secondPlayerCard = getTopCard(SECOND_PLAYER);
            logger.info(format("Итерация №%d; игрок №1 карта: %s; игрок №2 карта: %s.",
                    countIteration, CardUtils.toString(firstPlayerCard), CardUtils.toString(secondPlayerCard)));
            int compareCards = compare(getPar(firstPlayerCard), (getPar(secondPlayerCard)));
            if (compareCards > 0) {
                logger.info("Выиграл игрок 1!");
                lastWinPlayer = FIRST_PLAYER;
                addCard(FIRST_PLAYER, firstPlayerCard);
                addCard(FIRST_PLAYER, secondPlayerCard);
            } else if (compareCards < 0) {
                logger.info("Выиграл игрок 2!");
                lastWinPlayer = SECOND_PLAYER;
                addCard(SECOND_PLAYER, firstPlayerCard);
                addCard(SECOND_PLAYER, secondPlayerCard);
            } else {
                logger.info("Ничья!");
                addCard(FIRST_PLAYER, firstPlayerCard);
                addCard(SECOND_PLAYER, secondPlayerCard);
            }
            countIteration++;
        }

        logger.info(format("Игры выиграл игрок №%d", lastWinPlayer + 1));
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

    private static void distributionCards() {
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

