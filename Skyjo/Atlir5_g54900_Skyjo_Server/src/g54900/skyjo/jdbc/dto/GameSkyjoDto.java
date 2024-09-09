package g54900.skyjo.jdbc.dto;

/**
 *
 * @author timmy
 */
public class GameSkyjoDto {

    private int id;
    private int player1;
    private int player2;
    private int scorePlayer1;
    private int scorePlayer2;
    private int winner;

    public GameSkyjoDto(int id, int player1, int player2, int scorePlayer1, int scorePlayer2, int winner) {
        this.id = id;
        this.player1 = player1;
        this.player2 = player2;
        this.scorePlayer1 = scorePlayer1;
        this.scorePlayer2 = scorePlayer2;
        this.winner = winner;
    }

    public int getId() {
        return id;
    }

    public int getPlayer1() {
        return player1;
    }

    public int getPlayer2() {
        return player2;
    }

    public int getScorePlayer1() {
        return scorePlayer1;
    }

    public int getScorePlayer2() {
        return scorePlayer2;
    }

    public int getWinner() {
        return winner;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPlayer1(int player1) {
        this.player1 = player1;
    }

    public void setPlayer2(int player2) {
        this.player2 = player2;
    }

    public void setScorePlayer1(int scorePlayer1) {
        this.scorePlayer1 = scorePlayer1;
    }

    public void setScorePlayer2(int scorePlayer2) {
        this.scorePlayer2 = scorePlayer2;
    }

    public void setWinner(int winner) {
        this.winner = winner;
    }

    @Override
    public String toString() {
        return "GameSkyjoDto{" + "id=" + id + ", player1=" + player1 + ", player2=" + player2 + ", scorePlayer1=" + scorePlayer1 + ", scorePlayer2=" + scorePlayer2 + ", winner=" + winner + '}';
    }
    
    
}
