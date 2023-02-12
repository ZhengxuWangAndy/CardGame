import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/*
* control the whole process
* */
public class Trianta_ena extends Game{

    GetInput getInput = new GetInput();
    private Scanner in = new Scanner(System.in);

    private LinkedHashMap<String, CardPlayer> playerMap;

    private int roundDealerValue = 0;
    private int roundDealerMoneyChange = 0;

    Dozen dozen = new Dozen();


    // the game process of Trianta_ena
    public Trianta_ena(LinkedHashMap<String, CardPlayer> playerMap){
        this.playerMap = playerMap;

        System.out.println("\033[31;36;1m" + " _____  ____  _  ____  _      _____  ____    _____ _      ____ \n" +
                "/__ __\\/  __\\/ \\/  _ \\/ \\  /|/__ __\\/  _ \\  /  __// \\  /|/  _ \\\n" +
                "  / \\  |  \\/|| || / \\|| |\\ ||  / \\  | / \\|  |  \\  | |\\ ||| / \\|\n" +
                "  | |  |    /| || |-||| | \\||  | |  | |-||  |  /_ | | \\||| |-||\n" +
                "  \\_/  \\_/\\_\\\\_/\\_/ \\|\\_/  \\|  \\_/  \\_/ \\|  \\____\\\\_/  \\|\\_/ \\|\n" +
                "                                                               " + "\033[0m");

        System.out.println("WELCOME!");
    }

    public void printPlayer(){
        System.out.println("This is all players.");
        this.playerMap.forEach((key, value) ->{
            System.out.println("Name : " + key + " , Money : " + String.valueOf(value.getScore()));
        });
    }

    public void process(){
        dozen.shuffleDozen();

        // show dealer's first card
        this.playerMap.forEach((key,value)->{
            if(value.getDealerStatus()){
                System.out.printf("Hello, %s . Dealer should be first and face up hand card.\n", key);
                Card temp = dozen.dealCard();
                value.addHandCard(temp);
                System.out.println("Dealer face up card: " + temp.getFace());
            }
        });

        // each player bet and hit/stand
        this.playerMap.forEach((key,value)->{
            if(!value.getDealerStatus()){
                System.out.printf("Hello, %s . That's your first card\n", key);
                Card temp = dozen.dealCard();
                value.addHandCard(temp);
                System.out.println("Your first card: " + temp.getFace());

                System.out.println("Do you want to bet? (y/n)");
                String ans = in.nextLine();
                getInput.Exit(ans);
                if(ans.charAt(0) == 'y'){
                    int bet = 0;
                    while (bet < 1 || bet > value.getScore()) {
                        System.out.printf("Hi %s ! How much you want to bet? (1-%d)\n",key,value.getScore());
                        ans = in.nextLine();
                        getInput.Exit(ans);
                        bet = getInput.getInt(ans);
                    }
                    value.setBet(bet);

                    // if he bet, add two more cards for him
                    temp = dozen.dealCard();
                    value.addHandCard(temp);
                    temp = dozen.dealCard();
                    value.addHandCard(temp);
                    value.printHands();


                    while (true) {
                        System.out.println("Do you want to hit? y:hit n:stand");
                        ans = in.nextLine();
                        getInput.Exit(ans);
                        if (ans.charAt(0) == 'y'){
                            Card temp2 = dozen.dealCard();
                            value.addHandCard(temp2);
                            System.out.println("Your card: " + temp2.getFace() );
                            value.printHands();
                            if(value.calculateSum() > 31){
                                System.out.println("\033[31;31;1m" + "Oops! You Bust" + "\033[0m");
                                value.setBust(true);
                                break;
                            }
                        }else {
                            break;
                        }
                    }
                }

            }
        });

        // dealer finish deal card
        this.playerMap.forEach((key,value)->{
            if(value.getDealerStatus()){
                System.out.printf("Hello, %s . You are this round's dealer.\n", key);
                Card temp = dozen.dealCard();
                value.addHandCard(temp);
                temp = dozen.dealCard();
                value.addHandCard(temp);
                value.printHands();

                while (true){
                    try {
                        Thread.currentThread().sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    if(value.calculateSum() > 27){
                        break;
                    } else if (value.getHasA() == true && value.calculateSum() + 10 > 27) {
                        break;
                    }
                    System.out.println("Keep deal");
                    temp = dozen.dealCard();
                    value.addHandCard(temp);
                    value.printHands();
                }

                if(value.calculateSum() > 31){
                    System.out.println("\033[31;31;1m" + "Oops! You Bust" + "\033[0m");
                    value.setBust(true);
                }

                // calculate dealer's optimal value
                if(value.getBust() == false) {
                    int tempSum = value.calculateSum();
                    if(value.getHasA() == true && (tempSum + 10) <= 31){
                        this.roundDealerValue = tempSum + 10;
                    }else {
                        this.roundDealerValue = value.calculateSum();
                    }
                }
            }
        });

        System.out.println("============================================");
        System.out.println("Here are this round's result.");
        // Calculate and round finish
        this.playerMap.forEach((key,value)->{
            if(!value.getDealerStatus()){
                System.out.printf("Hello %s.\n",key);
                value.printHands();
                if(value.getBust() == false && value.getBet() != 0){
                    if(value.getHasA() == false  && value.calculateSum() > this.roundDealerValue) {
                        System.out.println("\033[31;31;1m" + "CONGRATULATIONS! YOU WIN!!!" + "\033[0m");
                        System.out.printf("You earned %d from dealer\n", value.getBet());
                        this.roundDealerMoneyChange -= value.getBet();
                        value.changeMoney(value.getBet());
                        System.out.printf("Now you have %d\n", value.getScore());
                        value.clearHand();
                    } else if (value.getHasA() == true  &&((value.calculateSum() + 10 > this.roundDealerValue && value.calculateSum() + 10 <= 31) || value.calculateSum() > this.roundDealerValue)) {
                        System.out.println("\033[31;31;1m" + "CONGRATULATIONS! YOU WIN!!!" + "\033[0m");
                        System.out.printf("You earned %d from dealer\n", value.getBet());
                        this.roundDealerMoneyChange -= value.getBet();
                        value.changeMoney(value.getBet());
                        System.out.printf("Now you have %d\n", value.getScore());
                        value.clearHand();
                    }else {
                        System.out.println("You are lower than dealer");
                        System.out.printf("You lost %d to dealer\n", value.getBet());
                        this.roundDealerMoneyChange += value.getBet();
                        value.changeMoney(-value.getBet());
                        System.out.printf("Now you have %d\n", value.getScore());
                        value.clearHand();
                    }
                } else if (value.getBust() == true) {
                    System.out.println("You bust.");
                    System.out.printf("You lost %d to dealer\n", value.getBet());
                    this.roundDealerMoneyChange += value.getBet();
                    value.changeMoney(-value.getBet());
                    System.out.printf("Now you have %d\n", value.getScore());
                    value.clearHand();
                }else {
                    System.out.println("You didn't bet, so your money not change.");
                    value.clearHand();
                }
            }
            System.out.println("============================================");
        });

        // Calculate dealer's money
        this.playerMap.forEach((key,value)->{
            if(value.getDealerStatus()) {
                System.out.printf("Hello %s. You are this round's dealer.\n",key);
                value.changeMoney(this.roundDealerMoneyChange);
                System.out.printf("Now you have %d\n", value.getScore());
                value.clearHand();
            }
        System.out.println("============================================");
        });
    }


}
