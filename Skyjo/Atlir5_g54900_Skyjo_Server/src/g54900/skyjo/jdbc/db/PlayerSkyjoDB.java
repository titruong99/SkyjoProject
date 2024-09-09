package g54900.skyjo.jdbc.db;

import g54900.skyjo.jdbc.dto.PlayerSkyjoDto;
import g54900.skyjo.jdbc.exception.DbException;
import g54900.skyjo.jdbc.exception.DtoException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class PlayerSkyjoDB has the interactions with the DB Player.
 *
 * @author timmy
 */
public class PlayerSkyjoDB {

    /**
     * Gets the players in the DB Player.
     *
     * @return the players in the DB Player.
     * @throws DtoException
     * @throws DbException
     * @throws SQLException
     */
    public static List<PlayerSkyjoDto> getAllPlayers() throws DtoException,
            DbException, SQLException {
        List<PlayerSkyjoDto> players = new ArrayList<>();
        java.sql.Connection connexion = DBManager.getConnection();
        java.sql.PreparedStatement update;
        String sql = "SELECT id,name FROM Player ORDER BY id";
        update = connexion.prepareStatement(sql);
        var result = update.executeQuery();
        while (result.next()) {
            players.add(new PlayerSkyjoDto(result.getInt(1),
                    result.getString(2)));
        }
        return players;
    }

    /**
     * Updates the player in the DB Player.
     *
     * @param player the player to update
     * @throws DbException
     */
    public static void updateDb(PlayerSkyjoDto player) throws DbException {
        try {
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement update;
            String sql = "Update Player set "
                    + "name=?, "
                    + "where id=?";
            update = connexion.prepareStatement(sql);
            update.setString(1, player.getName());
            update.setInt(2, player.getId());
            update.executeUpdate();
        } catch (Exception ex) {
            throw new DbException("modification impossible:\n"
                    + ex.getMessage());
        }
    }

    /**
     * Deletes a player from the DB Player.
     *
     * @param id the id of the player to delete
     * @throws DbException
     */
    public static void deleteDb(int id) throws DbException {
        try {
            java.sql.Statement stmt = DBManager.getConnection().
                    createStatement();
            stmt.execute("delete from Player where id=" + id);
        } catch (Exception ex) {
            throw new DbException("suppression impossible\n" + ex.getMessage());
        }
    }

    /**
     * Inserts a player in the DB Player.
     *
     * @param player the player to insert in the DB Player.
     * @return the index of the player inserted.
     * @throws DbException
     */
    public static int insertDb(PlayerSkyjoDto player) throws DbException {
        try {
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement insert;
            insert = connexion.prepareStatement(
                    "Insert into Player(id,name) "
                    + "values(?, ?)");
            insert.setInt(1, player.getId());
            insert.setString(2, player.getName());
            insert.executeUpdate();
        } catch (Exception ex) {
            throw new DbException("ajout impossible\n" + ex.getMessage());
        }
        return player.getId();
    }
}
