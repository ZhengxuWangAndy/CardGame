import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Random;

// a dozen of cards
public class Dozen implements DozenMethods{
    private String[] suits={"\033[31;31;1m♦\033[0m","\033[31;31;1m♥\033[0m","♠","♣"};
    private String[] ranks={"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
    private LinkedList<Integer> arrList = new LinkedList<Integer>();
    private LinkedHashMap<String, Card> allCards = new LinkedHashMap<String, Card>();
    private Random rand = new Random();
    private int randomSeed = rand.nextInt();

    // Create a dozen of cards
    public Dozen(){
        int sort = 0;
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 13; j++){
                Card card = new Card();
                card.setFace(suits[i] + ranks[j]);
                if(j > 9){
                    card.setValue(10);
                }
                else {
                    card.setValue(j+1);
                }
                this.allCards.put(String.valueOf(sort), card);
                this.arrList.add(sort);
                sort += 1;
            }
        }

    }
    // shuffle the cards' arrangement
    public void shuffleDozen(){
        Random rand = new Random(randomSeed);
        Collections.shuffle(this.arrList,rand);
//        System.out.println(this.arrList.toString());
    }
    // deal one card
    public Card dealCard(){
        // keep cards never run out
        if(allCards.size() == 0){
            int sort = 0;
            for(int i = 0; i < 4; i++){
                for(int j = 0; j < 13; j++){
                    Card card = new Card();
                    card.setFace(suits[i] + ranks[j]);
                    if(j > 9){
                        card.setValue(10);
                    }
                    else {
                        card.setValue(j+1);
                    }
                    this.allCards.put(String.valueOf(sort), card);
                    this.arrList.add(sort);
                    sort += 1;
                }
            }
            Random rand = new Random(randomSeed);
            Collections.shuffle(this.arrList,rand);
        }
        return allCards.get(arrList.pop().toString());
    }
}
