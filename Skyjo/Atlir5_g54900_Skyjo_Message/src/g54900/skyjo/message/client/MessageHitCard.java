package g54900.skyjo.message.client;

import g54900.skyjo.message.model.MessageSkyjo;
import g54900.skyjo.message.model.TypeMessageSkyjo;

/**
 * Class MessageHitCard represents the message sent by a client to the server
 * when a player wants to hit a card from the deck.
 *
 * @author timmy
 */
public class MessageHitCard implements MessageSkyjo {

    private boolean hitCard;

    /**
     * Constructor of MessageHitCard with a boolean as argument saying if a
     * client wants to hit a card or not.
     *
     * @param hitCard true if the client wants to hit a card.
     */
    public MessageHitCard(boolean hitCard) {
        this.hitCard = hitCard;
    }

    /**
     * Gets the type of the message.
     *
     * @return the type HIT_CARD.
     */
    @Override
    public TypeMessageSkyjo getType() {
        return TypeMessageSkyjo.HIT_CARD;
    }

    /**
     * Gets the content of the message.
     *
     * @return true if the client wants to hit a card.
     */
    @Override
    public Object getContent() {
        return hitCard;
    }
}
