package g54900.skyjo.model;

/**
 * Class Hand represents the list of cards of a player.
 *
 * @author timmy
 */
public class Hand {

    static final int NB_ROWS = 3;
    static final int NB_COLUMNS = 4;

    private Card[][] cards;

    /**
     * Constructor of a hand without arguments. It creates a hand without cards.
     */
    public Hand() {
        cards = new Card[NB_ROWS][NB_COLUMNS];
    }

    /**
     * Constructor of a hand with an array of card as argument. The method is
     * used in the tests.
     *
     * @param _cards the cards of the hand
     */
    public Hand(Card[][] _cards) {
        cards = _cards;
    }

    /**
     * Gives a copy of the cards of the player.
     *
     * @return a copy of the cards of the player.
     */
    public Card[][] getCards() {
        return copyCards();
    }

    /**
     * Adds a card in the hand of a player.
     *
     * @param card the card to add
     * @param row the row where the card will be put
     * @param column the column where the card will be put
     */
    void addCard(Card card, int row, int column) {
        cards[row][column] = card;
    }

    /**
     * Reveals all the cards of the player.
     */
    void revealCards() {
        for (Card[] rowCard : cards) {
            for (Card card : rowCard) {
                if (card.isHidden()) {
                    card.setVisible();
                }
            }
        }
    }

    /**
     * Computes the actual points of the visible cards in the hand of the
     * player.
     *
     * @return the total of points of the visible cards.
     */
    public int getPoints() {
        int points = 0;
        for (Card[] rowCards : cards) {
            for (Card card : rowCards) {
                if (!card.isHidden()) {
                    points += card.getValueCard().getValue();
                }
            }
        }
        return points;
    }

    /**
     * Reveals the card according to the row and the column chosen by the
     * player.
     *
     * @param row the row chosen by the player
     * @param column the column chosen by the player
     */
    void revealCard(int row, int column) {
        cards[row][column].setVisible();
    }

    /**
     * Private method called by getCards: it copies the cards of the player into
     * an other array.
     *
     * @return the copy of the cards.
     */
    private Card[][] copyCards() {
        Card[][] copy = new Card[NB_ROWS][NB_COLUMNS];
        for (int i = 0; i < cards.length; i++) {
            System.arraycopy(cards[i], 0, copy[i], 0, cards[i].length);
        }
        return copy;
    }
}
