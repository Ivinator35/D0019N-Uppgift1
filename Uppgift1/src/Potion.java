public class Potion extends Item{
    private int HPamp;

    public Potion(String name, int HPamp) {
        super(name);
        this.HPamp = HPamp;
        this.itemType = "Potion";
    }

    public void useStatIncrease(Player player){
        int tempHP = player.getPlayerHP() + HPamp;
        if (tempHP <= player.getMaxPlayerHP()){
            player.setPlayerHP(tempHP);
        } else {
            player.setPlayerHP(player.getMaxPlayerHP());
        }

    }

    public int getHPamp(){return this.HPamp;}
}
