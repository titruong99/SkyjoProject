package g54900.skyjo.jdbc;

import g54900.skyjo.jdbc.business.GameSkyjoFaçade;
import g54900.skyjo.jdbc.business.GameSkyjoModel;
import g54900.skyjo.jdbc.business.PlayerSkyjoModel;
import g54900.skyjo.jdbc.exception.BusinessException;
import g54900.skyjo.jdbc.controller.ControllerJDBCSkyjo;
import g54900.skyjo.jdbc.view.ViewSkyjo;
import java.util.List;
import g54900.skyjo.jdbc.business.PlayerSkyjoFaçade;
import g54900.skyjo.jdbc.exception.DbException;
import java.util.ArrayList;

/**
 * This code was totally inspired from the lesson ATLIR5. I modify it for my own
 * needs. The <code> DemoSkyjo </code> runs jdbc demoSkyjo. It instanciates the
 * models, the view and the controller.
 */
public final class DemoSkyjo {

    private List<Object> dataPlayers;
    private List<Integer> dataGame;
    private ControllerJDBCSkyjo controller;

    /**
     * Main used to show the last players and the last games stored in the DBS.
     * Easier way to show in the server.
     *
     * @param args
     * @throws BusinessException
     */
    public static void main(String[] args) throws BusinessException, DbException {
        GameSkyjoFaçade model = new GameSkyjoModel();
        PlayerSkyjoFaçade model2 = new PlayerSkyjoModel();
        ViewSkyjo view = new ViewSkyjo(model, model2);
        ControllerJDBCSkyjo controller = new ControllerJDBCSkyjo(model,
                model2, view);
        controller.showAllDB();
    }

    /**
     * Constructor of DemoSkyjo without arguments. This constructor is used when
     * it is needed to send the different data of the last players and the last
     * games to a client.
     *
     * @throws BusinessException
     */
    public DemoSkyjo() throws BusinessException {
        GameSkyjoFaçade model = new GameSkyjoModel();
        PlayerSkyjoFaçade model2 = new PlayerSkyjoModel();
        ViewSkyjo view = new ViewSkyjo(model, model2);
        controller = new ControllerJDBCSkyjo(model, model2, view);
    }

    /**
     * Constructor of DemoSkyjo with a list with the informations of the game to
     * insert in the DB Game. This constructor is used when a game needs to be
     * inserted.
     *
     * @param dataGame the informations of the game to insert
     * @throws BusinessException
     */
    public DemoSkyjo(List<Integer> dataGame) throws BusinessException {
        GameSkyjoFaçade game = new GameSkyjoModel();
        this.dataGame = dataGame;
        controller = new ControllerJDBCSkyjo(new ViewSkyjo(game), game,
                dataGame);
        controller.insertGame(this.dataGame);
    }

    /**
     * Constructor of DemoSkyjo with the id and the name of the player to insert
     * in the DB Player. This constructor is specially used when a player need
     * to be inserted.
     *
     * @param id id of the player
     * @param name name of the player
     * @throws BusinessException
     */
    public DemoSkyjo(int id, String name) throws BusinessException {
        PlayerSkyjoFaçade player = new PlayerSkyjoModel();
        dataPlayers = new ArrayList<>();
        dataPlayers.add(id);
        dataPlayers.add(name);
        controller = new ControllerJDBCSkyjo(new ViewSkyjo(player), player,
                dataPlayers);
        controller.insertPlayer(dataPlayers);
    }

    /**
     * Gets the controller.
     *
     * @return the controller.
     */
    public ControllerJDBCSkyjo getController() {
        return controller;
    }
}
