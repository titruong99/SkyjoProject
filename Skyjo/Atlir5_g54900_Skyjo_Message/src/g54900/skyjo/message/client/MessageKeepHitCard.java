package g54900.skyjo.message.client;

import g54900.skyjo.message.model.MessageSkyjo;
import g54900.skyjo.message.model.TypeMessageSkyjo;
import java.util.List;

/**
 * Class MessageKeepHitCard represents the message sent by a client to the
 * server when a client wants to keep the hit card and wants to exchange with
 * one of his cards.
 *
 * @author timmy
 */
public class MessageKeepHitCard implements MessageSkyjo {

    private List<Integer> coordinates;

    /**
     * Constructor of MessageKeepHitCard with the coordinates of the card to
     * exchange with the hid card as argument.
     *
     * @param coordinates the coordinates of the card to exchange with the hit
     * card
     */
    public MessageKeepHitCard(List<Integer> coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * Gets the type of the message.
     *
     * @return the type KEEP_HIT_CARD.
     */
    @Override
    public TypeMessageSkyjo getType() {
        return TypeMessageSkyjo.KEEP_HIT_CARD;
    }

    /**
     * Gets the content of the message.
     *
     * @return the coordinates of the card to exchange with the hit card.
     */
    @Override
    public Object getContent() {
        return coordinates;
    }
}
