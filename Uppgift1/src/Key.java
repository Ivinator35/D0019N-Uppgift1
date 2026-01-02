public class Key extends Item {
    protected String itemType;

    public Key(String name) {
        super(name);
        this.itemType = "Key";
    }
    public String getItemType(){return itemType;}

}
