
public class Door {
    private Room nextRoom;
    private String direction;
    private boolean isLocked;

    public CreateDoor(String direction, boolean isLocked){
        this.direction = direction;
        this.isLocked = isLocked;
    }

    public Room getNextRoom(){
        return nextRoom;
    }

}
