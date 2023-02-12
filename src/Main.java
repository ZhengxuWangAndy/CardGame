import java.util.Scanner;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Iterator;

// Control the top process
public class Main {
    public static void main(String[] args) {
        GetInput getInput = new GetInput();
        selectGame game = new selectGame();
        Scanner in = new Scanner(System.in);
        while (true){
            // select which game
            String type = game.select();
            if (type.equals("1")) {
                // set the team and players, 2-n team each team 1-n player.
                // every team have different name, every player have different name.
                setTeamsPlayers teamBuilder = new setTeamsPlayers();
                LinkedHashMap<String, team> teamMap = teamBuilder.setTeamsPlayers();
                // Tic tac toe
                Tic tic = new Tic();
                while (tic.getTurnNum() != 0) {
                    tic.ticRound(teamMap);
                    String answer = "y";
                    while (!answer.equals("n")) {
                        System.out.println("Do you wanna play another round? y/n");
                        answer = in.nextLine();
                        getInput.Exit(answer);
                        if (answer.equals("y")) {
                            tic.setGameOver(false);
                            tic.ticRound(teamMap);
                        } else if (answer.equals("n")) {
                            tic.setTurnNum(0);
                            break;
                        }
                    }
                }
                System.out.println("Thank you for your playing Tic tac toe");
                System.out.println("===================================================");
            } else if (type.equals("2")) {
                //Order and Chaos
                setTeamsPlayers teamBuilder = new setTeamsPlayers();
                LinkedHashMap<String, team> teamMap = teamBuilder.setTeamsPlayers();
                OrderChaos order = new OrderChaos();
                while (order.getTurnNum() != 0) {
                    order.orderRound(teamMap);
                    String answer = "y";
                    while (!answer.equals("n")) {
                        System.out.println("Do you wanna play another round? y/n");
                        answer = in.nextLine();
                        getInput.Exit(answer);
                        if (answer.equals("y")) {
                            order.setGameOver(false);
                            order.orderRound(teamMap);
                        } else if (answer.equals("n")) {
                            order.setTurnNum(0);
                            break;
                        }
                    }
                }
                System.out.println("Thank you for your playing Order and Chaos");
                System.out.println("===================================================");
            } else if (type.equals("3")) {
                SetPlayers setter = new SetPlayers();
                LinkedHashMap<String, CardPlayer> playerMap = setter.set();
                while (true) {
                    // Set the richest player as dealer
                    int richest = 0;
                    Iterator<Map.Entry<String, CardPlayer>> it = playerMap.entrySet().iterator();
                    while (it.hasNext()){
                        Map.Entry<String, CardPlayer> entry = it.next();
                        if(entry.getValue().getScore() <= 0){
                            it.remove();
                        }
                        richest = Math.max(richest, entry.getValue().getScore());
                    }
                    for (String key : playerMap.keySet()) {
                        CardPlayer p = playerMap.get(key);
                        if (p.getScore() == richest) {
                            p.setDealer(true);
                            break;
                        }
                    }
                    if(playerMap.keySet().size() == 1){
                        playerMap.forEach((key,value) -> {
                            System.out.printf("Dear %s, You are the only one left! You are final Winner!\nGAME ENDED\n", key);
                        });
                        break;
                    }
                    Trianta_ena trianta = new Trianta_ena(playerMap);
                    trianta.printPlayer();
                    trianta.process();
                    System.out.println("Do you want have another round? (y/n)");
                    String answer = in.nextLine();
                    getInput.Exit(answer);
                    if (answer.charAt(0) != 'y'){
                        break;
                    }
                }
            }
        }
    }
}