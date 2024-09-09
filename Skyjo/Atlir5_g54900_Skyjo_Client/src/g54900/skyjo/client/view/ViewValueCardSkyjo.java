package g54900.skyjo.client.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Class ViewValueCardSkyjo represents the view of a visible card with his value
 * and his color.
 *
 * @author timmy
 */
public class ViewValueCardSkyjo extends Label {

    /**
     * Constructor of ViewValueCardSkyjo with the integer value as argument.
     *
     * @param value the integer value of the card
     */
    public ViewValueCardSkyjo(int value) {
        this.setText("" + value);
        this.setFont(new Font("Times New Roman", 40));
        this.setAlignment(Pos.CENTER);
        this.setMinSize(68, 120);
        this.setBackground(new Background(new BackgroundFill(
                getColorCard(value), CornerRadii.EMPTY,
                Insets.EMPTY)));
    }

    /**
     * Private method that gives the color of the card according to the value.
     *
     * @param value the integer value
     * @return the color according to the value of the card.
     */
    private Color getColorCard(int value) {
        Color color;
        switch (value) {
            case -2:
                color = Color.LIGHTGREY;
                break;
            case -1:
                color = Color.LIGHTGREY;
                break;
            case 0:
                color = Color.DEEPSKYBLUE;
                break;
            case 1:
                color = Color.GREENYELLOW;
                break;
            case 2:
                color = Color.GREENYELLOW;
                break;
            case 3:
                color = Color.GREENYELLOW;
                break;
            case 4:
                color = Color.GREENYELLOW;
                break;
            case 5:
                color = Color.YELLOW;
                break;
            case 6:
                color = Color.YELLOW;
                break;
            case 7:
                color = Color.YELLOW;
                break;
            case 8:
                color = Color.YELLOW;
                break;
            case 9:
                color = Color.RED;
                break;
            case 10:
                color = Color.RED;
                break;
            case 11:
                color = Color.RED;
                break;
            default:
                color = Color.RED;
                break;
        }
        return color;
    }
}
