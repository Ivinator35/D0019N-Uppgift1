public class Weapon extends Item{


    public Weapon(String name, int DMGamp){
        super(name);
        this.DMGamp = DMGamp;
        this.itemType = "Weapon";
    }
    public void useStatIncrease(Player player){
        player.setPlayerDMG(player.getPlayerDMG() + DMGamp);
    }
    public void removeStatIncrease(Player player){
        player.setPlayerDMG(player.getPlayerDMG() - DMGamp);
    }
}

