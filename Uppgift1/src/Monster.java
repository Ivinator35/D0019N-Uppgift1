public class Monster {
    private String name;
    private int HP;
    private int DMG;
    private String monsterDesc;
    private boolean isBoss;

    public Monster(){
        this.name = "Vanlig Monster";
        this.HP = 8;
        this.DMG = 1;
        this.isBoss = false;
    }

    public Monster(String name, String monsterDesc, int HP, int DMG, boolean isBoss){
        this.name = name;
        this.monsterDesc = monsterDesc;
        this.HP = HP;
        this.DMG = DMG;
        this.isBoss = isBoss;
    }


    public String getName(){return name;}
    public int getDMG(){return DMG;}

    public int getHP(){return HP;}
    public void setHP(int HP){this.HP = HP;}

    public String getMonsterDesc() {return monsterDesc;}
}
