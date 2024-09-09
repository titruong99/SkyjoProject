package g54900.skyjo.message.client;

import g54900.skyjo.message.model.MessageSkyjo;
import g54900.skyjo.message.model.TypeMessageSkyjo;

/**
 * Class MessageData represents the message sent by a client to the server
 * saying that the client wants to see the informations about the last games or
 * the previous players.
 *
 * @author timmy
 */
public class MessageData implements MessageSkyjo {

    private boolean data;

    /**
     * Constructor of MessageData with a boolean, saying if the client wants to
     * have a view about previous games or players, as argument.
     *
     * @param data true if the client wants the view
     */
    public MessageData(boolean data) {
        this.data = data;
    }

    /**
     * Gets the type of the message.
     *
     * @return the type DATA.
     */
    @Override
    public TypeMessageSkyjo getType() {
        return TypeMessageSkyjo.DATA;
    }

    /**
     * Gets the content of the message.
     *
     * @return true if the client wants the view.
     */
    @Override
    public Object getContent() {
        return data;
    }
}
