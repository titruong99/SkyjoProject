package g54900.skyjo.client.view;

import g54900.skyjo.client.model.ClientSkyjo;
import g54900.skyjo.message.client.MessageExit;
import g54900.skyjo.message.client.MessageData;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Class ViewIntroductionMenuSkyjo represents the introduction menu of the game.
 *
 * @author timmy
 */
public class ViewIntroductionMenuSkyjo extends VBox {

    private ClientSkyjo client;
    private Stage stage;
    private Button startButton;
    private Button exitButton;
    private Button dataButton;

    /**
     * Constructor of ViewIntroductionMenuSkyjo with the client to associate and
     * the stage to associate as arguments.
     *
     * @param client the client to associate
     * @param stage the stage to associate
     */
    public ViewIntroductionMenuSkyjo(ClientSkyjo client, Stage stage) {
        this.client = client;
        this.stage = stage;
        this.setPadding(new Insets(275, 0, 0, 0));
        this.setAlignment(Pos.TOP_CENTER);
        this.setSpacing(10);
        File file = new File("./src/resources/Skyjo.jpg");
        Image image = new Image(file.toURI().toString());
        BackgroundImage backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.SPACE, BackgroundRepeat.SPACE,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        this.setBackground(new Background(backgroundImage));
        createButtons();
    }

    /**
     * Private method called by the constructor: Creates the buttons to start a
     * game of Skyjo and to exit the game and not play at it and leaves the
     * server.
     */
    private void createButtons() {
        createStartButton();
        createExitButton();
        createDataButton();
        HBox buttons = new HBox();
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(100);
        buttons.getChildren().addAll(startButton, dataButton, exitButton);
        this.getChildren().add(buttons);
    }

    /**
     * Private method that creates the button start of the menu.
     */
    private void createStartButton() {
        startButton = new Button("Start");
        startButton.addEventHandler(ActionEvent.ACTION, (t) -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().
                        getResource("ViewTextFieldName.fxml"));
                Parent root = loader.load();
                ViewTextfieldNameController controller = loader.getController();
                controller.setStage(stage);
                controller.setClient(client);
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ViewIntroductionMenuSkyjo.class.getName())
                        .log(Level.SEVERE, null, ex);
            }

        });
        startButton.setMinHeight(30);
        startButton.setMinWidth(100);
    }

    /**
     * Private method that creates the button exit of the menu.
     */
    private void createExitButton() {
        exitButton = new Button("Exit");
        exitButton.addEventHandler(ActionEvent.ACTION, (t) -> {
            try {
                stage.close();
                client.sendToServer(new MessageExit(true));
                client.closeConnection();
            } catch (IOException ex) {
                Logger.getLogger(ViewIntroductionMenuSkyjo.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        });
        exitButton.setMinHeight(30);
        exitButton.setMinWidth(100);
    }

    /**
     * Private method that creates the button data of the menu.
     */
    private void createDataButton() {
        dataButton = new Button("Data");
        dataButton.addEventHandler(ActionEvent.ACTION, (t) -> {
            try {
                client.sendToServer(new MessageData(true));
            } catch (IOException ex) {
                Logger.getLogger(ViewIntroductionMenuSkyjo.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        });
        dataButton.setMinHeight(30);
        dataButton.setMinWidth(100);
    }
}
