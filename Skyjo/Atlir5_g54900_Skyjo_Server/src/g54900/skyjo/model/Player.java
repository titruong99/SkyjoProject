package g54900.skyjo.model;

/**
 * Class Player represents a player in the game of Skyjo. A player has a total
 * of points during the game, a name and a hand(cards of the player).
 *
 * @author timmy
 */
public class Player {

    private int totalPoints;
    private Hand hand;

    /**
     * Constructor of a player with a hand(cards) as argument and puts the total
     * of points of the player at nul by default.
     *
     * @param _hand the cards of the player
     */
    public Player(Hand _hand) {
        hand = _hand;
    }

    /**
     * Gets the hand(cards) of the player.
     *
     * @return the hand of the player.
     */
    public Hand getHand() {
        return hand;
    }

    /**
     * Gets the total of points of the player.
     *
     * @return the total of points of the player.
     */
    public int getTotalPoints() {
        totalPoints = hand.getPoints();
        return totalPoints;
    }

    /**
     * Reveals the cards chosen by the player.
     *
     * @param row row of the chosen card
     * @param column column of the chosen card
     */
    void revealCard(int row, int column) {
        hand.revealCard(row, column);
    }

    /**
     * Changes the card chosen by the player with an other card(hit card or top
     * card).
     *
     * @param card the new card to add
     * @param row row of card to exchange
     * @param column column of the card to exchange
     */
    void changeCard(Card card, int row, int column) {
        hand.addCard(card, row, column);
    }

    /**
     * Reveals all the cards of the player for the end of the game.
     */
    void revealCards() {
        hand.revealCards();
    }
}
