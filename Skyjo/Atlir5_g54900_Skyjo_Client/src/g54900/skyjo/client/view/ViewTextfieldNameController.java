package g54900.skyjo.client.view;

import g54900.skyjo.client.model.ClientSkyjo;
import g54900.skyjo.message.client.MessageReadyToPlay;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Class ViewTextfieldNameController represents the controller of the FXML file
 * ViewTextfieldName.
 *
 * @author timmy
 */
public class ViewTextfieldNameController implements Initializable {

    private Stage stage;
    private ClientSkyjo client;

    @FXML
    private TextField name;

    /**
     * Represents the action when a client click on the button "OK" to confirm
     * his name. It sends a message to the server and closes the window to show
     * an other one.
     *
     * @throws IOException
     */
    @FXML
    public void confirm() throws IOException {
        client.sendToServer(new MessageReadyToPlay(name.getText()));
        Parent root = FXMLLoader.load(getClass().
                getResource("WaitingWindow.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Sets the stage of the controller.
     *
     * @param client the stage to change with the current one
     */
    void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Sets the client of the controller.
     *
     * @param client the client to change with the current one
     */
    void setClient(ClientSkyjo client) {
        this.client = client;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

}
