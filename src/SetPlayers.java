import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;


/*
*
* set all players
*
* */

public class SetPlayers extends Config implements SetPlayersInterface{

    public SetPlayers(){}

    // Create card game player map
    public LinkedHashMap<String, CardPlayer> set(){
        GetInput getInput = new GetInput();
        Scanner in = new Scanner(System.in);
        LinkedHashMap<String,CardPlayer> playerList = new LinkedHashMap<String,CardPlayer>();

        System.out.println("Now we can add players, the first player is first round's dealer.");
        System.out.printf("The first dealer has %d * n money first.\n",this.initMoney);
        System.out.println("Each round's highest money player will be dealer.");
        int memberCount = 0;
        while (memberCount < 2){
            try {
                System.out.println("How many players do you want to add. Please input an integer more than 1.");
                String temp = in.nextLine();
                getInput.Exit(temp);
                memberCount = getInput.getInt(temp);
            }catch (Exception e){

            }
        }

        for(int i = 0; i < memberCount; i++){
            CardPlayer p = new CardPlayer();
            String name = "";
            while (playerList.containsKey(name) || name.equals("")) {
                System.out.printf("What is the No.%d player's name? Please make sure every player have different name.\n", i+1);
                name = in.nextLine();
                getInput.Exit(name);
            }
            if(i == 0){
                p.setName(name);
                p.setDealer(true);
                p.setMoney(this.initMoney * memberCount);
                playerList.put(name, p);
            }else {
                p.setName(name);
                p.setMoney(this.initMoney);
                playerList.put(name, p);
            }
        }
        System.out.println("Now we have players.");
        System.out.println("===================================================");

        return playerList;
    }
}
