package g54900.skyjo.client.model;

import g54900.skyjo.message.model.StatusClient;
import g54900.skyjo.client.AbstractClientSkyjo;
import g54900.skyjo.message.model.MessageSkyjo;
import g54900.skyjo.message.model.TypeMessageSkyjo;
import g54900.skyjo.utils.Observable;
import g54900.skyjo.utils.Observer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class ClientSkyjo represents a client/ player of the game Skyjo. A client can
 * connect to the server of Skyjo and plays a game against an other client. It
 * implements also Observable.
 *
 * @author timmy
 */
public class ClientSkyjo extends AbstractClientSkyjo implements Observable {

    private List<Observer> observers;
    private List<String> namesPlayers;
    private List<Integer> informationsGame;
    private List<Object> dataDBPlayer;
    private List<Object> dataDBGame;
    private StatusClient status;
    private int indexClient = -1;

    /**
     * Constructor of ClientSkyjo with the host and the port as arguments.
     *
     * @param host the server's host name.
     * @param port the port number.
     * @throws IOException
     */
    public ClientSkyjo(String host, int port) throws IOException {
        super(host, port);
        status = StatusClient.IN_MENU;
        observers = new ArrayList<>();
        namesPlayers = new ArrayList<>();
        informationsGame = new ArrayList<>();
        dataDBPlayer = new ArrayList<>();
        dataDBGame = new ArrayList<>();
        openConnection();
        System.out.println("client connected");
    }

    /**
     * Gets the status of a client.
     *
     * @return the status of the client.
     */
    public StatusClient getStatus() {
        return status;
    }

    /**
     * Gets the list of the name of the players.
     *
     * @return the names of the players.
     */
    public List<String> getNamesPlayers() {
        return namesPlayers;
    }

    /**
     * Gets the informations of the game.
     *
     * @return the informations of the game.
     */
    public List<Integer> getInformationsGame() {
        return informationsGame;
    }

    /**
     * Gets the data of the last players.
     *
     * @return the list with the different data of the players.
     */
    public List<Object> getDataDBPlayer() {
        return dataDBPlayer;
    }

    /**
     * Gets the data of the last games.
     *
     * @return the list with the different data of the games.
     */
    public List<Object> getDataDBGame() {
        return dataDBGame;
    }

    @Override
    protected void handleMessageFromServer(Object msg) {
        MessageSkyjo message = (MessageSkyjo) msg;
        TypeMessageSkyjo type = message.getType();
        switch (type) {
            case IN_WAIT:
                status = (StatusClient) message.getContent();
                indexClient = 0;
                break;
            case IN_GAME:
                if (indexClient == -1) {
                    indexClient = 1;
                }
                status = (StatusClient) message.getContent();
                break;
            case NAMES:
                namesPlayers.clear();
                namesPlayers = (List<String>) message.getContent();
                break;
            case DATA_DB:
                dataDBPlayer.clear();
                dataDBGame.clear();
                List<List<Object>> data
                        = (List<List<Object>>) message.getContent();
                dataDBPlayer = data.get(0);
                dataDBGame = data.get(1);
                break;
            case INFORMATIONS_GAME:
                informationsGame.clear();
                informationsGame = (List<Integer>) message.getContent();
                break;
        }
        notifyObservers();
    }

    /**
     * Gets the index of the client.
     *
     * @return the index of the client.
     */
    public int getIndexClient() {
        return indexClient;
    }

    /**
     * Adds an observer.
     *
     * @param obs the observer to add
     */
    @Override
    public void registerObserver(Observer obs) {
        if (!observers.contains(obs)) {
            observers.add(obs);
        }
    }

    /**
     * Removes an observer.
     *
     * @param obs the observer to delete
     */
    @Override
    public void removeObserver(Observer obs) {
        observers.remove(obs);
    }

    /**
     * Notifies the different observers of the modifications.
     */
    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
