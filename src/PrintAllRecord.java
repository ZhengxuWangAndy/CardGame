import java.util.HashMap;

// use for print the current teams players and their scores situation
public class PrintAllRecord {
    PrintAllRecord(){}
    public void printRecord(HashMap<String, team> teamMap){
        teamMap.forEach((key, value) ->{
            System.out.printf("Team : %s \t\t Score: %d  \n",key,value.getScore());
            value.playerMap.forEach((pKey, pValue) ->{
                System.out.printf("\tPlayer : %s \t\t Score: %d  \n",pKey,pValue.getScore());
            });
        });
    }
}
