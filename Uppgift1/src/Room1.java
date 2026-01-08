import java.util.ArrayList;
import java.util.HashMap;

public class Room1 {
    private String roomDesc;

    // Skapar en HashMap för att koppla ett rums riktningar till en dörr
    private HashMap<String, Door> roomExits = new HashMap<String, Door>();

    private ArrayList<Item> roomItems = new ArrayList<Item>();


    // konstruktor för Room tar endast rumsbeskrivning
    public Room1(String roomDesc){
        this.roomDesc = roomDesc;
    }

    // Skapar dörrar till ett rum skapar dessutom ett rum i motsatt riktning
    public void setExits (String dir, Room1 nextRoom, boolean isLocked) {
        Door door = new Door(nextRoom, isLocked);
        roomExits.put(dir, door);
        Door back = new Door(this, isLocked);
        nextRoom.roomExits.put(oppositeDir(dir), back);
    }

    // getter för nästa rum genom en dörr, krävdes felhantering för null returns
    public Room1 getExit (String dir) {
        Door door = roomExits.get(dir);
        if (door == null) {
            return null;
        }
        return door.getNextRoom();
    }

    // "Getter" som kollar om en dörr i en specifik riktning är låst,
    // krävdes felhantering för null returns
    public boolean checkLock(String dir){
        Door door = this.roomExits.get(dir);
        if (door == null) {
            return false;
        }
        return door.getLock();
    }

    // skriver ut rumsbeskrivningar och rummets dörrar
    public void doNarrative(){
        System.out.println(" ");
        System.out.println(roomDesc);
        // for loop för att skriva ut alla riktningar med olåsta dörrar ur HashMapen
        for (String dir : roomExits.keySet()) {
            if (checkLock(dir) == false){
                System.out.println("Du kan gå åt [" + dir + "]");
            }
        }
        // for loop för att skriva ut alla riktningar med låsta dörrar
        for (String dir : roomExits.keySet()) {
            if (checkLock(dir) == true){
                System.out.println("Det finns en låst dörr åt [" + dir + "]");
            }
        }
        System.out.println(" ");

    }

    // simpel string metod för att hitta motsatt riktning,
    // används för att lätt kunna skapa dörrar åt två håll
    private String oppositeDir(String dir){
        switch (dir){
            case "n" : return  "s";
            case "s" : return  "n";
            case "w" : return  "e";
            case "e" : return  "w";
            default: return null;
        }
    }

    public void addItems(Item item) {
        roomItems.add(item);
    }

    public void showItems() {
        for (Item item : roomItems) {
            System.out.println(item.getItemDesc());
        }
    }

    
}