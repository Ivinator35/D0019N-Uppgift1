public class Item {
    protected String itemType;
    protected String itemName;
    protected String itemDesc;
    protected int DMGamp;
    protected int HPamp;

    public Item(String name){
        this.itemName = name;
    }

    public String getItemName(){return itemName;}

    public String getItemType(){return itemType;}

    public String getItemDesc(){return itemDesc;}

    public int getDMGamp(){return this.DMGamp;}

    public int getHPamp(){return this.HPamp;}

    public void useStatIncrease(Player player){
        player.setPlayerHP(player.getPlayerHP() + this.HPamp);
        player.setPlayerDMG(player.getPlayerDMG() + this.DMGamp);
    }

    public void removeStatIncrease(Player player){
        player.setPlayerHP(player.getPlayerHP() - this.HPamp);
        player.setPlayerDMG(player.getPlayerDMG() - this.DMGamp);
    }


}
