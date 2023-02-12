public interface CardPlayerMethods {

    public Boolean getBust();
    public void setBust(Boolean bust);
    public Boolean getHasA();
    public Boolean getDealerStatus();
    public void setBet(int bet);
    public int getBet();
    public void setMoney(int money);
    public void changeMoney(int money);
    public void setDealer(Boolean dealer);

    // add a card to the player's hand card.
    public void addHandCard(Card card);

    // calculate the player's hand value sum
    public int calculateSum();

    // clear the player's card, bet money and etc. after each round
    public void clearHand();

    // show player's hand card and hand value
    public void printHands();
}
