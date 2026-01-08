public class Potion extends Item{
    private int HPamp;
    
    public Potion(String name, String desc, int hp){
        super(name, desc);
        this.HPamp = hp;
        this.setItemId(2);
    }

    public int getAmp() {
        return this.HPamp;
    }
}
