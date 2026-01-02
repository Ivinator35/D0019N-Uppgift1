
public class Door {
    private Room1 nextRoom;
    private boolean isLocked;

    // konstruktor för dörrar, tar nästa rum och boolean för lås "status"
    public Door(Room1 nextRoom, boolean isLocked){
        this.nextRoom = nextRoom;
        this.isLocked = isLocked;
    }

    // getters för rum och lås
    public Room1 getNextRoom(){
        return nextRoom;
    }

    public boolean getLock(){
        return isLocked;
    }

    public void unlockDoor(){
        this.isLocked = false;
    }

}
