package g54900.skyjo.jdbc.business;

import g54900.skyjo.jdbc.db.PlayerSkyjoDB;
import g54900.skyjo.jdbc.dto.PlayerSkyjoDto;
import g54900.skyjo.jdbc.exception.DbException;
import g54900.skyjo.jdbc.exception.DtoException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author timmy
 */
public class PlayerSkyjoBusinessLogic {

    /**
     * Gets the differents players in the DB Player.
     *
     * @return the players in the DB Player.
     * @throws DbException
     * @throws DtoException
     * @throws SQLException
     */
    static List<PlayerSkyjoDto> selectAll() throws DbException, DtoException,
            SQLException {
        return PlayerSkyjoDB.getAllPlayers();
    }

    /**
     * Adds a player in the DB Player.
     *
     * @param player the player to add
     * @return the index of the player added.
     * @throws DbException
     */
    static int add(PlayerSkyjoDto player) throws DbException {
        return PlayerSkyjoDB.insertDb(player);
    }

    /**
     * Deletes a player from the DB Player.
     *
     * @param id the id of the player
     * @throws DbException
     */
    static void delete(int id) throws DbException {
        PlayerSkyjoDB.deleteDb(id);
    }

    /**
     * Updates a player in the DB Player.
     *
     * @param player the player to update
     * @throws DbException
     */
    static void update(PlayerSkyjoDto player) throws DbException {
        PlayerSkyjoDB.updateDb(player);
    }
}
