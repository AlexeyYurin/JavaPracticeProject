package games;

import org.slf4j.Logger;

import java.io.IOException;

import static games.CardUtils.*;
import static games.Choice.getCharacterFromUser;

public class BlackJack {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(BlackJack.class);

    private static final int FIRST_PLAYER = 0;
    private static final int SECOND_PLAYER = 1;
    private static final int MAX_VALUE = 21;
    private static final int MAX_CARDS_COUNT = 8;
    private static final int START_PLAYER_MONEY = 100;
    private static final int ROUND_STEP = 10;

    private static int cursor; // Счётчик карт основной колоды
    private static int nextCard = 0;

    private static int[][] playersCards; // карты игроков. Первый индекс - номер игрока
    private static int[] playersCursors; // курсоры карт игроков. Индекс - номер игрока
    private static int[] playersMoney = {START_PLAYER_MONEY, START_PLAYER_MONEY};

    private static void initRound() {
        logger.info(String.format("\nУ Вас %d$, у дилера - %d$. Начинаем новый раунд!", playersMoney[FIRST_PLAYER], playersMoney[SECOND_PLAYER]));
        createCards();
        shuffleCards();
        playersCards = new int[2][MAX_CARDS_COUNT];
        playersCursors = new int[]{0, 0};
        cursor = 0;
    }

    public static void main(String... args) throws IOException {

        while (playersMoney[FIRST_PLAYER] != 0 && playersMoney[SECOND_PLAYER] != 0) {
            initRound();

            //ходы игрока
            nextCard = addCard2Player(FIRST_PLAYER);
            logger.info(String.format("Вам выпала карта %s", CardUtils.toString(nextCard)));
            nextCard = addCard2Player(FIRST_PLAYER);
            logger.info("Вам выпала карта " + CardUtils.toString(nextCard));
            while (confirm("Берём ещё?")) {
                nextCard = addCard2Player(FIRST_PLAYER);
                logger.info("Вам выпала карта " + CardUtils.toString(nextCard));
                if (getFinalSum(FIRST_PLAYER) == 0) {
                    break;
                }
            }
            //ходы дилера
            nextCard = addCard2Player(SECOND_PLAYER);
            logger.info("Дилеру выпала карта " + CardUtils.toString(nextCard));
            nextCard = addCard2Player(SECOND_PLAYER);
            logger.info("Дилеру выпала карта " + CardUtils.toString(nextCard));
            while (getFinalSum(SECOND_PLAYER) < 17 && getFinalSum(SECOND_PLAYER) != 0) {
                nextCard = addCard2Player(SECOND_PLAYER);
                logger.info("Дилеру решил взять ещё и ему выпала карта " + CardUtils.toString(nextCard));
            }
            logger.info("Сумма ваших очков - " + getFinalSum(FIRST_PLAYER) + ", дилера - " + getFinalSum(SECOND_PLAYER));
            if (getFinalSum(FIRST_PLAYER) == getFinalSum(SECOND_PLAYER)) {
                logger.info("Ничья!");
            } else if (getFinalSum(FIRST_PLAYER) > getFinalSum(SECOND_PLAYER)) {
                logger.info("Вы выиграли раунд! Получаете 10$");
                distributionMoney(FIRST_PLAYER, SECOND_PLAYER);
            } else {
                logger.info("Вы проиграли раунд! Теряете 10$");
                distributionMoney(SECOND_PLAYER, FIRST_PLAYER);
            }
        }

        if (playersMoney[0] > 0)
            logger.info("Вы выиграли! Поздравляем!");
        else
            logger.info("Вы проиграли. Соболезнуем...");
    }

    private static void distributionMoney(int winner, int looser) {
        playersMoney[winner] += ROUND_STEP;
        playersMoney[looser] -= ROUND_STEP;
    }

    private static int addCard2Player(int player) {
        var cardValue = cards[cursor];
        playersCards[player][playersCursors[player]] = cardValue;
        incrementCursors(player);
        return cardValue;
    }

    private static void incrementCursors(int player) {
        cursor++;
        playersCursors[player]++;
    }

    static int sum(int player) {
        int sum = 0;
        for (int i = 0; i < playersCursors[player]; i++) {
            sum += value(playersCards[player][i]);
        }
        //если выпало два туза, то блекджек
        if (playersCards[player].length == 2 && sum == 22) {
            sum = 21;
        }
        return sum;
    }

    static int getFinalSum(int player) {
        var finalSum = sum(player);
        if (finalSum > MAX_VALUE) {
            return 0;
        } else {
            return finalSum;
        }
    }

    static boolean confirm(String message) throws IOException {
        logger.info(message + " \"Y\" - Да, {любой другой символ} - нет (Чтобы выйти из игры, нажмите Ctrl + C)");
        switch (getCharacterFromUser()) {
            case 'Y':
            case 'y':
                return true;
            default:
                return false;
        }
    }

    private static int value(int card) {
        switch (CardUtils.getPar(card)) {
            case JACK:
                return 2;
            case QUEEN:
                return 3;
            case KING:
                return 4;
            case SIX:
                return 6;
            case SEVEN:
                return 7;
            case EIGHT:
                return 8;
            case NINE:
                return 9;
            case TEN:
                return 10;
            case ACE:
            default:
                return 11;
        }
    }
}
