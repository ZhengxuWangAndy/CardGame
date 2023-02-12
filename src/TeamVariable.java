import java.util.HashMap;

public abstract class TeamVariable {
    private String name = "";
    private int score = 0;
    HashMap<String, player> playerMap = new HashMap<String, player>();

    public void setName(String name){
        this.name = name;
    }

    public void setScore(int score){
        this.score = score;
    }
    public int getScore(){
        // recalculate the whole teams score
        this.score = 0;
        playerMap.forEach((key, value) ->{
            this.score += value.getScore();
        });
        return this.score;
    }

}
