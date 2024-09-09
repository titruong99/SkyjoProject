package g54900.skyjo.message.client;

import g54900.skyjo.message.model.MessageSkyjo;
import g54900.skyjo.message.model.TypeMessageSkyjo;

/**
 * Class MessageReadyToPlay represents the message sent by a client to the
 * server when a player wants to play a game.
 *
 * @author timmy
 */
public class MessageReadyToPlay implements MessageSkyjo {

    private String namePlayer;

    /**
     * Constructor of MessageReadyToPlay with the name of the player as
     * argument.
     *
     * @param namePlayer the name of the player
     */
    public MessageReadyToPlay(String namePlayer) {
        this.namePlayer = namePlayer;
    }

    /**
     * Gets the type of the message.
     *
     * @return the type READY_TO_PLAY.
     */
    @Override
    public TypeMessageSkyjo getType() {
        return TypeMessageSkyjo.READY_TO_PLAY;
    }

    /**
     * Gets the content of the message.
     *
     * @return the name of the player.
     */
    @Override
    public Object getContent() {
        return namePlayer;
    }
}
