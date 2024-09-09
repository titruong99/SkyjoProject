package g54900.skyjo.jdbc.db;

import g54900.skyjo.jdbc.dto.GameSkyjoDto;
import g54900.skyjo.jdbc.exception.DbException;
import g54900.skyjo.jdbc.exception.DtoException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class GameSkyjoDB has the interactions with the DB Game.
 *
 * @author timmy
 */
public class GameSkyjoDB {

    /**
     * Gets the games in the DB Game.
     *
     * @return the games of the DB Game.
     * @throws DtoException
     * @throws DbException
     * @throws SQLException
     */
    public static List<GameSkyjoDto> getAllGames() throws DtoException,
            DbException, SQLException {
        List<GameSkyjoDto> games = new ArrayList<>();
        java.sql.Connection connexion = DBManager.getConnection();
        java.sql.PreparedStatement update;
        String sql = "SELECT id,player1,player2,scorePlayer1,"
                + "scorePlayer2,winner "
                + "FROM Game ORDER BY id";
        update = connexion.prepareStatement(sql);

        var result = update.executeQuery();
        while (result.next()) {
            games.add(new GameSkyjoDto(result.getInt(1), result.getInt(2),
                    result.getInt(3), result.getInt(4), result.getInt(5),
                    result.getInt(6)));
        }
        return games;
    }

    /**
     * Updates a game in the DB Game.
     *
     * @param game the game to update
     * @throws DbException
     */
    public static void updateDb(GameSkyjoDto game) throws DbException {
        try {
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement update;
            String sql = "Update Player set "
                    + "player1=?, "
                    + "player2=? "
                    + "scorePlayer1=?"
                    + "scorePlayer2=?"
                    + "winner=?"
                    + "where id=?";
            update = connexion.prepareStatement(sql);
            update.setInt(1, game.getPlayer1());
            update.setInt(2, game.getPlayer2());
            update.setInt(3, game.getScorePlayer1());
            update.setInt(4, game.getScorePlayer2());
            update.setInt(5, game.getWinner());
            update.setInt(6, game.getId());
            update.executeUpdate();
        } catch (Exception ex) {
            throw new DbException("modification impossible:\n"
                    + ex.getMessage());
        }
    }

    /**
     * Deletes a game in the DB Game.
     *
     * @param id the id of the game
     * @throws DbException
     */
    public static void deleteDb(int id) throws DbException {
        try {
            java.sql.Statement stmt = DBManager.getConnection().
                    createStatement();
            stmt.execute("delete from Game where id=" + id);
        } catch (Exception ex) {
            throw new DbException("suppression impossible\n" + ex.getMessage());
        }
    }

    /**
     * Inserts a game in the DB Game.
     *
     * @param game the game to insert
     * @return the index of the game to insert.
     * @throws DbException
     */
    public static int insertDb(GameSkyjoDto game) throws DbException {
        try {
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement insert;
            insert = connexion.prepareStatement(
                    "Insert into Game(id,player1,player2,scorePlayer1,"
                    + "scorePlayer2,winner) "
                    + "values(?, ?, ?, ?, ?,?)");
            insert.setInt(1, game.getId());
            insert.setInt(2, game.getPlayer1());
            insert.setInt(3, game.getPlayer2());
            insert.setInt(4, game.getScorePlayer1());
            insert.setInt(5, game.getScorePlayer2());
            insert.setInt(6, game.getWinner());
            insert.executeUpdate();
        } catch (Exception ex) {
            throw new DbException("ajout impossible\n" + ex.getMessage());
        }
        return game.getId();
    }
}
