import java.util.HashMap;

public class Room1 {
    private String roomDesc;
    private HashMap<String, Room1> roomExits = new HashMap<String, Room1>();
    private HashMap<String, Door> doorDir = new HashMap<String, Door>

    public void setRoomDesc(String roomDesc) {
        this.roomDesc = roomDesc;
    }

    public void setExits (String dir, Room1 nextRoom, boolean isLocked) {
        Door newDoor = new Door()
        newDoor.CreateDoor(dir, isLocked)
        
        this.roomExits.put(dir, nextRoom);
        this.doorDir.put(dir, )
    }

    public Room1 getExit (String dir) {
        this.roomExits.get(dir);
    }



}