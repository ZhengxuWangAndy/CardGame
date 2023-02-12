import java.util.Scanner;

// let user select which game to play
public class selectGame extends Game{
    GetInput getInput = new GetInput();
    public selectGame(){

    }

    // interact with player, help them choose which game to play.
    public String select(){
        String type = "";
        System.out.println("Which game do you want to play?");
        System.out.println("Enter 1 tic tac toe");
        System.out.println("Enter 2 orders and chaos");
        System.out.println("Enter 3 Trianta ena");
        System.out.println("Enter exit quit the game");

        while (!(type.equals("1") || type.equals("2") || type.equals("3"))) {
            System.out.println("Please input a valid number");
            Scanner in = new Scanner(System.in);
            type = in.nextLine();
            this.getInput.Exit(type);
        }
        return type;
    }
}
