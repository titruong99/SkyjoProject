package g54900.skyjo.server.model;

import g54900.skyjo.jdbc.DemoSkyjo;
import g54900.skyjo.jdbc.db.GameSkyjoDB;
import g54900.skyjo.jdbc.db.PlayerSkyjoDB;
import g54900.skyjo.jdbc.dto.GameSkyjoDto;
import g54900.skyjo.jdbc.dto.PlayerSkyjoDto;
import g54900.skyjo.jdbc.exception.BusinessException;
import g54900.skyjo.jdbc.exception.DbException;
import g54900.skyjo.jdbc.exception.DtoException;
import g54900.skyjo.message.server.MessageDataDB;
import g54900.skyjo.message.server.MessageInGame;
import g54900.skyjo.message.server.MessageInWait;
import g54900.skyjo.message.server.MessageInformationsGame;
import g54900.skyjo.message.server.MessageNames;
import g54900.skyjo.message.model.MessageSkyjo;
import g54900.skyjo.message.model.StatusClient;
import g54900.skyjo.message.model.TypeMessageSkyjo;
import g54900.skyjo.model.Game;
import g54900.skyjo.server.AbstractServer;
import g54900.skyjo.server.ConnectionToClient;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class ServerSkyjo represents the server of the game Skyjo on which the
 * different clients can connect if they want to play to Skyjo.
 *
 * @author timmy
 */
public class ServerSkyjo extends AbstractServer {

    private static int startNbPlayers = 0;
    private static int first = 0;
    private static int second = 1;

    private int indexClient;
    private int indexGame;
    private List<Game> games;
    private List<String> namesPlayers;
    private int nbMaxPlayers = startNbPlayers;

    public static void main(String[] args) throws IOException, DtoException,
            DbException, SQLException {
        ServerSkyjo server = new ServerSkyjo(12345);
        System.out.println("Server Started");
    }

    /**
     * Constructor of ServerSkyjo with a port to connect as argument
     *
     * @param port the port to connect to
     * @throws IOException
     * @throws DtoException
     * @throws DbException
     * @throws SQLException
     */
    public ServerSkyjo(int port) throws IOException, DtoException, DbException,
            SQLException {
        super(port);
        indexGame = getLastIndexGameDB();
        indexClient = getNextIndexClientOrPlayerDB();
        games = new ArrayList<>();
        namesPlayers = new ArrayList<>();
        this.listen();
    }

    @Override
    protected void handleMessageFromClient(Object msg,
            ConnectionToClient client) {
        MessageSkyjo message = (MessageSkyjo) msg;
        TypeMessageSkyjo type = message.getType();
        switch (type) {
            case READY_TO_PLAY: 
                try {
                handlePlayersReadyToPlay(message, client);
            } catch (IOException ex) {
                Logger.getLogger(ServerSkyjo.class.getName()).log(Level.SEVERE, null, ex);
            }
            break;
            case DATA: 
                try {
                sendDataToClient(client);
            } catch (IOException ex) {
                Logger.getLogger(ServerSkyjo.class.getName()).
                        log(Level.SEVERE, null, ex);
            } catch (DbException ex) {
                Logger.getLogger(ServerSkyjo.class.getName()).
                        log(Level.SEVERE, null, ex);
            } catch (BusinessException ex) {
                Logger.getLogger(ServerSkyjo.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
            break;
            case EXIT:
                clientDisconnected(client);
                break;
            case HIT_CARD:
                handleHitCard(client);
                break;
            case EXCHANGE_DISCARD_CARD:
                handleExchangeDiscardCard(message, client);
                break;
            case DROP_HIT_CARD:
                handleDropHitCard(message, client);
                break;
            case KEEP_HIT_CARD:
                handleKeepHitCard(message, client);
                break;
            case ENDGAME:
                try {
                putInDataBase(client, getGame(client.getIndexGame()), message);
            } catch (BusinessException ex) {
                Logger.getLogger(ServerSkyjo.class.getName()).
                        log(Level.SEVERE, null, ex);
            } catch (DtoException ex) {
                Logger.getLogger(ServerSkyjo.class.getName()).
                        log(Level.SEVERE, null, ex);
            } catch (DbException ex) {
                Logger.getLogger(ServerSkyjo.class.getName()).
                        log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ServerSkyjo.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
            break;
        }
    }

    /**
     * Private method that handles the message of type READY_TO_PLAY.
     *
     * @param message the message sent by the client
     * @param client the client who sent the message
     */
    private void handlePlayersReadyToPlay(MessageSkyjo message,
            ConnectionToClient client) throws IOException {
        if (nbMaxPlayers == startNbPlayers) {
            indexGame++;
        }
        threadsInGame.add(client);
        threads.remove(client);
        namesPlayers.add(message.getContent().toString());
        nbMaxPlayers++;
        client.setIndexGame(indexGame);
        if (nbMaxPlayers == 1) {
            try {
                client.sendToClient(new MessageInWait(
                        StatusClient.IN_WAIT_OF_GAME));
            } catch (IOException ex) {
                Logger.getLogger(ServerSkyjo.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        } else {
            client.sendToClient(new MessageInGame(StatusClient.IN_GAME));
            createGame();
        }

    }

    /**
     * Private method that creates a game with two players and send the
     * informations to each player.
     */
    private void createGame() {
        startGame();
        nbMaxPlayers = startNbPlayers;
        for (Thread thread : threadsInGame) {
            ConnectionToClient c = (ConnectionToClient) thread;
            if (c.getIndexGame() == indexGame) {
                try {
                    c.setIndexClient(indexClient);
                    indexClient++;
                    c.sendToClient(new MessageNames(namesPlayers));
                    c.sendToClient(new MessageInGame(StatusClient.IN_GAME));
                    c.sendToClient(new MessageInformationsGame(games.get(
                            games.size() - 1).getInformationsGame()));
                } catch (IOException ex) {
                    Logger.getLogger(ServerSkyjo.class.getName()).
                            log(Level.SEVERE, null, ex);
                }
            }
        }
        namesPlayers.clear();
    }

    /**
     * Private method that handles the message of type HIT_CARD.
     *
     * @param client the client who sent the message
     */
    private void handleHitCard(ConnectionToClient client) {
        Game game = getGame(client.getIndexGame());
        game.hitCard();
        game.putInformationsGame();
        {
            try {
                for (Thread thread : threadsInGame) {
                    ConnectionToClient c = (ConnectionToClient) thread;
                    if (c.getIndexGame() == game.getIndex()) {
                        c.sendToClient(new MessageInformationsGame(game.
                                getInformationsGame()));
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(ServerSkyjo.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Private method that handles the message of type EXCHANGE_DISCARD_CARD.
     *
     * @param message the message sent by the client
     * @param client the client who sent the message
     */
    private void handleExchangeDiscardCard(MessageSkyjo message,
            ConnectionToClient client) {
        Game game = getGame(client.getIndexGame());
        List<Integer> coordinates = (List<Integer>) message.getContent();
        int row = coordinates.get(first);
        int col = coordinates.get(second);
        game.exchangeWithDiscardCard(row, col);
        game.putInformationsGame();
        {
            try {
                for (Thread thread : threadsInGame) {
                    ConnectionToClient c = (ConnectionToClient) thread;
                    if (c.getIndexGame() == game.getIndex()) {
                        c.sendToClient(new MessageInformationsGame(
                                game.getInformationsGame()));
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(ServerSkyjo.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Private method that handles the message of type DROP_HIT_CARD.
     *
     * @param message the message sent by the client
     * @param client the client who sent the message
     */
    private void handleDropHitCard(MessageSkyjo message,
            ConnectionToClient client) {
        Game game = getGame(client.getIndexGame());
        List<Integer> coordinates = (List<Integer>) message.getContent();
        int row = coordinates.get(first);
        int col = coordinates.get(second);
        game.dropHitCard(row, col);
        game.putInformationsGame();
        {
            try {
                for (Thread thread : threadsInGame) {
                    ConnectionToClient c = (ConnectionToClient) thread;
                    if (c.getIndexGame() == game.getIndex()) {
                        c.sendToClient(new MessageInformationsGame(game.
                                getInformationsGame()));
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(ServerSkyjo.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Private method that handles the message of type KEEP_HIT_CARD.
     *
     * @param message the message sent by the client
     * @param client the client who sent the message
     */
    private void handleKeepHitCard(MessageSkyjo message,
            ConnectionToClient client) {
        Game game = getGame(client.getIndexGame());
        List<Integer> coordinates = (List<Integer>) message.getContent();
        int row = coordinates.get(first);
        int col = coordinates.get(second);
        game.keepHitCard(row, col);
        game.putInformationsGame();
        {
            try {
                for (Thread thread : threadsInGame) {
                    ConnectionToClient c = (ConnectionToClient) thread;
                    if (c.getIndexGame() == game.getIndex()) {
                        c.sendToClient(new MessageInformationsGame(game.
                                getInformationsGame()));
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(ServerSkyjo.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Puts different data of the players and the game in the data base.
     *
     * @param game the current game
     * @param name the name of the player
     * @throws BusinessException
     */
    private void putInDataBase(ConnectionToClient client, Game game,
            MessageSkyjo message) throws BusinessException, DtoException,
            DbException, SQLException {
        putPlayerInDB(client, message.getContent().toString());
        if (getLastIndexGameDB() != game.getIndex()) {
            List<Integer> dataGame = new ArrayList<>();
            int winner;
            dataGame.add(game.getIndex());
            for (Thread thread : threadsInGame) {
                ConnectionToClient c = (ConnectionToClient) thread;
                if (c.getIndexGame() == game.getIndex()) {
                    dataGame.add(c.getIndexClient());
                }
            }
            dataGame.add(game.getInformationsGame().get(8));
            dataGame.add(game.getInformationsGame().get(9));
            if (game.getInformationsGame().get(0) == 0) {
                winner = dataGame.get(1);
            } else {
                winner = dataGame.get(2);
            }
            dataGame.add(winner);
            DemoSkyjo demo = new DemoSkyjo(dataGame);
        }
    }

    /**
     * Private method that puts a new player in the DB Player.
     *
     * @param client the player to add in the DB Player
     * @param name the name of the player
     * @throws BusinessException
     */
    private void putPlayerInDB(ConnectionToClient client, String name)
            throws BusinessException {
        DemoSkyjo demo = new DemoSkyjo(client.getIndexClient(), name);
    }

    /**
     * Private method that creates the different lists with the data of the last
     * players and last games and send it to the client.
     *
     * @param client the client to send the data
     * @throws IOException
     * @throws DbException
     * @throws BusinessException
     */
    private void sendDataToClient(ConnectionToClient client) throws
            IOException, DbException, BusinessException {
        DemoSkyjo demo = new DemoSkyjo();
        List<Object> dataGames = new ArrayList<>();
        List<Object> dataPlayers = new ArrayList<>();
        for (GameSkyjoDto gameSkyjoDto : demo.getController().getGamesDB()) {
            dataGames.add(gameSkyjoDto.getId());
            dataGames.add(gameSkyjoDto.getPlayer1());
            dataGames.add(gameSkyjoDto.getPlayer2());
            dataGames.add(gameSkyjoDto.getScorePlayer1());
            dataGames.add(gameSkyjoDto.getScorePlayer2());
            dataGames.add(gameSkyjoDto.getWinner());
        }
        for (PlayerSkyjoDto playerSkyjoDto : demo.getController().
                getPlayersDB()) {
            dataPlayers.add(playerSkyjoDto.getId());
            dataPlayers.add(playerSkyjoDto.getName());
        }
        client.sendToClient(new MessageDataDB(dataPlayers, dataGames));
    }

    @Override
    synchronized protected void clientDisconnected(
            ConnectionToClient client) {
        threads.remove(client);
    }

    /**
     * Private method that starts a game.
     */
    private void startGame() {
        Game game = new Game(indexGame);
        game.start();
        game.putInformationsGame();
        games.add(game);
    }

    /**
     * Private method that gets the game according to the index.
     *
     * @param index the index of a client which we search the associating game
     * @return the game associated to the given index.
     */
    private Game getGame(int index) {
        for (Game game : games) {
            if (game.getIndex() == index) {
                return game;
            }
        }
        return null;
    }

    /**
     * Private method that gets the last index of an added game in the data
     * base.
     *
     * @return the new index;
     * @throws DtoException
     * @throws DbException
     * @throws SQLException
     */
    private int getLastIndexGameDB() throws DtoException, DbException,
            SQLException {
        List<GameSkyjoDto> games = GameSkyjoDB.getAllGames();
        if (games.isEmpty()) {
            return 0;
        } else {
            return games.get(games.size() - 1).getId();
        }
    }

    /**
     * Private method that gets the next index after the last line added in the
     * player data base.
     *
     * @return the next index.
     * @throws DtoException
     * @throws DbException
     * @throws SQLException
     */
    private int getNextIndexClientOrPlayerDB() throws DtoException, DbException,
            SQLException {
        List<PlayerSkyjoDto> players = PlayerSkyjoDB.getAllPlayers();
        if (players.isEmpty()) {
            return 1;
        } else {
            return players.get(players.size() - 1).getId() + 1;
        }
    }
}
