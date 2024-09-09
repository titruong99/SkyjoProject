package g54900.skyjo.model;

import java.util.List;

/**
 * Model represents the methods used by the class Game to turn the game of
 * Skyjo.
 *
 * @author timmy
 */
public interface Model {

    /**
     * Starts the game of Skyjo.
     */
    public void start();

    /**
     * Starts the game by revealing two random cards from the hand of the two
     * players and determines who begins.
     *
     */
    public void startGame();

    /**
     * Exchanges the card chosen by the player with the top card of the discard
     * pile.
     *
     * @param row row of the chosen card
     * @param column column of the chosen card
     */
    public void exchangeWithDiscardCard(int row, int column);

    /**
     * Exchanges the card that has been hit with the card chosen by the player.
     * The card chosen by the player is put after on the discard pile.
     *
     * @param row row of the chosen card
     * @param column column of the chosen card
     */
    public void keepHitCard(int row, int column);

    /**
     * Drops the card that has been hit by putting it in the discard pile and
     * lets the player reveal the card he or she has chosen.
     *
     * @param row row of the chosen card
     * @param column column of the chosen card
     */
    public void dropHitCard(int row, int column);

    /**
     * Hits a card from the deck by calling the method in the class Deck.
     */
    public void hitCard();

    /**
     * Puts the different informations of the game like the current player or
     * the number of points of the players...
     */
    public void putInformationsGame();

    /**
     * Gets the informations of the game.
     *
     * @return the informations of the game
     *
     */
    public List<Integer> getInformationsGame();

    /**
     * Gets the index of the game.
     *
     * @return the index of the game
     */
    public int getIndex();

}
