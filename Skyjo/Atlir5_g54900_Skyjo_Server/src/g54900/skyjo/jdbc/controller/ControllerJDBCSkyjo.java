package g54900.skyjo.jdbc.controller;

import g54900.skyjo.jdbc.business.GameSkyjoFaçade;
import g54900.skyjo.jdbc.dto.GameSkyjoDto;
import g54900.skyjo.jdbc.dto.PlayerSkyjoDto;
import g54900.skyjo.jdbc.exception.BusinessException;
import g54900.skyjo.jdbc.view.ViewSkyjo;
import java.util.List;
import g54900.skyjo.jdbc.business.PlayerSkyjoFaçade;

/**
 * Controller makes the link with the view and the Façades.
 *
 * @author timmy
 */
public class ControllerJDBCSkyjo {

    private PlayerSkyjoFaçade player;
    private GameSkyjoFaçade game;
    private ViewSkyjo view;

    public ControllerJDBCSkyjo(GameSkyjoFaçade game, PlayerSkyjoFaçade player,
            ViewSkyjo view) throws BusinessException {
        this.game = game;
        this.player = player;
        this.view = view;
    }

    public ControllerJDBCSkyjo(ViewSkyjo view, PlayerSkyjoFaçade player,
            List<Object> dataPlayer) throws BusinessException {
        this.view = view;
        this.player = player;
    }

    public ControllerJDBCSkyjo(ViewSkyjo view, GameSkyjoFaçade game,
            List<Integer> dataGame) throws BusinessException {
        this.view = view;
        this.game = game;
    }

    /**
     * Shows the different games and players in DB Game and Player.
     *
     * @throws BusinessException
     */
    public void showAllDB() throws BusinessException {
        List<GameSkyjoDto> games = game.getGames();
        List<PlayerSkyjoDto> players = player.getPlayers();
        view.displayAllGames(games);
        view.displayAllPlayers(players);
    }

    /**
     * Inserts a game in DB Game.
     *
     * @param dataGame the data of the game to insert
     * @throws BusinessException
     */
    public void insertGame(List<Integer> dataGame) throws BusinessException {
        game.addGame(dataGame.get(0), dataGame.get(1), dataGame.get(2),
                dataGame.get(3), dataGame.get(4), dataGame.get(5));
    }

    /**
     * Inserts a player in the DB Player.
     *
     * @param dataPlayer the data of the player to insert
     * @throws BusinessException
     */
    public void insertPlayer(List<Object> dataPlayer)
            throws BusinessException {
        player.addPlayer((Integer) dataPlayer.get(0),
                dataPlayer.get(1).toString());
    }

    /**
     * Gets the games in the DB Game.
     *
     * @return the games in the DB Game.
     * @throws BusinessException
     */
    public List<GameSkyjoDto> getGamesDB() throws BusinessException {
        List<GameSkyjoDto> games = game.getGames();
        return games;
    }

    /**
     * Gets the players in the DB Player.
     *
     * @return the players in the DB Player.
     * @throws BusinessException
     */
    public List<PlayerSkyjoDto> getPlayersDB() throws BusinessException {
        List<PlayerSkyjoDto> players = player.getPlayers();
        return players;
    }
}
