package g54900.skyjo.client.view;

import g54900.skyjo.client.model.ClientSkyjo;
import g54900.skyjo.message.client.MessageDropHitCard;
import g54900.skyjo.message.client.MessageExchangeDiscardCard;
import g54900.skyjo.message.client.MessageKeepHitCard;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Class ViewPlayerSkyjo represents the view of the cards, the score and the
 * name of the player.
 *
 * @author timmy
 */
public class ViewPlayerSkyjo extends VBox {

    private StackPane display;
    private Text informationsPlayer;
    private GridPane viewCards;
    private ContextMenu interactions;
    private MenuItem itemExchange;
    private MenuItem itemKeepHitCard;
    private MenuItem itemDropHitCard;
    private int indexPlayer;

    /**
     * Constructor of ViewPlayerSkyjo with the client to associate, the index of
     * the client, the name of the player, the informations of the game and the
     * label where the messages are shown.
     *
     * @param client the client to associate
     * @param index the index of the game
     * @param namePlayer the name of the player
     * @param informationsGame the informations of the game
     * @param message the label where the messages are shown
     */
    public ViewPlayerSkyjo(ClientSkyjo client, int index, String namePlayer,
            List<Integer> informationsGame, ViewMessageSkyjo message) {
        indexPlayer = index;
        createDisplay(namePlayer, informationsGame);
        createHand(client, informationsGame, message);
        this.setSpacing(30);
        this.getChildren().addAll(display, viewCards);
    }

    /**
     * Private method called by the constructor: Creates the display for the
     * points and the name of the player.
     *
     * @param namePlayer the name of the player
     * @param informationsGame the informations of the game
     */
    private void createDisplay(String namePlayer,
            List<Integer> informationsGame) {
        Rectangle shapeDisplay = new Rectangle(300, 30, Color.BEIGE);
        shapeDisplay.setArcWidth(30);
        shapeDisplay.setArcHeight(30);
        int value;
        if (indexPlayer == 0) {
            value = informationsGame.get(8);
        } else {
            value = informationsGame.get(9);
        }
        informationsPlayer = new Text(namePlayer + " - Points: " + value);
        informationsPlayer.setFont(new Font("Arial", 15));
        display = new StackPane();
        display.getChildren().addAll(shapeDisplay, informationsPlayer);
    }

    /**
     * Private method called by the constructor: Creates the display for the
     * cards of the player.
     *
     * @param client the client associated
     * @param informationsGame the informations of the game
     * @param message the label where the messages are shown
     */
    private void createHand(ClientSkyjo client,
            List<Integer> informationsGame, ViewMessageSkyjo message) {
        int index = getStartIndex(indexPlayer, informationsGame);
        int row = index;
        int col = index + 1;
        int valueCard = index + 2;
        int current = row;
        viewCards = new GridPane();
        viewCards.setHgap(8);
        viewCards.setVgap(8);
        initiateInteractionsWithGame();
        createCards(client, informationsGame, message, row, col, valueCard);
    }

    /**
     * Private method that creates the view of the cards.
     *
     * @param client the client associated
     * @param informationsGame the informations of the game
     * @param message the label where the messages are shown
     * @param row the index where the row of the card is
     * @param col the index where the column of the card is
     * @param valueCard the index where the value of the card is
     */
    private void createCards(ClientSkyjo client, List<Integer> informationsGame,
            ViewMessageSkyjo message, int row, int col, int valueCard) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                if (i == informationsGame.get(row)
                        && j == informationsGame.get(col)) {
                    ViewValueCardSkyjo card
                            = new ViewValueCardSkyjo(
                                    informationsGame.get(valueCard));
                    canInteractWithCard(client, card,
                            informationsGame, message);
                    viewCards.add(card, j, i);
                    row += 3;
                    if (informationsGame.get(row) != -100) {
                        col += 3;
                        valueCard += 3;
                    }
                } else {
                    ViewHiddenCardSkyjo card = new ViewHiddenCardSkyjo();
                    canInteractWithCard(client, card,
                            informationsGame, message);
                    viewCards.add(card, j, i);
                }
            }
        }
    }

    /**
     * Private method that gets the index according to the player where start
     * the informations about the visible cards.
     *
     * @param index the index of the client
     * @param informationsGame the informations of the game
     * @return the index where to start according to the index of the player.
     */
    private int getStartIndex(int index, List<Integer> informationsGame) {
        int i = 11;
        if (indexPlayer == 1) {
            while (informationsGame.get(i) != -100) {
                i++;
            }
            return i + 1;
        }
        return i;
    }

    /**
     * Private method called by createHand and instantiates the menu with his
     * different items for the interactions on a card.
     */
    private void initiateInteractionsWithGame() {
        interactions = new ContextMenu();
        itemExchange = new MenuItem("Exchange with pile card");
        itemKeepHitCard = new MenuItem("Exchange with hit card");
        itemDropHitCard = new MenuItem("Drop hit card && Reveal card");
        interactions.getItems().addAll(itemExchange, itemKeepHitCard,
                itemDropHitCard);
    }

    /**
     * Private method that allows the player to interact with the cards
     * according to the player and the situation of the game.
     *
     * @param client the client associated
     * @param card the card to create
     * @param informationsGame the informations of the game
     * @param message the label where the messages are shown
     */
    private void canInteractWithCard(ClientSkyjo client, Node card,
            List<Integer> informationsGame, ViewMessageSkyjo message) {
        List<Integer> coordinates = new ArrayList<>();
        if (informationsGame.get(0) == -10) {
            card.setOnContextMenuRequested(
                    new EventHandler<ContextMenuEvent>() {
                @Override
                public void handle(ContextMenuEvent event) {
                    if (informationsGame.get(2) == client.getIndexClient()
                            && indexPlayer == informationsGame.get(2)
                            && !(event.getTarget() instanceof Text)) {
                        interactions.show(card, event.getScreenX(),
                                event.getScreenY());
                        setActionItemExchange(coordinates, event, client,
                                card, informationsGame, message);
                        setActionItemDropHitCard(coordinates, event, client,
                                card, informationsGame, message);
                        setActionItemKeepHitCard(coordinates, event, client,
                                card, informationsGame, message);
                    } else {
                        event.consume();
                    }
                }
            });
        }
    }

    /**
     * Private method that allows the player to exchange a card with the discard
     * pile.
     *
     * @param coordinates the list where the coordinates of the chosen card will
     * be
     * @param event
     * @param client the client associated
     * @param card the chosen card
     * @param informationsGame the informations of the game
     * @param message the label with the messages
     */
    private void setActionItemExchange(List<Integer> coordinates,
            ContextMenuEvent event, ClientSkyjo client, Node card,
            List<Integer> informationsGame, ViewMessageSkyjo message) {
        itemExchange.setOnAction((ActionEvent t) -> {
            if (informationsGame.get(6) == -10) {
                int row = GridPane.getRowIndex(card);
                int col = GridPane.getColumnIndex(card);
                coordinates.add(row);
                coordinates.add(col);
                try {
                    client.sendToServer(
                            new MessageExchangeDiscardCard(
                                    coordinates));
                } catch (IOException ex) {
                    Logger.getLogger(ViewPlayerSkyjo.class.getName()).
                            log(Level.SEVERE, null, ex);
                }
            } else {
                message.setText("Sorry ! You have already hit a "
                        + "card ! Keep the hit card or reveal one"
                        + " of your cards.");
                event.consume();
            }
        });
    }

    /**
     * Private method that allows the player to drop the hit card and reveal the
     * chosen card.
     *
     * @param coordinates the list where the coordinates of the chosen card will
     * be
     * @param event
     * @param client the client associated
     * @param card the chosen card
     * @param informationsGame the informations of the game
     * @param message the label with the messages
     */
    private void setActionItemDropHitCard(List<Integer> coordinates,
            ContextMenuEvent event, ClientSkyjo client, Node card,
            List<Integer> informationsGame, ViewMessageSkyjo message) {
        itemDropHitCard.setOnAction((ActionEvent t) -> {
            if (informationsGame.get(6) == -10) {
                message.setText("You have to hit a card first "
                        + "!");
            } else if (!(card instanceof ViewHiddenCardSkyjo)) {
                message.setText("It is not a hidden card ! "
                        + "Choose a hidden card please !");
            } else {
                int row = GridPane.getRowIndex(card);
                int col = GridPane.getColumnIndex(card);
                coordinates.add(row);
                coordinates.add(col);
                try {
                    client.sendToServer(new MessageDropHitCard(coordinates));
                } catch (IOException ex) {
                    Logger.getLogger(ViewPlayerSkyjo.class.getName()).
                            log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    /**
     *
     * Private method that allows the player to keep the hit card and exchanges
     * with one of his cards.
     *
     * @param coordinates the list where the coordinates of the chosen card will
     * be
     * @param event
     * @param client the client associated
     * @param card the chosen card
     * @param informationsGame the informations of the game
     * @param message the label with the messages
     */
    private void setActionItemKeepHitCard(List<Integer> coordinates,
            ContextMenuEvent event, ClientSkyjo client, Node card,
            List<Integer> informationsGame, ViewMessageSkyjo message) {
        itemKeepHitCard.setOnAction((ActionEvent t) -> {
            if (informationsGame.get(6) != -10) {
                int row = GridPane.getRowIndex(card);
                int col = GridPane.getColumnIndex(card);
                coordinates.add(row);
                coordinates.add(col);
                try {
                    client.sendToServer(new MessageKeepHitCard(coordinates));
                } catch (IOException ex) {
                    Logger.getLogger(ViewPlayerSkyjo.class.getName()).
                            log(Level.SEVERE, null, ex);
                }
            } else {
                message.setText("You have to hit a card first "
                        + "!");
            }
        }
        );
    }
}
