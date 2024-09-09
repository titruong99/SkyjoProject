package g54900.skyjo.message.client;

import g54900.skyjo.message.model.MessageSkyjo;
import g54900.skyjo.message.model.TypeMessageSkyjo;

/**
 * Class MessageEndGame represents the message sent by a client to the server to
 * say that the game is over.
 *
 * @author timmy
 */
public class MessageEndGame implements MessageSkyjo {

    String name;

    /**
     * Constructor of MessageEndGame with the name of the player as argument.
     *
     * @param name the name of the player
     */
    public MessageEndGame(String name) {
        this.name = name;
    }

    /**
     * Gets the type of the message.
     *
     * @return the type ENDGAME.
     */
    @Override
    public TypeMessageSkyjo getType() {
        return TypeMessageSkyjo.ENDGAME;
    }

    /**
     * Gets the content of the message.
     *
     * @return the name of the player.
     */
    @Override
    public Object getContent() {
        return name;
    }
}
