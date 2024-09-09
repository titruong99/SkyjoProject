package g54900.skyjo.message.server;

import g54900.skyjo.message.model.MessageSkyjo;
import g54900.skyjo.message.model.StatusClient;
import g54900.skyjo.message.model.TypeMessageSkyjo;

/**
 * Class MessageInGame represents the message sent by the server to a client
 * telling him he is in a game against an other client.
 *
 * @author timmy
 */
public class MessageInGame implements MessageSkyjo {

    private StatusClient status;

    /**
     * Constructor of MessageInGame with the status of the client as argument.
     *
     * @param status the status of the client
     */
    public MessageInGame(StatusClient status) {
        this.status = status;
    }

    /**
     * Gets the type of the message.
     *
     * @return the type IN_GAME.
     */
    @Override
    public TypeMessageSkyjo getType() {
        return TypeMessageSkyjo.IN_GAME;
    }

    /**
     * Gets the content of the message.
     *
     * @return the status of the client.
     */
    @Override
    public Object getContent() {
        return status;
    }
}
