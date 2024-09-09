package g54900.skyjo.jdbc.business;

import g54900.skyjo.jdbc.db.DBManager;
import g54900.skyjo.jdbc.dto.PlayerSkyjoDto;
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
 */
public class PlayerSkyjoModel implements PlayerSkyjoFaçade {

    /**
     * Returns all the players in the DB Player.
     *
     * @return the players in the DB Player.
     * @throws BusinessException
     */
    @Override
    public List<PlayerSkyjoDto> getPlayers() throws BusinessException {
        List<PlayerSkyjoDto> players = new ArrayList<>();
        try {
            DBManager.startTransaction();
            players = PlayerSkyjoBusinessLogic.selectAll();
            DBManager.validateTransaction();

        } catch (DbException eDB) {
            String msg = eDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new BusinessException("Liste des Players inaccessible! \n"
                        + msg);
            }
        } catch (DtoException ex) {
            Logger.getLogger(PlayerSkyjoModel.class.getName()).log(Level.SEVERE,
                    null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PlayerSkyjoModel.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        return players;
    }

    /**
     *
     * Adds a player in the DB Player.
     *
     * @param id the id of the player to add
     * @param name the name of the player to add
     * @return the id of the player to add.
     * @throws BusinessException
     */
    @Override
    public int addPlayer(int id, String name) throws BusinessException {
        try {
            DBManager.startTransaction();
            PlayerSkyjoDto player = new PlayerSkyjoDto(id, name);
            int idPlayer = PlayerSkyjoBusinessLogic.add(player);
            DBManager.validateTransaction();
            return idPlayer;
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
     * Removes a player of the DB Player.
     *
     * @param player the player to remove
     * @throws BusinessException
     */
    @Override
    public void removePlayer(PlayerSkyjoDto player) throws BusinessException {
        try {
            DBManager.startTransaction();
            PlayerSkyjoBusinessLogic.delete(player.getId());
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
     * Updates a player in the DB Player.
     *
     * @param player the player to update
     * @throws BusinessException
     */
    @Override
    public void updatePlayer(PlayerSkyjoDto player) throws BusinessException {
        try {
            DBManager.startTransaction();
            PlayerSkyjoBusinessLogic.update(player);
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
