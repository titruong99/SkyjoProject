package g54900.skyjo.jdbc.business;

import g54900.skyjo.jdbc.dto.GameSkyjoDto;
import g54900.skyjo.jdbc.exception.BusinessException;
import java.util.List;

/**
 *
 * @author timmy
 */
public interface GameSkyjoFaçade {

    /**
     * Gets the different games of the DB Game.
     *
     * @return the games of the DB Game.
     * @throws BusinessException
     */
    List<GameSkyjoDto> getGames() throws BusinessException;

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
    int addGame(int id, int player1, int player2, int scorePlayer1,
            int scorePlayer2, int winner) throws BusinessException;

    /**
     * Removes a game from the DB Game.
     *
     * @param game the game to remove
     * @throws BusinessException
     */
    void removeGame(GameSkyjoDto game) throws BusinessException;

    /**
     * Updates a game in the DB Game.
     *
     * @param game the game to update
     * @throws BusinessException
     */
    void updateGame(GameSkyjoDto game) throws BusinessException;
}
