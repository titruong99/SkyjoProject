package g54900.skyjo.client.view;

import g54900.skyjo.client.model.ClientSkyjo;
import g54900.skyjo.message.client.MessageEndGame;
import java.io.IOException;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Class ViewMessageSkyjo represents the location where the messages of the game
 * will be shown.
 *
 * @author timmy
 */
public class ViewMessageSkyjo extends Label {

    /**
     * Constructor of a ViewMessageSkyjo with the client to associate, the
     * informations of the game and the name of the two players as arguments.
     *
     * @param client the client to associate
     * @param informationsGame the informations of the game
     * @param namePlayer1 the name of the first player
     * @param namePlayer2 the name of the second player
     * @throws IOException
     */
    public ViewMessageSkyjo(ClientSkyjo client, List<Integer> informationsGame,
            String namePlayer1, String namePlayer2) throws IOException {
        String text;
        String currentPlayer;
        String otherPlayer;
        if (informationsGame.get(2) == 0) {
            currentPlayer = namePlayer1;
            otherPlayer = namePlayer2;
        } else {
            currentPlayer = namePlayer2;
            otherPlayer = namePlayer1;
        }
        if (informationsGame.get(0) != -10) {
            String winner;
            String name;
            if (informationsGame.get(0) == 0) {
                winner = namePlayer1;
            } else {
                winner = namePlayer2;
            }
            text = "Congratulations "
                    + winner + " ! You have Won !";
            if (client.getIndexClient() == 0) {
                name = namePlayer1;
            } else {
                name = namePlayer2;
            }
            client.sendToServer(new MessageEndGame(name));
        } else if (client.getIndexClient() != informationsGame.get(2)) {
            text = otherPlayer + " , it is not your turn to play.";
        } else {
            text = "Well , " + currentPlayer + " , your turn to play ! "
                    + "Right click on one of the cards to "
                    + "see the different actions.";

        }
        this.setAlignment(Pos.CENTER);
        this.setText(text);
        this.setStyle("-fx-font-weight: bold");
        this.setFont(Font.font("Arial", 15));
        this.setBackground(new Background(new BackgroundFill(Color.BEIGE,
                CornerRadii.EMPTY, Insets.EMPTY)));
        this.setAlignment(Pos.CENTER);
        this.setMinHeight(50);
        this.setMinWidth(800);
    }

    /**
     * Changes the instructions given to the players.
     *
     * @param instructions the instructions to give
     */
    void setMessage(String instructions) {
        this.setText(instructions);
    }
}
