import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    private Scanner input = new Scanner(System.in);

    private String playerName;
    private Room1 currentRoom;
    private int playerHp = 10;
    private int playerDmg = 1;

    private ArrayList<Item> potionInv = new ArrayList<Item>();
    private Item currentWeapon;
    private ArrayList<Item> weaponInv = new ArrayList<Item>();

    // konstruktor för spelar klassen, tar namn och rum man börjar i
    public Player(String name, Room1 startRoom) {
        this.playerName = name;
        this.currentRoom = startRoom;
    }

    // getter och setters för namn samt currentRoom( används inte än men kan vara bra till del 2)
    public String getPlayerName() {
        return playerName;
    }

    public int getPlayerHP() {
        return this.playerHp;
    }

    public int getPlayerDMG() {
        return this.playerDmg;
    }

    public void setCurrentRoom(Room1 room){this.currentRoom = room;}

    public Room1 getCurrentRoom(){return currentRoom;}

    // metod för att flytta en spelare mellan rum
    public void move(String dir){
        // skapar ett rums variabel och hämtar nästa rum från dörren i riktningen "dir"
        Room1 nextRoom = currentRoom.getExit(dir);
        if (nextRoom != null && !currentRoom.checkLock(dir)){
            // Om det finns en dörr/rum åt riktningen "dir" och dörren inte är låst
            // ändra currentRoom till nextRoom och skriv ut nextRoom "Narrative"
            currentRoom = nextRoom;
            nextRoom.doNarrative();
        } else if (nextRoom != null && currentRoom.checkLock(dir)) {
            // använder metod checkLock för se om dörren är låst och skriver meddelande om sant
            System.out.println("Dörren är låst");
            currentRoom.doNarrative();
        } else {
            // skrivs ut om det inte finns någon dörr eller om man skrivit något annat än n,s,e eller w
            System.out.println("Du kan inte gå i denna riktning!");
            System.out.println(" ");
            currentRoom.doNarrative();
        }
    }

    public void addPotion(Item potion) {
        potionInv.add(potion);
    }

    public void addWeapon(Weapon weapon) {
        if (currentWeapon == null) {
            weaponInv.add(weapon);
            this.playerDmg = weapon.getAmp();
            this.currentWeapon = weapon; 
        } else {
            weaponInv.add(weapon);
            System.out.println("Du fick ett nytt vapen. För att byta till det tryck i."); 
        }
    }

    public void checkWeaponInv() {
        int i = 0;
        String command;

        for (Item weapon : weaponInv) {
            i++;
            System.out.println(i + ". " + weapon.getItemName() + " DMG: " + weapon.getAmp()); 
        }
        System.out.println("Vill du byta vapen y/n?");
        command = input.nextLine().toLowerCase();
            
        if (command.equals("y")) {
            System.out.println("Skriv nummret till det vapen du vill byta till");
            command = input.nextLine();
            
            try {
                if (Integer.parseInt(command) > weaponInv.size()) {
                    System.out.println("Du har inte så många vapen");
                } else {
                    currentWeapon = weaponInv.get(Integer.parseInt(command) - 1);
                    this.playerDmg = currentWeapon.getAmp();
                }

            }
            catch (NumberFormatException e) {
                System.out.println("Skriv en siffra");
                
            }
        }
    }
}
