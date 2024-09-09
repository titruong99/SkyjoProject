package g54900.skyjo.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class DiscardPile represents the pile of discarded cards of the game.
 *
 * @author timmy
 */
public class DiscardPile {

    private List<Card> discardPile;

    /**
     * Constructor of DiscardPile, it creates an empty list that can welcome the
     * discarded cards.
     */
    public DiscardPile() {
        discardPile = new ArrayList<>();
    }

    /**
     * Gets the visible card on top of the pile of discarded cards.
     *
     * @return the visible card on top of the discard pile.
     */
    public Card getTopCard() {
        return discardPile.get(discardPile.size() - 1);
    }

    /**
     * Puts a card in the pile of discarded cards and makes the card visible.
     *
     * @param card the card to add in the pile of discarded cards.
     */
    void putOnDiscardPile(Card card) {
        if (card.isHidden()) {
            card.setVisible();
        }
        discardPile.add(card);
    }

    /**
     * Removes the top card from the discard pile.
     *
     * @return the card that was on the top of the discard pile.
     */
    Card removeTopCard() {
        return discardPile.remove(discardPile.size() - 1);
    }
}
