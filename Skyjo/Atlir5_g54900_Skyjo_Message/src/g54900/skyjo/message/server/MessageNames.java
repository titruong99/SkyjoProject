package g54900.skyjo.message.server;

import g54900.skyjo.message.model.MessageSkyjo;
import g54900.skyjo.message.model.TypeMessageSkyjo;
import java.util.List;

/**
 * Class MessageNames represents the message sent by the server to a client
 * telling him the names of the different players for the display of the game.
 *
 * @author timmy
 */
public class MessageNames implements MessageSkyjo {

    private List<String> names;

    /**
     * Constructor of MessageNames with the list of names as argument.
     *
     * @param names the names of the players.
     */
    public MessageNames(List<String> names) {
        this.names = names;
    }

    /**
     * Gets the type of the message.
     *
     * @return the type NAMES.
     */
    @Override
    public TypeMessageSkyjo getType() {
        return TypeMessageSkyjo.NAMES;
    }

    /**
     * Gets the content of the message.
     *
     * @return the list of names of players.
     */
    @Override
    public Object getContent() {
        return names;
    }
}
