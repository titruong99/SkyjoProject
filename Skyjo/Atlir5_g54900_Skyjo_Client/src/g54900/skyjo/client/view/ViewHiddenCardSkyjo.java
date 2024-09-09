package g54900.skyjo.client.view;

import java.io.File;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Class ViewHiddenCardSkyjo represents the display of a hidden card of the game
 * Skyjo.
 *
 * @author timmy
 */
public class ViewHiddenCardSkyjo extends ImageView {

    /**
     * Constructor of ViewHiddenCardSkyjo without arguments. It creates a hidden
     * card of the game Skyjo from an image given with the path.
     */
    public ViewHiddenCardSkyjo() {
        File file = new File("./src/resources/carteSkyjo.jpg");
        Image image = new Image(file.toURI().toString());
        this.setImage(image);
        this.setFitHeight(120);
        this.setFitWidth(68);
    }
}
