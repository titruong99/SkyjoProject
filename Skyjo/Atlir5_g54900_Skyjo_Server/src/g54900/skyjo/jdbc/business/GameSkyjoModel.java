package g54900.skyjo.jdbc.business;

import g54900.skyjo.jdbc.db.DBManager;
import g54900.skyjo.jdbc.dto.GameSkyjoDto;
import g54900.skyjo.jdbc.exception.BusinessException;
import g54900.skyjo.jdbc.exception.DbException;
import g54900.skyjo.jdbc.exception.DtoException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author timmy
 */
public class GameSkyjoModel implements GameSkyjoFaçade {

    /**
     * Gets the different games of the DB Game.
     *
     * @return the games of the DB Game.
     * @throws BusinessException
     */
    @Override
    public List<GameSkyjoDto> getGames() throws BusinessException {
        List<GameSkyjoDto> games = new ArrayList<>();
        try {
            DBManager.startTransaction();
            games = GameSkyjoBusinessLogic.selectAll();
            DBManager.validateTransaction();

        } catch (DbException eDB) {
            String msg = eDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new BusinessException(
                        "Liste des Games inaccessible! \n" + msg);
            }
        } catch (DtoException ex) {
            Logger.getLogger(GameSkyjoModel.class.getName()).
                    log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GameSkyjoModel.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        return games;
    }

    /**
     * Adds a game in the DB Game with all the infomrations needed.
     *
     * @param id id of the game
     * @param player1 id of the player1
     * @param player2 id of the player2
     * @param scorePlayer1 score of the player1
     * @param scorePlayer2 score of the player2
     * @param winner id of the winner
     * @return the index of the added game
     * @throws BusinessException
     */
    @Override
    public int addGame(int id, int player1, int player2, int scorePlayer1,
            int scorePlayer2, int winner) throws BusinessException {
        try {
            DBManager.startTransaction();
            GameSkyjoDto game = new GameSkyjoDto(id, player1, player2,
                    scorePlayer1, scorePlayer2, winner);
            int idGame = GameSkyjoBusinessLogic.add(game);
            DBManager.validateTransaction();
            return idGame;
        } catch (DbException ex) {
            try {
                DBManager.cancelTransaction();
                throw new BusinessException(ex.getMessage());
            } catch (DbException ex1) {
                throw new BusinessException(ex1.getMessage());
            }
        }
    }

    /**
     * Removes a game from the DB Game.
     *
     * @param game the game to remove
     * @throws BusinessException
     */
    @Override
    public void removeGame(GameSkyjoDto game) throws BusinessException {
        try {
            DBManager.startTransaction();
            GameSkyjoBusinessLogic.delete(game.getId());
            DBManager.validateTransaction();
        } catch (DbException eDB) {
            String msg = eDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new BusinessException("Suppression de User impossible! \n"
                        + msg);
            }
        }
    }

    /**
     * Updates a game in the DB Game.
     *
     * @param game the game to update
     * @throws BusinessException
     */
    @Override
    public void updateGame(GameSkyjoDto game) throws BusinessException {
        try {
            DBManager.startTransaction();
            GameSkyjoBusinessLogic.update(game);
            DBManager.validateTransaction();
        } catch (DbException eDB) {
            String msg = eDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new BusinessException("Mise à jour de User impossible! \n"
                        + msg);
            }
        }
    }

}
