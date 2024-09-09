package g54900.skyjo.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class Deck represents the list of cards of the game.
 *
 * @author timmy
 */
public class Deck {

    private static final int NB_MINUS_TWO_CARDS = 5;
    private static final int NB_ZERO_CARDS = 15;
    private static final int NB_OTHER_CARDS = 10;

    private List<Card> deck;

    /**
     * Constructor of a deck, it creates a deck with the 150 cards.
     */
    public Deck() {
        deck = new ArrayList<>();
        createDeck();
    }

    /**
     * Shuffles the deck of cards.
     */
    void shuffle() {
        Collections.shuffle(deck);
    }

    /**
     * Hits a card from the deck.It removes the last card (the card that has
     * been hit) from the deck.
     *
     * @return the card that has been hit.
     */
    Card hit() {
        return deck.remove(deck.size() - 1);
    }

    /**
     * Private method called by createDeck, it creates the number of cards
     * according to the value of the card and adds the new card in the deck.
     *
     * @param number the number of cards to create
     * @param value the value of the card
     */
    private void createCard(int number, Value value) {
        for (int i = 0; i < number; i++) {
            Card card = new Card(value);
            deck.add(card);
        }
    }

    /**
     * Private method called by the constructor to create a deck of cards for
     * the game.
     */
    private void createDeck() {
        for (Value cardValue : Value.values()) {
            switch (cardValue) {
                case MINUS_TWO:
                    createCard(NB_MINUS_TWO_CARDS, cardValue);
                    break;
                case ZERO:
                    createCard(NB_ZERO_CARDS, cardValue);
                    break;
                default:
                    createCard(NB_OTHER_CARDS, cardValue);
                    break;
            }
        }
    }
}
