import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Room1 {
    private String roomDesc;

    // Skapar en HashMap för att koppla ett rums riktningar till en dörr
    private HashMap<String, Door> roomExits = new HashMap<String, Door>();

    private ArrayList<Item> items = new ArrayList<Item>();

    private ArrayList<Monster> monsters = new ArrayList<Monster>();

    // konstruktor för Room tar endast rumsbeskrivning
    public Room1(String roomDesc){
        this.roomDesc = roomDesc;
    }

    public ArrayList<Item> getItems(){return items;}
    public ArrayList<Monster> getMonsters(){return monsters;}

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
        roomExits.get(dir).unlockDoor();
        this.getExit(dir).roomExits.get(oppositeDir(dir)).unlockDoor();
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

    public void addKey(String name){
        Item NewKey = new Key(name);
        items.add(NewKey);
    }

    public void addWeapon(String name, int DMGamp){
        Item newWeapon = new Weapon(name, DMGamp);
        items.add(newWeapon);
    }

    public void addPotion(String name, int HPamp){
        Item newPotion = new Potion(name, HPamp);
        items.add(newPotion);
    }

    public void clearItems(){
        ArrayList<Item> tempItems = new ArrayList<Item>(items);
        for (Item item : tempItems){
            items.remove(item);
        }
    }

    public void addMonster(String name, int HP, int DMG){
        Monster NewMonster = new Monster(name, HP, DMG);
        monsters.add(NewMonster);
    }

    public void doBattle(Player player){
        ArrayList<Monster> tempMonsters = new ArrayList<Monster>(monsters);
        for (Monster monster : tempMonsters){
            System.out.println(" ");
            System.out.println(monster.getMonsterDesc() + "\n" );
            while (monster.getHP() > 0 && player.getPlayerHP() > 0){
                monster.setHP(monster.getHP() - player.getPlayerDMG());
                System.out.println(player.getPlayerName() + " attackerar " + monster.getName() + " och gör "+ player.getPlayerDMG()+ " DMG");
                if (monster.getHP() <= 0){
                    System.out.println(monster.getName() + " har blivit besegrad!");
                    monsters.remove(monster);
                    break;
                }else { System.out.println(monster.getName() + " har " + monster.getHP() + " HP kvar");}

                player.setPlayerHP(player.getPlayerHP() - monster.getDMG());
                System.out.println(monster.getName() + " attakerar dig och gör, " + monster.getDMG() + " DMG");
                if (player.getPlayerHP() <= 0){
                    System.out.println("Du har blivit besegrad!");
                    break;
                } else {System.out.println("Du har " + player.getPlayerHP() + " HP kvar");}
            }

        }
    }
    // skriver ut rumsbeskrivningar och rummets dörrar
    public void doNarrative(){
        System.out.println(" ");
        System.out.println(roomDesc);
        if (!items.isEmpty()){
            for (Item item : items){
                System.out.println("I rummet ligger " + item.getItemName() + ", av typen " + item.getItemType());
            }
            System.out.println("-Klicka [p] för att ta upp föremål! \n");
        }

        // for loop för att skriva ut alla riktningar med olåsta dörrar ur HashMapen
        for (String dir : roomExits.keySet()) {
            if (checkLock(dir) == false){
                System.out.println("-Du kan gå åt [" + dir + "]");
            }
        }
        // for loop för att skriva ut alla riktningar med låsta dörrar
        for (String dir : roomExits.keySet()) {
            if (checkLock(dir) == true){
                System.out.println("-Det finns en låst dörr åt [" + dir + "]");
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
}