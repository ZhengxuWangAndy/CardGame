import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Queue;

// control the process of order and chaos game
public class OrderChaos extends gameInitialization{

    private int winCount = 5;
    private int size = 6;
    public OrderChaos(){
        System.out.println("   ___          _              ___          _                     \n" +
                "  / _ \\ _ __ __| | ___ _ __   ( _ )     ___| |__   __ _  ___  ___ \n" +
                " | | | | '__/ _` |/ _ \\ '__|  / _ \\/\\  / __| '_ \\ / _` |/ _ \\/ __|\n" +
                " | |_| | | | (_| |  __/ |    | (_>  < | (__| | | | (_| | (_) \\__ \\\n" +
                "  \\___/|_|  \\__,_|\\___|_|     \\___/\\/  \\___|_| |_|\\__,_|\\___/|___/\n" +
                "                                                                  ");
    }

    public void orderRound(LinkedHashMap<String,team> teamMap){

        System.out.println("Here are the current teams and players.");
        print.printRecord(teamMap);
        System.out.println("===================================================");

        System.out.printf("Round %d Game start!\n", getRound());

//        Boolean flag = true;
//        while (flag) {
//            try {
//                System.out.println("What's the board size do you want to play？");
//                System.out.println("Please input an integer n means the board size of n*n. Valid number needs greater than 2");
//                n = Integer.parseInt(in.nextLine());
//
//                if(n>2){
//                    flag = false;
//                }
//            } catch (Exception e) {
//
//            }
//        }

        this.board = new Board(size,size);
        this.draw = new drawBoard(board);
        System.out.println("Here is your board.");
        draw.draw(board);
        System.out.println("===================================================");


        System.out.println("And current team board.");
        print.printRecord(teamMap);
        System.out.println("===================================================");


        String teamA = "";
        String playerA_name = "";
        while (!teamMap.containsKey(teamA)){
            System.out.println("The first team (Oreder team) is? Please input a valid name that include in team board");
            teamA = in.nextLine();
            if(teamA.equals("exit")){
                System.exit(0);
            }
        }

        while (!teamMap.get(teamA).playerMap.containsKey(playerA_name)){
            System.out.println("The player name (Order gamer) is? Please input a valid name that include in this team");
            playerA_name = in.nextLine();
            if(playerA_name.equals("exit")){
                System.exit(0);
            }
        }


        String symbolA = "";
        player playerA = teamMap.get(teamA).playerMap.get(playerA_name);

        while (symbolA.equals("") || symbolA.equals(" ")) {
            System.out.println("What is the symbol would you like? Use for mark the piece");
            symbolA = in.nextLine().substring(0, 1);
        }
        playerA.setSymbol(symbolA);
        System.out.println("===================================================");



        String teamB = "";
        String playerB_name = "";

        // make sure it contains and not same as teamA.
        while (!(teamMap.containsKey(teamB) && !teamA.equals(teamB))) {
            System.out.println("And another team (Chaos team) is? Please input a valid name that include in team board.");
            teamB = in.nextLine();
            if(teamB.equals("exit")){
                System.exit(0);
            }
        }

        while (!(teamMap.get(teamB).playerMap.containsKey(playerB_name) && !playerA_name.equals(playerB_name))) {
            System.out.println("The player name (Chaos gamer) is? Please input a valid name that include in this team");
            playerB_name = in.nextLine();
            if(playerB_name.equals("exit")){
                System.exit(0);
            }
        }

        String symbolB = "";
        player playerB = teamMap.get(teamB).playerMap.get(playerB_name);
        while (symbolB.equals(symbolA) || symbolB.equals("") || symbolB.equals(" ") ) {
            System.out.println("What is the symbol would you like? Use for mark the piece (Not null and different from opponent)");
            symbolB = in.nextLine().substring(0, 1);
        }
        playerB.setSymbol(symbolB);

        Queue<player> roundControl = new LinkedList();
        roundControl.add(playerA);
        roundControl.add(playerB);

        System.out.println("===================================================");
        System.out.println("Here is the board below");
        draw.draw(board);

        while(getGameOver().equals(false)){

            int i = 0;
            int j = 0;

            // pop the current player
            player currentPlayer = roundControl.poll();
            roundControl.add(currentPlayer);

            Boolean posFlag = false; // test whether the position is valid


            String currentSymbol = "";

            while(posFlag!=true) {
                System.out.printf("It's %s turn\n",currentPlayer.getName());
                System.out.println("What's position would you want to?(please input a valid number and the symbol you want, split with one space)");
                System.out.printf("You can choose from symbol %s or %s\n", symbolA, symbolB);
                System.out.println("For example:2 3 x");

                String posStr = in.nextLine();
                if(posStr.equals("exit")){
                    System.exit(0);
                }
                String[] posRead = posStr.split(" ");

                if (posRead.length == 3) {
                    try{
                        i = Integer.parseInt(posRead[0]);
                        j = Integer.parseInt(posRead[1]);
                        currentSymbol = posRead[2];

                        if (board.getBoard()[i][j] == " " || currentSymbol != symbolA || currentSymbol != symbolB){
                            posFlag = true;
                        }
                    }catch (Exception e){
                        System.out.println("looks not good, please input a again.");
                    }
                }else{
                    posFlag = false;
                }
            }



            String roundResult = board.round(currentSymbol, i, j, winCount);

            draw.draw(board);
            System.out.println("===================================================");


            if(roundResult != "" && roundResult != "stalemate"){
                setGameOver(true);
                setRound(getRound() + 1);
                playerA.addScore();
                System.out.printf("Congratulation! %s WIN!!! Order WIN!!!\n",playerA.getName());
                board.clearBoard();

                print.printRecord(teamMap);
                System.out.println("===================================================");

            } else if (roundResult == "stalemate") {
                setGameOver(true);
                setRound(getRound() + 1);
                playerB.addScore();
                System.out.printf("Congratulation! %s WIN!!! Chaos WIN!!!\n",playerB.getName());
                board.clearBoard();

                print.printRecord(teamMap);
                System.out.println("===================================================");


            }else {
                setGameOver(false);
            }

            setTurnNum(getTurnNum() + 1);
        }


    }

}
