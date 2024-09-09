package g54900.skyjo.message.server;

import g54900.skyjo.message.model.MessageSkyjo;
import g54900.skyjo.message.model.TypeMessageSkyjo;
import java.util.List;

/**
 * Class MessageInFormationsGame represents the message sent by the server to a
 * client giving the different informations about the game.
 *
 * @author timmy
 */
public class MessageInformationsGame implements MessageSkyjo {

    private List<Integer> infosGame;

    /**
     * Constructor of MessageInformationsGame with the list of informations
     * about the game as argument.
     *
     * @param infosGame the informations of the game
     */
    public MessageInformationsGame(List<Integer> infosGame) {
        this.infosGame = infosGame;
    }

    /**
     * Gets the type of the message.
     *
     * @return the type INFORMATIONS_GAME.
     */
    @Override
    public TypeMessageSkyjo getType() {
        return TypeMessageSkyjo.INFORMATIONS_GAME;
    }

    /**
     * Gets the content of the message.
     *
     * @return the list of informations of the game.
     */
    @Override
    public Object getContent() {
        return infosGame;
    }
}
