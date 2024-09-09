package g54900.skyjo.jdbc.business;

import g54900.skyjo.jdbc.dto.PlayerSkyjoDto;
import g54900.skyjo.jdbc.exception.BusinessException;
import java.util.List;

/**
 *
 */
public interface PlayerSkyjoFaçade {

    /**
     * Returns all the players in the DB Player.
     *
     * @return the players in the DB Player.
     * @throws BusinessException
     */
    List<PlayerSkyjoDto> getPlayers() throws BusinessException;

    /**
     * Adds a player in the DB Player.
     *
     * @param id the id of the player to add
     * @param name the name of the player to add
     * @return the id of the player to add.
     * @throws BusinessException
     */
    int addPlayer(int id, String name) throws BusinessException;

    /**
     * Removes a player of the DB Player.
     *
     * @param player the player to remove
     * @throws BusinessException
     */
    void removePlayer(PlayerSkyjoDto player) throws BusinessException;

    /**
     * Updates a player in the DB Player.
     *
     * @param player the player to update
     * @throws BusinessException
     */
    void updatePlayer(PlayerSkyjoDto player) throws BusinessException;

}
