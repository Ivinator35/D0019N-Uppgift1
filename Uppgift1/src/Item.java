abstract class Item {
    protected String itemName;
    protected String itemDesc;
    protected int itemID;
    private int amp;

    public Item(String name) {
        this.itemName = name;
    }

    public String getItemName(){
        return this.itemName;
    }

    //Ett id system för att hålla koll på vad för typ av item det är
    //1 = weapon, 2 = potion, 3 = key, 4 = treasure
    public void setItemId(int id){
        this.itemID = id;
    }

    public int getItemID(){
        return this.itemID;
    }

    public void setItemDesc(String desc){
        this.itemDesc = desc;
    }

    public String getItemDesc(){
        return this.itemDesc;
    }

    public int getAmp(){
        return this.amp;
    }
}
