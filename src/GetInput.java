import java.util.Scanner;


/*
* check input value
* */
public class GetInput {
    Scanner in = new Scanner(System.in);

    public Integer getInt(String ans){
        int num = 0;
        try{
            num = Integer.parseInt(ans);
        }catch (Exception e){
            System.out.println("It's not a valid number.");
            return -1;
        }
        return num;
    }

    public void Exit(String ans){
        if(ans.equals("exit")) {
            System.out.println("Bye!");
            System.exit(0);
        }
    }
}
