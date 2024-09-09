package g54900.skyjo.message.server;

import g54900.skyjo.message.model.MessageSkyjo;
import g54900.skyjo.message.model.TypeMessageSkyjo;
import java.util.ArrayList;
import java.util.List;

/**
 * Class MessageDataDB represents the message sent by the server to a client
 * with the data of the last games and previous players.
 *
 * @author timmy
 */
public class MessageDataDB implements MessageSkyjo {

    List<List<Object>> dbs;

    /**
     * Constructor of MessageDataDB with the list with the different data of the
     * last games and players as argument.
     *
     * @param dataPlayers the data of the players in the db
     * @param dataGames the data of the games in the db
     */
    public MessageDataDB(List<Object> dataPlayers, List<Object> dataGames) {
        dbs = new ArrayList<>();
        dbs.add(dataPlayers);
        dbs.add(dataGames);
    }

    /**
     * Gets the type of the message.
     *
     * @return the type DATA_DB.
     */
    @Override
    public TypeMessageSkyjo getType() {
        return TypeMessageSkyjo.DATA_DB;
    }

    /**
     * Gets the content of the message.
     *
     * @return a list of lists: one for the data of the players and one for the
     * data of the games.
     */
    @Override
    public Object getContent() {
        return dbs;
    }
}
