package g54900.skyjo.client.view;

import g54900.skyjo.client.model.ClientSkyjo;
import g54900.skyjo.message.model.StatusClient;
import g54900.skyjo.utils.Observer;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Class ViewClientSkyjo represents the main class that observes a client and
 * creates and shows the view of the client according to the situation.
 *
 * @author timmy
 */
public class ViewClientSkyjo extends Application implements Observer {

    private ClientSkyjo client;
    private Stage stage;
    private ViewGameSkyjo view;

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Starts by creating a client and creates the display of the menu for the
     * client.
     *
     * @param stage the stage used for the display
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        stage.setResizable(false);
        client = new ClientSkyjo("localhost", 12345);
        client.registerObserver(this);
        this.stage = stage;
        ViewIntroductionMenuSkyjo startRoot
                = new ViewIntroductionMenuSkyjo(client, stage);
        Scene startScene = new Scene(startRoot, 530, 900);
        stage.setScene(startScene);
        stage.centerOnScreen();
        stage.show();
    }

    /**
     * updates the view of the client.
     */
    @Override
    public void update() {
        if (client.getStatus() == StatusClient.IN_MENU) {
            showDB();
        } else if (client.getStatus() == StatusClient.IN_WAIT_OF_GAME) {
            showViewWaitingWindow();
        } else if (client.getStatus() == StatusClient.IN_GAME
                && !client.getInformationsGame().isEmpty()) {
            try {
                showViewGame();
            } catch (IOException ex) {
                Logger.getLogger(ViewClientSkyjo.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        }

    }

    /**
     * Private method the creates the waiting window of the client and shows it.
     */
    private void showViewWaitingWindow() {
        Platform.runLater(() -> {
            try {
                Parent root = FXMLLoader.load(getClass().
                        getResource("WaitingWindow.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ViewClientSkyjo.class.getName())
                        .log(Level.SEVERE, null, ex);
            }

        });
    }

    /**
     * Private method that creates the view of the game for the client and shows
     * it.
     */
    private void showViewGame() throws IOException {
        view = new ViewGameSkyjo(client, client.getNamesPlayers().get(0),
                client.getNamesPlayers().get(1), client.getInformationsGame());
        Platform.runLater(() -> {
            Scene scene = new Scene(view.getRoot(), 940, 900);
            stage.setScene(scene);
            stage.show();
        });

    }

    /**
     * Private method that creates and shows the displays with the informations
     * about the last games and previous players to the client.
     */
    private void showDB() {
        Platform.runLater(() -> {
            createViewDBPlayer();
            createViewDBGame();
        });
    }

    /**
     * Creates the display with the informations of the last players.
     */
    private void createViewDBPlayer() {
        int index = 0;
        Stage dataPlayerStage = new Stage();
        dataPlayerStage.setTitle("PLAYER");
        ListView dataPlayer = new ListView();
        Label line = new Label();
        line.setMinWidth(250);
        line.setText("id                       name");
        line.setBackground(new Background(new BackgroundFill(
                Color.LIGHTGRAY, CornerRadii.EMPTY,
                Insets.EMPTY)));
        dataPlayer.getItems().add(line);
        while (index < client.getDataDBPlayer().size()) {
            line = new Label();
            line.setText(client.getDataDBPlayer().get(index) + "               "
                    + "          "
                    + client.getDataDBPlayer().get(index + 1));
            dataPlayer.getItems().addAll(line);
            index += 2;
        }
        Scene scene = new Scene(dataPlayer, 250, 300);
        dataPlayerStage.setScene(scene);
        dataPlayerStage.setResizable(false);
        dataPlayerStage.setX(840);
        dataPlayerStage.setY(100);
        dataPlayerStage.show();
    }

    /**
     * Creates the display with the informations of the last games.
     */
    private void createViewDBGame() {
        int index = 0;
        Stage dataGameStage = new Stage();
        dataGameStage.setTitle("GAME");
        ListView dataGame = new ListView();
        Label line = new Label();
        line.setMinWidth(450);
        line.setBackground(new Background(new BackgroundFill(
                Color.LIGHTGRAY, CornerRadii.EMPTY,
                Insets.EMPTY)));
        line.setText("id          player1          player2          "
                + "scorePlayer1          scorePlayer2          winner");
        dataGame.getItems().add(line);
        while (index < client.getDataDBGame().size()) {
            line = new Label();
            line.setText(client.getDataDBGame().get(index) + "               "
                    + client.getDataDBGame().get(index + 1) + "                "
                    + "    "
                    + client.getDataDBGame().get(index + 2) + "                "
                    + "       "
                    + client.getDataDBGame().get(index + 3) + "                "
                    + "           "
                    + client.getDataDBGame().get(index + 4) + "                "
                    + "       "
                    + client.getDataDBGame().get(index + 5));
            dataGame.getItems().addAll(line);
            index += 6;
        }
        Scene scene = new Scene(dataGame, 430, 300);
        dataGameStage.setScene(scene);
        dataGameStage.setResizable(false);
        dataGameStage.setX(750);
        dataGameStage.setY(500);
        dataGameStage.show();
    }
}
