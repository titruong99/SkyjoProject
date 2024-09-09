package g54900.skyjo.message.client;

import g54900.skyjo.message.model.MessageSkyjo;
import g54900.skyjo.message.model.TypeMessageSkyjo;
import java.util.List;

/**
 * Class MessageExchangeHitCard represents the message sent by a client to the
 * server when a player wants to exchange a card with the top card on the
 * discard pile.
 *
 * @author timmy
 */
public class MessageExchangeDiscardCard implements MessageSkyjo {

    private List<Integer> coordinates;

    /**
     * Constructor of MessageExchangeDiscardCard with the coordinates of the
     * card to exchange with the top card of the discard pile as argument.
     *
     * @param coordinates the coordinates of the card to exchange
     */
    public MessageExchangeDiscardCard(List<Integer> coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * Gets the type of the message.
     *
     * @return the type EXCHANGE_DISCARD_CARD.
     */
    @Override
    public TypeMessageSkyjo getType() {
        return TypeMessageSkyjo.EXCHANGE_DISCARD_CARD;
    }

    /**
     * Gets the content of message.
     *
     * @return the coordinates of the card to exchange.
     */
    @Override
    public Object getContent() {
        return coordinates;
    }
}
