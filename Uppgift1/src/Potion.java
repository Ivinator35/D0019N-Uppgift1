public class Potion extends Item{
    private int HPamp;
    
    public Potion(String name, int hp){
        super(name);
        this.HPamp = hp;
        this.setItemId(2);
    }

    public int getAmp() {
        return this.HPamp;
    }
}
