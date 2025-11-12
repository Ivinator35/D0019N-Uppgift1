
public class Door {
    private Room nextRoom;
    private String direction;

    public Door(String direction, Room nextRoom){
        this.direction = direction;
        this.nextRoom = nextRoom;
    }

    public Room getNextRoom(){
        return nextRoom;
    }
}
