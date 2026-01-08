public class Weapon extends Item{
    private int dmgAmp;
    
    public Weapon(String name, String desc, int dmg){
        super(name, desc);
        this.setItemId(1);
        this.dmgAmp = dmg;
    }

    // getter för AMP
    public int getAmp() {
        return this.dmgAmp;
    }


}
