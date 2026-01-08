import java.util.ArrayList;
import java.util.HashMap;

public class Room1 {
    private String roomDesc;
    private Monster monster;

    // Skapar en HashMap för att koppla ett rums riktningar till en dörr
    private HashMap<String, Door> roomExits = new HashMap<String, Door>();

    private ArrayList<Item> roomItems = new ArrayList<Item>();

    public ArrayList<Item> getRoomItems() {
        return this.roomItems;
    }

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

    public void unlockDoors(String dir){
        if (roomExits.get(dir) != null) {
            roomExits.get(dir).unlock();
            this.getExit(dir).roomExits.get(oppositeDir(dir)).unlock();
        }
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

    public void addMonster(String name, String desc, int HP, int DMG, boolean isBoss){
        this.monster = new Monster(name, desc, HP, DMG, isBoss);
    }

    public Monster getMonster(){return monster;}

    // Vi antar att det bara kan vara ett monster per rum
    public void doBattle(Player player){
            System.out.println("\n" + monster.getName() + " har kommit!");
            System.out.println(monster.getMonsterDesc() +"\n");
            while (monster.getHP() > 0 && player.getPlayerHP() > 0){
                monster.setHP(monster.getHP() - player.getPlayerDMG());
                System.out.println(player.getPlayerName() + " attackerar " + monster.getName() + " och gör "+ player.getPlayerDMG()+ " DMG");
                if (monster.getHP() <= 0){
                    System.out.println(monster.getName() + " har blivit besegrad!");
                    this.monster = null;
                    break;
                }
                player.setPlayerHP(player.getPlayerHP() - monster.getDMG());
                System.out.println(monster.getName() + " attakerar dig och gör, " + monster.getDMG() + " DMG");
                if (player.getPlayerHP() <= 0){
                    System.out.println("Du har blivit besegrad!");
                    break;
                } else {System.out.println("Du har " + player.getPlayerHP() + " HP kvar");}
            }
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

        if (!roomItems.isEmpty()) {
            for (Item item : roomItems) {
                System.out.println(item.getItemDesc());
            }
        }
        System.out.println(" ");

    }

    public void unlockDoors(String dir){
        if (roomExits.get(dir) != null) {
            roomExits.get(dir).unlock();
            this.getExit(dir).roomExits.get(oppositeDir(dir)).unlock();
        }
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

    public void pickupItems(Player player) {
        for (Item item : roomItems) {
            switch (item.getItemID()) {
                case 1:
                    player.addWeapon(item);
                case 2:
<<<<<<< HEAD
                    player.addItem(item);
                    break; 
=======
                    player.addPotion(item);
>>>>>>> 5ac8b85b8671f3a664e5bae43cddd5824a9c2ba0
            }
        }
    }

    
}