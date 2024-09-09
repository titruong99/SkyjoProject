package g54900.skyjo.client.view;

import g54900.skyjo.client.model.ClientSkyjo;
import g54900.skyjo.message.client.MessageHitCard;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * Class ViewPilesSkyjo represents the view of the deck and the discard pile
 * with their logos and also the hit card if there is one.
 *
 * @author timmy
 */
public class ViewPilesSkyjo extends GridPane {

    private ViewValueCardSkyjo viewDiscardPile;
    private ViewHiddenCardSkyjo viewDeck;
    private VBox cardHitLocation;

    /**
     * Constructor of ViewPilesSkyjo with the client to associate, the
     * informations of the game and the label where the messages are shown as
     * argument.
     *
     *
     * @param client the client to associate
     * @param informationsGame the informations of the game
     * @param message the label where the messages are shown
     */
    public ViewPilesSkyjo(ClientSkyjo client, List<Integer> informationsGame,
            ViewMessageSkyjo message) {
        this.setVgap(50);
        this.setHgap(15);
        putHitCard(informationsGame);
        createDeck(client, informationsGame, message);
        viewDiscardPile = new ViewValueCardSkyjo(informationsGame.get(4));
        ImageView iconDeck = getImage("./src/resources/iconDeck.png");
        ImageView iconDiscardPile = getImage("./src/resources"
                + "/iconDiscardCard.png");
        this.add(cardHitLocation, 2, 1);
        this.add(viewDeck, 1, 1);
        this.add(viewDiscardPile, 1, 3);
        this.add(iconDeck, 0, 1);
        this.add(iconDiscardPile, 0, 3);
    }

    /**
     * Private method that creates a lebel for the hit card according to the
     * informations.
     *
     * @param informationsGame the informations of the game
     */
    private void putHitCard(List<Integer> informationsGame) {
        cardHitLocation = new VBox();
        if (informationsGame.get(6) != -10) {
            cardHitLocation.getChildren().
                    add(new ViewValueCardSkyjo(informationsGame.get(6)));
        } else {
            cardHitLocation.setBackground(new Background(new BackgroundFill(
                    Color.FORESTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            cardHitLocation.setMinHeight(120);
            cardHitLocation.setMinWidth(68);
        }
    }

    /**
     * Private method that creates the view of the deck with his interactions.
     *
     * @param client the client associated
     * @param informationsGame the informations of the game
     * @param message the label where the messages are shown
     */
    private void createDeck(ClientSkyjo client, List<Integer> informationsGame,
            ViewMessageSkyjo message) {
        viewDeck = new ViewHiddenCardSkyjo();
        if (informationsGame.get(0) == -10) {
            viewDeck.addEventHandler(MouseEvent.MOUSE_CLICKED,
                    (MouseEvent event) -> {
                        if (informationsGame.get(2)
                        == client.getIndexClient()) {
                            if (cardHitLocation.getChildren().isEmpty()) {
                                try {
                                    client.sendToServer(
                                            new MessageHitCard(true));
                                } catch (IOException ex) {
                                    Logger.getLogger(ViewPilesSkyjo.class.
                                            getName()).log(Level.SEVERE, null,
                                                    ex);
                                }
                            } else {
                                message.setText("Sorry ! You have already hit a"
                                        + " card ! Keep the hit card or reveal "
                                        + "one of your cards.");
                            }
                        } else {
                            event.consume();
                        }
                    });
        }
    }

    /**
     * Private method called by the constructor: it creates an image that will
     * be put in the display, according to the given path.
     *
     * @param path the path for the image
     * @return an image that will be put in the display.
     */
    private ImageView getImage(String path) {
        File file = new File(path);
        Image image = new Image(file.toURI().toString());
        ImageView imgView = new ImageView(image);
        imgView.setFitHeight(100);
        imgView.setFitWidth(100);
        return imgView;
    }

}
