import java.util.Scanner;

// define the parameters of every game and instance the Board and drawBoard class
public class gameInitialization extends Game{
//    Board board = new Board();
    private int n = 0;
    Scanner in = new Scanner(System.in);
    private Boolean gameOver = false;
    private int turnNum = 1;
    private int round = 1;
    Board board;
    drawBoard draw;
    PrintAllRecord print = new PrintAllRecord();

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public Boolean getGameOver(){return this.gameOver;}

    public int getTurnNum() {
        return turnNum;
    }

    public void setTurnNum(int turnNum) {
        this.turnNum = turnNum;
    }
    public void setGameOver(Boolean gameOver) {
        this.gameOver = gameOver;
    }
    public gameInitialization(){
        System.out.printf("Welcome to the\n");
    }
}
