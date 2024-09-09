package g54900.skyjo.jdbc.business;

import g54900.skyjo.jdbc.db.GameSkyjoDB;
import g54900.skyjo.jdbc.dto.GameSkyjoDto;
import g54900.skyjo.jdbc.exception.DbException;
import g54900.skyjo.jdbc.exception.DtoException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author timmy
 */
public class GameSkyjoBusinessLogic {

    /**
     * Gets the different games of the DB Game.
     *
     * @return the different games in the DB Game.
     * @throws DbException
     * @throws DtoException
     * @throws SQLException
     */
    static List<GameSkyjoDto> selectAll() throws DbException, DtoException,
            SQLException {
        return GameSkyjoDB.getAllGames();
    }

    /**
     * Adds a game to the DB Game.
     *
     * @param game the game to add
     *
     * @return the index of the game.
     * @throws DbException
     */
    static int add(GameSkyjoDto game) throws DbException {
        return GameSkyjoDB.insertDb(game);
    }

    /**
     * Deletes a game in the DB Game according to his id.
     *
     * @param id the id of the game to delete
     * @throws DbException
     */
    static void delete(int id) throws DbException {
        GameSkyjoDB.deleteDb(id);
    }

    /**
     * Updates a game in the DB Game.
     *
     * @param game the game to update.
     * @throws DbException
     */
    static void update(GameSkyjoDto game) throws DbException {
        GameSkyjoDB.updateDb(game);
    }
}
