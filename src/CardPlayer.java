import java.util.LinkedHashMap;

public class CardPlayer extends player implements CardPlayerMethods{

    private Boolean dealer = false;

    private Boolean hasA = false;

    private LinkedHashMap<String, Card> handCards = new LinkedHashMap<String, Card>();

    private int sum = 0;

    private int bet = 0;

    private Boolean bust = false;

    public Boolean getBust(){return this.bust;}

    public void setBust(Boolean bust){this.bust = bust;}

    public Boolean getHasA(){return this.hasA;}
    public Boolean getDealerStatus(){return this.dealer;};

    public CardPlayer(){}
    public void setBet(int bet){this.bet = bet;}
    public int getBet(){return this.bet;}
    public void setMoney(int money){
        this.setScore(money);
    }
    public void changeMoney(int money){
        setScore(getScore() + money);
    }
    public void setDealer(Boolean dealer){
        this.dealer = dealer;
    }

    // add a card to the player's hand card.
    public void addHandCard(Card card){
        this.handCards.put(card.getFace(),card);
    }

    // calculate the player's hand value sum
    public int calculateSum(){
        this.sum = 0;
        this.handCards.forEach((key,value)->{
            if(key.charAt(1) == 'A'){
                this.hasA = true;
            }
            this.sum += value.getValue();
        });
        return this.sum;
    }

    // clear the player's card, bet money and etc. after each round
    public void clearHand(){
        this.sum = 0;
        this.bet = 0;
        this.hasA = false;
        this.handCards.clear();
        this.bust = false;
        this.dealer = false;
    }

    // show player's hand card and hand value
    public void printHands(){
        System.out.printf("You have : ");
        this.handCards.forEach((key, value)->{
            System.out.printf(key + "\t");
        });
        int tempSum = this.calculateSum();
        System.out.printf("Total value: " + String.valueOf(tempSum));
        if(this.hasA == true){
            System.out.printf(" or " + String.valueOf(tempSum + 10));
        }
        System.out.printf("\n");
    }
}
