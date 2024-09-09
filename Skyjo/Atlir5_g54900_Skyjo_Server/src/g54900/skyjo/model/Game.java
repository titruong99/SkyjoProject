package g54900.skyjo.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Game represents the game of Skyjo with all the components needed to
 * make work the game.
 *
 * @author timmy
 */
public class Game implements Model {

    private static int NB_FIRST_PLAYER = 1;
    private static int INITIAL_VISIBLE_CARDS = 2;
    private static int NO_VALUE = -10;
    private static int END_VALUE = -100;

    private DiscardPile pile;
    private Deck deck;
    private Player firstPlayer;
    private Player secondPlayer;
    private Player firstToPlay;
    private Player currentPlayer;
    private Status status;
    private Player winner;
    private Card hitCard;
    private List<Integer> informations;
    private int index;

    /**
     * Constructor of a game with the index of the game.It sets the index of the
     * game, creates the deck, the discardPile, the two players, puts the game
     * at the status of START and creates a list of informations of the game.
     *
     * @param index index of the game
     */
    public Game(int index) {
        this.index = index;
        status = Status.START;
        deck = new Deck();
        pile = new DiscardPile();
        firstPlayer = new Player(new Hand());
        secondPlayer = new Player(new Hand());
        informations = new ArrayList<>();
    }

    /**
     * Gets the informations of the game.
     *
     * @return the informations of the game
     */
    @Override
    public List<Integer> getInformationsGame() {
        return informations;
    }

    /**
     * Gets the index of the game.
     *
     * @return the index of the game
     */
    @Override
    public int getIndex() {
        return index;
    }

    /**
     * Starts the game of Skyjo by shuffling the deck, putting a card on the
     * discard pile and distributing the cards to the players.
     */
    @Override
    public void start() {
        deck.shuffle();
        pile.putOnDiscardPile(deck.hit());
        currentPlayer = firstPlayer;
        distributeCards();
        startGame();
    }

    /**
     * Starts the game by revealing two random cards from the hand of the two
     * players and determines who begins.
     *
     */
    @Override
    public void startGame() {
        if (status == status.START) {
            for (int i = 0; i < 4; i++) {
                if (i == 2) {
                    changePlayer();
                }
                currentPlayer.revealCard((int) (Math.random() * (3)),
                        (int) (Math.random() * (4)));
            }
            decideBeginner();
        }
    }

    /**
     * Exchanges the card chosen by the player with the top card of the discard
     * pile.
     *
     * @param row row of the chosen card
     * @param column column of the chosen card
     */
    @Override
    public void exchangeWithDiscardCard(int row, int column) {
        Card oldTopCard = pile.removeTopCard();
        pile.putOnDiscardPile(currentPlayer.getHand().getCards()[row][column]);
        currentPlayer.changeCard(oldTopCard, row, column);
        currentPlayer.revealCard(row, column);
        if (isOver() && !currentPlayer.equals(firstToPlay)) {
            finishGame();
        }
        changePlayer();
    }

    /**
     * Exchanges the card that has been hit with the card chosen by the player.
     * The card chosen by the player is put after on the discard pile.
     *
     * @param row row of the chosen card
     * @param column column of the chosen card
     */
    @Override
    public void keepHitCard(int row, int column) {
        pile.putOnDiscardPile(currentPlayer.getHand().getCards()[row][column]);
        currentPlayer.changeCard(hitCard, row, column);
        currentPlayer.revealCard(row, column);
        if (isOver() && !currentPlayer.equals(firstToPlay)) {
            finishGame();
        }
        changePlayer();
        hitCard = null;

    }

    /**
     * Drops the card that has been hit by putting it in the discard pile and
     * lets the player reveal the card he or she has chosen.
     *
     * @param row row of the chosen card
     * @param column column of the chosen card
     */
    @Override
    public void dropHitCard(int row, int column) {
        pile.putOnDiscardPile(hitCard);
        currentPlayer.revealCard(row, column);
        if (isOver() && !currentPlayer.equals(firstToPlay)) {
            finishGame();
        }
        changePlayer();
        hitCard = null;
    }

    /**
     * Hits a card from the deck by calling the method in the class Deck.
     */
    @Override
    public void hitCard() {
        if (status == status.PLAYING) {
            hitCard = deck.hit();
        }
    }

    /**
     * Puts the different informations of the game like the current player or
     * the number of points of the players...
     */
    @Override
    public void putInformationsGame() {
        informations.clear();
        putInformationsFromGame();
        putInformationsHandPlayer(firstPlayer);
        putInformationsHandPlayer(secondPlayer);
    }

    /**
     * Private method called by putInformationsGame: it adds the different
     * informations of the game.
     */
    private void putInformationsFromGame() {
        if (winner == firstPlayer) {
            informations.add(0);
        } else if (winner == secondPlayer) {
            informations.add(1);
        } else {
            informations.add(NO_VALUE);
        }//indice 0
        informations.add(END_VALUE);//indice 1
        if (currentPlayer.equals(firstPlayer)) {
            informations.add(0);//indice 2
        } else {
            informations.add(1);//indice 2
        }
        informations.add(END_VALUE);//indice 3
        informations.add(pile.getTopCard().getValueCard().getValue());//indice 4
        informations.add(END_VALUE);//indice 5
        if (hitCard != null) {
            informations.add(hitCard.getValueCard().getValue());//indice 6
        } else {
            informations.add(NO_VALUE);//indice 6
        }
        informations.add(END_VALUE);// indice 7
        informations.add(firstPlayer.getTotalPoints());//indice 8
        informations.add(secondPlayer.getTotalPoints());//indice 9
        informations.add(END_VALUE); // indice 10
    }

    /**
     * Private method called by putInformationsGame: it adds the coordinates and
     * the values of the different visible cards in the hand of a player.
     *
     * @param player the player which the informations need to be added
     */
    private void putInformationsHandPlayer(Player player) {
        for (int i = 0; i < Hand.NB_ROWS; i++) {
            for (int j = 0; j < Hand.NB_COLUMNS; j++) {
                if (!player.getHand().getCards()[i][j].isHidden()) {
                    informations.add(i);
                    informations.add(j);
                    informations.add(player.getHand().getCards()[i][j].
                            getValueCard().getValue());
                }
            }
        }
        informations.add(END_VALUE);
    }

    /**
     * Private method called by start: it distributes the cards to the players.
     */
    private void distributeCards() {
        for (int i = 0; i < Hand.NB_ROWS; i++) {
            for (int j = 0; j < Hand.NB_COLUMNS; j++) {
                firstPlayer.getHand().addCard(deck.hit(), i, j);
            }
        }
        for (int k = 0; k < Hand.NB_ROWS; k++) {
            for (int l = 0; l < Hand.NB_COLUMNS; l++) {
                secondPlayer.getHand().addCard(deck.hit(), k, l);
            }
        }
    }

    /**
     * Private method called by startGame, exchangeWithDiscardCard, keepHitCard,
     * dropHitCard: Pass the turn to the other player according to the current
     * player.
     */
    private void changePlayer() {
        if (currentPlayer.equals(firstPlayer)) {
            currentPlayer = secondPlayer;
        } else {
            currentPlayer = firstPlayer;
        }
    }

    /**
     * Private method called by startGame: decides which of the players begin
     * according to their cards and puts the game at the status PLAYING.
     */
    private void decideBeginner() {
        if (firstPlayer.getHand().getPoints() > secondPlayer.getHand().
                getPoints()) {
            currentPlayer = firstPlayer;
            firstToPlay = firstPlayer;
        } else if (secondPlayer.getHand().getPoints() > firstPlayer.getHand().
                getPoints()) {
            currentPlayer = secondPlayer;
            firstToPlay = secondPlayer;
        } else {
            int order = (int) (Math.random() * 2) + 1;
            if (order == NB_FIRST_PLAYER) {
                currentPlayer = firstPlayer;
                firstToPlay = firstPlayer;
            } else {
                currentPlayer = secondPlayer;
                firstToPlay = secondPlayer;
            }
        }
        status = Status.PLAYING;
    }

    /**
     * Private method called by isOver: Checks if all the cards of the player
     * are visible.
     *
     * @param player the player which the hand has to be checked.
     * @return true if all his cards are visible, false otherwise.
     */
    private boolean areAllCardsVisible(Player player) {
        for (Card[] rowCard : player.getHand().getCards()) {
            for (Card card : rowCard) {
                if (card.isHidden()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Private method called by exchangeWithDiscardCard, keepHitCard,
     * dropHitCard: Checks if the game is over or not.
     *
     * @return true if one of the players has all his cards visible, false
     * otherwise.
     */
    private boolean isOver() {
        return areAllCardsVisible(firstPlayer)
                || areAllCardsVisible(secondPlayer);
    }

    /**
     * Private method called by exchangeWithDiscardCard, keepHitCard,
     * dropHitCard: Finishes the game by putting the game at the status
     * "FINISHED" and reveals all the cards of the players and decides the
     * winner.
     */
    private void finishGame() {
        status = Status.FINISHED;
        firstPlayer.revealCards();
        secondPlayer.revealCards();
        chooseWinner();
    }

    /**
     * Private method called by finishGame: chooses the winner of the game.
     */
    private void chooseWinner() {
        Player other;
        if (firstToPlay.equals(firstPlayer)) {
            other = secondPlayer;
        } else {
            other = firstPlayer;
        }

        if (firstToPlay.getTotalPoints() >= other.getTotalPoints()) {
            winner = other;
        } else {
            winner = firstToPlay;
        }
    }
}
