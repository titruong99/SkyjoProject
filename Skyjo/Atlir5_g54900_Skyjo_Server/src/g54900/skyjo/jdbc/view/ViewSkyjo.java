package g54900.skyjo.jdbc.view;

import g54900.skyjo.jdbc.business.GameSkyjoFaçade;
import g54900.skyjo.jdbc.business.PlayerSkyjoFaçade;
import g54900.skyjo.jdbc.dto.GameSkyjoDto;
import g54900.skyjo.jdbc.dto.PlayerSkyjoDto;
import java.util.List;

/**
 * Class ViewSkyjo represents a view of the games and players in the output .
 *
 * @author timmy
 */
public class ViewSkyjo {

    private GameSkyjoFaçade game;
    private PlayerSkyjoFaçade player;

    public ViewSkyjo(GameSkyjoFaçade game) {
        this.game = game;
    }

    public ViewSkyjo(PlayerSkyjoFaçade player) {
        this.player = player;
    }

    public ViewSkyjo(GameSkyjoFaçade game, PlayerSkyjoFaçade player) {
        this.game = game;
        this.player = player;
    }

    /**
     * Shows an introduction message.
     */
    public void displayWelcome() {
        System.out.println("\tBienvenue dans la db Skyjo\n");
    }

    /**
     * Shows all the games.
     *
     * @param games the different games to show
     */
    public void displayAllGames(List<GameSkyjoDto> games) {
        System.out.println("\tLa liste des games en DB est :\n");
        System.out.println("Id     |     Player1     |     Player2     |     "
                + "ScorePlayer1     |     ScorePlayer2     |     Winner");
        System.out.println("");
        for (GameSkyjoDto game : games) {
            System.out.print(game.getId() + "               ");
            System.out.print(game.getPlayer1() + "                 ");
            System.out.print(game.getPlayer2() + "                   ");
            System.out.print(game.getScorePlayer1() + "                    ");
            System.out.print(game.getScorePlayer2() + "                    ");
            System.out.print(game.getWinner());
            System.out.println("");
        }
        System.out.println("");
    }

    /**
     * Shows all the players.
     *
     * @param players the list of players to show
     */
    public void displayAllPlayers(List<PlayerSkyjoDto> players) {
        System.out.println("\tLa liste des players en DB est :\n");
        System.out.println("Id     |     Name    ");
        System.out.println("");
        for (PlayerSkyjoDto user : players) {
            System.out.print(user.getId() + "            ");
            System.out.print(user.getName() + "             ");
            System.out.println("");
        }
        System.out.println("");
    }
}
