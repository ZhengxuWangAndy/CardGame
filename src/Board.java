import java.util.*;


// define the board data struct and judge winner method for each turn.
public class Board {
    private String[][] board;
//    int n;
    private int width;
    private int height;

    public Board(int width, int height) {
        this.board = new String[height][width];
        this.width = width;
        this.height = height;
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                this.board[i][j] = " ";
            }
        }
    }

    public String[][] getBoard(){return this.board;}

    public void clearBoard(){
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                this.board[i][j] = " ";
            }
        }
    }
    public int getWidth(){return width;}
    public int getHeight(){return height;}
    public String round(String turn, int i, int j, int winCount){
        // Current player symbol turn, position i,j and victory judgments count winCount.
        // return map turn,board means turn wins and ""board means no one wins.
        this.board[i][j] = turn;
        int row_flag = 1;
        int col_flag = 1;

        int leftSpace = width * height;
        for(int p = 0; p < height; p++){
            for(int q = 0; q < width; q++){
                if(this.board[p][q] != " "){
                    leftSpace--;
                }
            }
        }
        if(leftSpace == 0){
            return "stalemate";
        }
        for(int k = 0; k < width-1; k++){
            //Judge on the Col
            if(board[k][j].equals(board[k+1][j]) && !board[k][j].equals(" ") && !board[k+1][j].equals(" ")){
                row_flag += 1;
            }
            if(row_flag == winCount){
                return turn;
            }
            if(!board[k][j].equals(board[k+1][j])){
                row_flag = 1;
            }

            // Judge on the Row
            if(board[i][k].equals(board[i][k+1]) && !board[i][k].equals(" ") && !board[i][k+1].equals(" ")){
                col_flag += 1;
            }
            if(col_flag == winCount){
                return turn;
            }
            if(!board[i][k].equals(board[i][k+1])){
                col_flag = 1;
            }

        }

        //Judge on the diagonal
        int diagonal1 = 1;
        for (int k = 1; k < width-i && k < height-j; k++){
            if(board[i+k-1][j+k-1].equals(board[i+k][j+k]) && !board[i+k-1][j+k-1].equals(" ") && !board[i+k][j+k].equals(" ")){
                diagonal1 += 1;
            }
            else{
                break;
            }
        }
        for (int k = 1; i-k >= 0 && j-k >= 0; k++){
            if(board[i-k+1][j-k+1].equals(board[i-k][j-k]) && !board[i-k+1][j-k+1].equals(" ") && !board[i-k][j-k].equals(" ")){
                diagonal1 += 1;
            }
            else{
                break;
            }
        }
        if(diagonal1 == winCount){
            return turn;
        }

        int diagonal2 = 1;
        for (int k = 1; i-k >= 0 && j+k < width; k++){
            if(board[i-k+1][j+k-1].equals(board[i-k][j+k]) && !board[i-k+1][j+k-1].equals(" ") && !board[i-k][j+k].equals(" ")){
                diagonal2 += 1;
            }
            else{
                break;
            }
        }
        for (int k = 1; i+k < height && j-k >= 0; k--){
            if(board[i+k-1][j-k+1].equals(board[i+k][j-k]) && !board[i+k-1][j-k+1].equals(" ") && !board[i+k][j-k].equals(" ")){
                diagonal2 += 1;
            }
            else{
                break;
            }
        }

        if(diagonal1 == winCount || diagonal2 == winCount){
            return turn;
        }

        return "";
    }

}
