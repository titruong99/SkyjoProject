package g54900.skyjo.message.client;

import g54900.skyjo.message.model.MessageSkyjo;
import g54900.skyjo.message.model.TypeMessageSkyjo;

/**
 * Class MessageExit represents the message sent by a client to the server when
 * a player is in the menu of skyjo and decides to leave.
 *
 * @author timmy
 */
public class MessageExit implements MessageSkyjo {

    private boolean exit;

    /**
     * Constructor of MessageExit with a boolan as argument saying if a player
     * wants to leave or not.
     *
     * @param exit true if the client wants to leave.
     */
    public MessageExit(boolean exit) {
        this.exit = exit;
    }

    /**
     * Gets the type of message.
     *
     * @return the type EXIT.
     */
    @Override
    public TypeMessageSkyjo getType() {
        return TypeMessageSkyjo.EXIT;
    }

    /**
     * Gets the content of the message.
     *
     * @return true if the client wants to leave.
     */
    @Override
    public Object getContent() {
        return exit;
    }
}
