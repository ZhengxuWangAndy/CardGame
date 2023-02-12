import java.util.HashMap;

// define the team struct, add player at this team and get the whole team score.
public class team extends TeamVariable implements TeamMethods {
    public team(){}
    public void addPlayer(player p){
        playerMap.put(p.getName(),p);
    }
}
