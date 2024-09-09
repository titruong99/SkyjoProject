package g54900.skyjo.model;

/**
 * Class card represents a card of the deck of cards. Each card has a value and
 * can be hidden or visible.
 *
 * @author timmy
 */
public class Card {

    private Value value;
    private boolean hidden;

    /**
     * Constructor of a card with a value as an argument and is hidden by
     * default.
     *
     * @param _value the value of the card
     */
    public Card(Value _value) {
        value = _value;
        hidden = true;
    }

    /**
     * Gets the value of a card.
     *
     * @return the value of the card.
     */
    public Value getValueCard() {
        return value;
    }

    /**
     * Checks if a card is hidden or not.
     *
     * @return true if the card is hidden, false otherwise.
     */
    public boolean isHidden() {
        return hidden;
    }

    /**
     * Makes the card not hidden anymore.
     */
    void setVisible() {
        hidden = false;
    }
}
