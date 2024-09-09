package g54900.skyjo.client.view;

import g54900.skyjo.client.model.ClientSkyjo;
import java.io.IOException;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.StageStyle;

/**
 * Class ViewGameSkyjo represents the global view of the game Skyjo.
 *
 * @author timmy
 */
public class ViewGameSkyjo extends VBox {

    private VBox root;
    private HBox hboxGame;
    private HBox hboxMenu;
    private Button helpButton;
    private Button rulesButton;
    private Label title;
    private ViewPlayerSkyjo player1;
    private ViewPlayerSkyjo player2;
    private ViewPilesSkyjo piles;
    private ViewMessageSkyjo message;

    /**
     * Constructor of ViewGameSkyjo with the client to associate, the name of
     * the players and the informations of the game.
     *
     * @param client the client to associate
     * @param namePlayer1 the name of the first player
     * @param namePlayer2 the name of the second player
     * @param informationsGame the informations of the game
     */
    public ViewGameSkyjo(ClientSkyjo client, String namePlayer1,
            String namePlayer2, List<Integer> informationsGame)
            throws IOException {
        initializeRoot(client, namePlayer1, namePlayer2, informationsGame);
    }

    /**
     * Gets the root.
     *
     * @return the root.
     */
    VBox getRoot() {
        return root;
    }

    /**
     * Initializes the main component for the view of the game and add the other
     * components needed for the view of the game in it.
     *
     * @param client the client associated
     * @param namePlayer1 the name of the first player
     * @param namePlayer2 the name of the second player
     * @param informationsGame the informations of the game
     */
    private void initializeRoot(ClientSkyjo client, String namePlayer1,
            String namePlayer2, List<Integer> informationsGame)
            throws IOException {
        initializeViewGame(client, namePlayer1, namePlayer2, informationsGame);
        root = new VBox();
        root.setSpacing(60);
        root.setPadding(new Insets(80, 20, 20, 20));
        root.setBackground(new Background(new BackgroundFill(
                Color.FORESTGREEN, CornerRadii.EMPTY,
                Insets.EMPTY)));
        root.setAlignment(Pos.TOP_CENTER);
        root.getChildren().addAll(hboxMenu, hboxGame, message);
    }

    /**
     * Initializes the view for the game of Skyjo by creating the view of the
     * players with their points and their cards, the piles and the box for the
     * message.
     *
     * @param client the client associated
     * @param namePlayer1 the name of the first player
     * @param namePlayer2 the name of the second player
     * @param informationsGame the informations of the game
     */
    private void initializeViewGame(ClientSkyjo client, String namePlayer1,
            String namePlayer2, List<Integer> informationsGame)
            throws IOException {
        initializeMenu();
        hboxGame = new HBox();
        hboxGame.setSpacing(10);
        hboxGame.setPadding(new Insets(0, 0, 50, 0));
        message = new ViewMessageSkyjo(client, informationsGame,
                namePlayer1, namePlayer2);
        player1 = new ViewPlayerSkyjo(client, 0, namePlayer1,
                informationsGame, message);
        player2 = new ViewPlayerSkyjo(client, 1, namePlayer2,
                informationsGame, message);
        piles = new ViewPilesSkyjo(client, informationsGame, message);
        hboxGame.getChildren().addAll(player1, piles, player2);
    }

    /**
     * Private method that initializes the little menu of the game.
     */
    private void initializeMenu() {
        hboxMenu = new HBox();
        hboxMenu.setAlignment(Pos.CENTER);
        createMenuButtons();
        title = new Label("SKYJO");
        title.setFont(new Font("Times New Roman", 50));
        title.setPadding(new Insets(0, 150, 0, 150));
        hboxMenu.getChildren().addAll(helpButton, title, rulesButton);
    }

    /**
     * Private method that create the buttons of the little menu of the game.
     */
    private void createMenuButtons() {
        createHelpButton();
        createRulesButton();
    }

    /**
     * Private method that creates the button help of the little menu of the
     * game.
     */
    private void createHelpButton() {
        helpButton = new Button("HELP");
        helpButton.setOnAction((ActionEvent t) -> {
            Alert help = new Alert(Alert.AlertType.INFORMATION);
            help.initStyle(StageStyle.UNDECORATED);
            help.setHeaderText("Hi, I am the help of Skyjo");
            help.setContentText("                            "
                    + "            How to play\n\n"
                    + "START: Two random cards in your hand are revealed.\n\n"
                    + "PLAY: right click and three options appear\n"
                    + "         (exchange card with the card on the discard"
                    + " pile\n          exchange with the hit card, \n     "
                    + "     drop the hit card).\n          Choose the "
                    + "option you want.\n\n"
                    + "DECK: click on the deck to hit a card.\n\n"
                    + "NOTICE: hit a card before if you want to exchange or"
                    + "\n              drop the hit card.");
            help.show();
        });
    }

    /**
     * Private method that creates the button rules of the little menu of the
     * game.
     */
    private void createRulesButton() {
        rulesButton = new Button("RULES");
        rulesButton.setOnAction((ActionEvent t) -> {
            Alert rules = new Alert(Alert.AlertType.INFORMATION);
            rules.initStyle(StageStyle.UNDECORATED);
            rules.setHeaderText("Hi, I am the rules of Skyjo");
            rules.setContentText(
                    "Start : - Each of you has 12 cards. Two random\n "
                    + "            cards will be "
                    + "revealed, you will be\n             able to see "
                    + "these cards at the start of the game.\n\n"
                    + "Game : - You can exchange a card in your\n"
                    + "              hand with "
                    + "the card on the discard pile\n\n"
                    + "            - You can choose to hit a card."
                    + "\n         "
                    + "     You decide to keep it and exchange\n"
                    + "               with one of your cards (the "
                    + "exchanging card\n"
                    + "               goes on"
                    + " the discard pile) or you can burn it\n "
                    + "              and return one of your hidden cards."
                    + "\n\n\n"
                    + "End : The game finishes when one of the\n "
                    + "         players have "
                    + "returned all of his cards\n"
                    + "          (Reminder: if the player "
                    + "who begins\n          "
                    + "has returned all of his cards,"
                    + " the next "
                    + "players\n          have to finish the round)\n\n"
                    + "Winner : The one who has the smallest\n"
                    + "                number of points");

            rules.show();
        });
    }
}
