package g54900.skyjo.message.server;

import g54900.skyjo.message.model.MessageSkyjo;
import g54900.skyjo.message.model.StatusClient;
import g54900.skyjo.message.model.TypeMessageSkyjo;

/**
 * Class MessageInWait represents the message sent by the server to a client
 * telling him he is in waiting for a game because there are not enough players
 * for a game.
 *
 * @author timmy
 */
public class MessageInWait implements MessageSkyjo {

    private StatusClient status;

    /**
     * Constructor of MessageInWait with the status of the client as argument.
     *
     * @param status the status of the client
     */
    public MessageInWait(StatusClient status) {
        this.status = status;
    }

    /**
     * Gets the type of the message.
     *
     * @return the type IN_WAIT.
     */
    @Override
    public TypeMessageSkyjo getType() {
        return TypeMessageSkyjo.IN_WAIT;
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
