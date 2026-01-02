public class Monster {
    private String name;
    private int HP;
    private int DMG;
    private String monsterDesc;

    public Monster(){
        this.name = "Vanlig Monster";
        this.HP = 8;
        this.DMG = 1;
    }

    public Monster(String name, int HP, int DMG){
        this.name = name;
        this.HP = HP;
        this.DMG = DMG;
    }

    public String getName(){return name;}
    public int getDMG(){return DMG;}

    public int getHP(){return HP;}
    public void setHP(int HP){this.HP = HP;}

    public void setMonsterDesc(String monsterDesc){this.monsterDesc = monsterDesc;}
    public String getMonsterDesc() {return monsterDesc;}
}
