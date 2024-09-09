package g54900.skyjo.message.client;

import g54900.skyjo.message.model.MessageSkyjo;
import g54900.skyjo.message.model.TypeMessageSkyjo;
import java.util.List;

/**
 * Class MessageDropHitCard represents the message sent by a client to the
 * server when a player wants to drop the hit card and reveals one of his cards.
 *
 * @author timmy
 */
public class MessageDropHitCard implements MessageSkyjo {

    private List<Integer> coordinates;

    /**
     * Constructor of MessageDropHitCard with the coordinates of the card to
     * reveal as argument.
     *
     * @param coordinates the coordinates of the card to reveal.
     */
    public MessageDropHitCard(List<Integer> coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * Gets the type of the message.
     *
     * @return the type DROP_HIT_CARD.
     */
    @Override
    public TypeMessageSkyjo getType() {
        return TypeMessageSkyjo.DROP_HIT_CARD;
    }

    /**
     * Gets the content of the message.
     *
     * @return the coordinates of the card to reveal.
     */
    @Override
    public Object getContent() {
        return coordinates;
    }
}
