public abstract class CardVariable {
    private String face;
    private int value;
    public void setFace(String face){
        this.face = face;
    }
    public String getFace(){
        return  this.face;
    }
    public void setValue(int value){
        this.value = value;
    }
    public int getValue(){
        return  this.value;
    }
}
