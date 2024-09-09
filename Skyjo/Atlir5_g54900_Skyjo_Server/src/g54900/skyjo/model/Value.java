package g54900.skyjo.model;

/**
 * Enumeration value represents the value of a card. There are fifteen different
 * values for a card. Each value has an associated integer value and an
 * associated color.
 *
 * @author timmy
 */
public enum Value {
    MINUS_TWO(-2), MINUS_ONE(-1), ZERO(0), ONE(1), TWO(2), THREE(3), FOUR(4),
    FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), ELEVEN(11),
    TWELVE(12);

    private int value;

    /**
     * Private constructor of a value that associates an integer value to a
     * value of the card.
     *
     * @param _value the integer value associating to the value
     */
    private Value(int _value) {
        value = _value;
    }

    /**
     * Gives the integer value of a card.
     *
     * @return the integer value associating to the value of the card.
     */
    public int getValue() {
        return value;
    }
}
