import java.util.*;

// control the process of order and chaos game
public class Tic extends gameInitialization{


    public Tic(){
        System.out.println(".___________. __    ______    .___________.    ___       ______    .___________.  ______    _______ \n" +
                "|           ||  |  /      |   |           |   /   \\     /      |   |           | /  __  \\  |   ____|\n" +
                "`---|  |----`|  | |  ,----'   `---|  |----`  /  ^  \\   |  ,----'   `---|  |----`|  |  |  | |  |__   \n" +
                "    |  |     |  | |  |            |  |      /  /_\\  \\  |  |            |  |     |  |  |  | |   __|  \n" +
                "    |  |     |  | |  `----.       |  |     /  _____  \\ |  `----.       |  |     |  `--'  | |  |____ \n" +
                "    |__|     |__|  \\______|       |__|    /__/     \\__\\ \\______|       |__|      \\______/  |_______|\n" +
                "                                                                                                    ");

    }

    public void ticRound(LinkedHashMap<String,team> teamMap){

        System.out.println("Here are the current teams and players.");
        print.printRecord(teamMap);
        System.out.println("===================================================");

        System.out.printf("Round %d Game start!\n", getRound());

        Boolean flag = true;
        int n = 0;
        while (flag) {
            try {
                System.out.println("What's the board size do you want to play？");
                System.out.println("Please input an integer n means the board size of n*n. Valid number needs greater than 2");
                String temp =in.nextLine();
                if(temp.equals("exit")){
                    System.exit(0);
                }
                n = Integer.parseInt(temp);
                if(n>2){
                    flag = false;
                }
            } catch (Exception e) {

            }
        }

        this.board = new Board(n,n);
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
            System.out.println("The first team is? Please input a valid name that include in team board");
            teamA = in.nextLine();
            if(teamA.equals("exit")){
                System.exit(0);
            }
        }

        while (!teamMap.get(teamA).playerMap.containsKey(playerA_name)){
            System.out.println("The player name is? Please input a valid name that include in this team");
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
            System.out.println("And another team is? Please input a valid name that include in team board.");
            teamB = in.nextLine();
            if(teamB.equals("exit")){
                System.exit(0);
            }
        }

        while (!(teamMap.get(teamB).playerMap.containsKey(playerB_name) && !playerA_name.equals(playerB_name))) {
            System.out.println("The player name is? Please input a valid name that include in this team");
            playerB_name = in.nextLine();
            if(playerB_name.equals("exit")){
                System.exit(0);
            }
        }

        String symbolB = "";
        player playerB = teamMap.get(teamB).playerMap.get(playerB_name);
        while (symbolB.equals(symbolA) || symbolB.equals("") || symbolB.equals(" ")) {
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


            while(posFlag!=true) {
                System.out.printf("It's %s turn\n",currentPlayer.getName());
                System.out.println("What's position would you want to?(please input a valid number split with space)");
                System.out.println("For example:2 3");
                String posStr = in.nextLine();
                if(posStr.equals("exit")){
                    System.exit(0);
                }
                String[] posRead = posStr.split(" ");

                if (posRead.length == 2) {
                    try{
                        i = Integer.parseInt(posRead[0]);
                        j = Integer.parseInt(posRead[1]);

                        if (board.getBoard()[i][j] == " "){
                            posFlag = true;
                        }
                    }catch (Exception e){
                        System.out.println("looks not good, please input a again.");
                    }
                }else{
                    posFlag = false;
                }
            }


            String roundResult = board.round(currentPlayer.getSymbol(), i, j, n);

            draw.draw(board);
            System.out.println("===================================================");


            if(roundResult != ""){
                if(roundResult == playerA.getSymbol()){
                    setGameOver(true);
                    setRound(getRound() + 1);
                    playerA.addScore();
                    System.out.printf("Congratulation! %s WIN!!!\n",playerA.getName());
                    board.clearBoard();

                    print.printRecord(teamMap);
                    System.out.println("===================================================");



                } else if (roundResult == playerB.getSymbol()) {
                    setGameOver(true);
                    setRound(getRound() + 1);
                    playerB.addScore();
                    System.out.printf("Congratulation! %s WIN!!!\n",playerB.getName());
                    board.clearBoard();

                    print.printRecord(teamMap);
                    System.out.println("===================================================");

                } else if (roundResult == "stalemate") {
                    setGameOver(true);
                    setRound(getRound() + 1);
                    System.out.printf("It's a stalemate\n");
                    board.clearBoard();
                    print.printRecord(teamMap);
                    System.out.println("===================================================");

                }
            }else {
                setGameOver(false);
            }
            setTurnNum(getTurnNum() + 1);
        }
    }

}
