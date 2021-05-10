package games;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CardUtils {
    public static final int PARS_TOTAL_COUNT = Par.values().length; //9
    public static final int CARDS_TOTAL_COUNT = PARS_TOTAL_COUNT * Suit.values().length; //36
    static final Integer[] cards = new Integer[CARDS_TOTAL_COUNT];

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

    public static CardUtils.Suit getSuit(int cardNumber) {
        return CardUtils.Suit.values()[cardNumber / PARS_TOTAL_COUNT];
    }

    public static CardUtils.Par getPar(int cardNumber) {
        return CardUtils.Par.values()[cardNumber % PARS_TOTAL_COUNT];
    }

    public static String toString(int cardNumber) {
        return getPar(cardNumber) + " " + getSuit(cardNumber);
    }

    public static void shuffleCards() {
        List<Integer> cardsList = Arrays.asList(cards);

        Collections.shuffle(cardsList);

        cardsList.toArray(cards);
    }

    public static void createCards() {
        for (var i = 0; i < cards.length; i++) {
            cards[i] = i;
        }
    }
}
