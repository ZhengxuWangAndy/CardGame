import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;


// use for set up teams and players. only need set one time on the beginning
public class setTeamsPlayers {
    public LinkedHashMap<String,team> setTeamsPlayers(){
        Scanner in = new Scanner(System.in);
        LinkedHashMap<String,team> teamMap = new LinkedHashMap<>();

        int teamCounts = 0;
        while (teamCounts<2){
            try {
                System.out.println("How many teams do you want? please input more than 1.");
                String temp = in.nextLine();
                if(temp.equals("exit")){
                    System.exit(0);
                }
                teamCounts = Integer.parseInt(temp);
            }catch (Exception e){

            }
        }

        for(int i = 0; i < teamCounts; i++){
            String name = "";
            while (teamMap.containsKey(name) || name.equals("")) {
                System.out.printf("Please input No.%d team name. Please input a valid name that different from others and not null.\n", i + 1);
                name = in.nextLine();
                if(name.equals("exit")){
                    System.exit(0);
                }
            }
            team oneTeam = new team();
            oneTeam.setName(name);
            teamMap.put(name,oneTeam);
        }

        System.out.printf("Now we have %d teams, ", teamCounts);
        System.out.println(teamMap.keySet().toString());
        System.out.println("===================================================");


        ArrayList<String> members = new ArrayList<String>();

        teamMap.forEach((key, value) ->{
            System.out.println("Now we can add player for team " + key);
            int memberCount = 0;
            while (memberCount < 1){
                try {
                    System.out.println("How many players do you want to add. Please input an integer more than 0.");
                    String temp = in.nextLine();
                    if(temp.equals("exit")){
                        System.exit(0);
                    }
                    memberCount = Integer.parseInt(temp);
                }catch (Exception e){

                }
            }

            for(int i = 0; i < memberCount; i++){
                player p = new player();
                String name = "";
                while (value.playerMap.containsKey(name) || name.equals("") || members.contains(name)) {
                    System.out.println("What is the player's name? Please make sure every player have different name.");
                    name = in.nextLine();
                    if(name.equals("exit")){
                        System.exit(0);
                    }
                }
                members.add(name);
                p.setName(name);
                value.addPlayer(p);
            }
        });
        System.out.println("Now we have teams and players.");
        System.out.println("===================================================");
        return teamMap;
    }
}
