import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    private Scanner input = new Scanner(System.in);

    private String playerName;
    private Room1 currentRoom;
    private int playerHp = 10;
    private int playerDmg = 1;

    private Item currentWeapon;
    private ArrayList<Item> playerInv = new ArrayList<Item>();

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

    public ArrayList<Item> getPlayerInv() {
        return this.playerInv;
    }

    public void setPlayerHP(int hp) {this.playerHp = hp;}

    public void setCurrentRoom(Room1 room){this.currentRoom = room;}

    public Room1 getCurrentRoom(){return currentRoom;}

    // metod för att flytta en spelare mellan rum
    public void move(String dir){
        // skapar ett rums variabel och hämtar nästa rum från dörren i riktningen "dir"
        Room1 nextRoom = currentRoom.getExit(dir);
        String command;

        if (nextRoom != null && !currentRoom.checkLock(dir)){
            // Om det finns en dörr/rum åt riktningen "dir" och dörren inte är låst
            // ändra currentRoom till nextRoom och skriv ut nextRoom "Narrative"
            if (nextRoom.getMonster() != null){
                nextRoom.doBattle(this);
            }
            if (this.playerHp > 0){
                currentRoom = nextRoom;
                nextRoom.doNarrative();
            }
            currentRoom = nextRoom;
            nextRoom.doNarrative();

            
        } else if (nextRoom != null && currentRoom.checkLock(dir)) {
            // använder metod checkLock för se om dörren är låst och skriver meddelande om sant
            System.out.println("Dörren är låst");
            boolean temp = true; // ersätt med metod för att se om player har nyckel
            if (temp == true){
                System.out.print("Du har en nyckel som kan öppna dörren, vill du använda den? [Y/N] > ");
                command = input.nextLine().toLowerCase();
                if (command.equals("y")){
                    // lägg in -- Remove key from inventory
                    currentRoom.unlockDoors(dir);
                    move(dir);
                }
            }
            currentRoom.doNarrative();
        } else {
            // skrivs ut om det inte finns någon dörr eller om man skrivit något annat än n,s,e eller w
            System.out.println("Du kan inte gå i denna riktning!");
            System.out.println(" ");
            currentRoom.doNarrative();
        }
    }

    public void addItem(Item item) {
        playerInv.add(item);
    }

    public void addWeapon(Item weapon) {
        if (currentWeapon == null) {
            playerInv.add(weapon);
            this.playerDmg = weapon.getAmp();
            this.currentWeapon = weapon; 
        } else {
            playerInv.add(weapon);
            System.out.println("Du fick ett nytt vapen. För att byta till det tryck 'i'."); 
        }
    }

    public void checkWeaponInv() {
        int i = 0;
        Item input;
        int command;

        for (Item item : playerInv) {
            i++;
            switch (item.getItemID()) {
                case 1:
                    System.out.println(i + ". " + item.getItemName() + " DMG: " + item.getAmp());
                    break; 
                case 2:
                    System.out.println(i + ". " + item.getItemName() + " HP: " + item.getAmp());
                    break;
                case 3:
                    System.out.println(i + ". " + item.getItemName());
                    break;
                case 4:
                    System.out.println(i + ". " + item.getItemName());
                    break;
            }
            
        }
        System.out.println("Skriv nummret för de föremål du vill använda");
        
        try {
            command = this.input.nextInt();
            
            if (command > playerInv.size()) {
                System.out.println("Du har inte så många vapen!");
            } else {
                input = playerInv.get(command - 1);

                switch (input.getItemID()) {
                    case 1:
                        currentWeapon = input;
                        this.playerDmg = currentWeapon.getAmp();
                        System.out.println("Du använder nu " + input.getItemName() + ".");
                        break; 
                    case 2: 
                        if (this.playerHp + input.getAmp() > 10) {
                            this.playerHp = 10;
                            System.out.println("Du fick tillbaka " + (10 - this.playerHp) + " HP.");
                        } else {
                            this.playerHp = this.playerHp + input.getAmp();
                            System.out.println("Du fick tillbaka " + input.getAmp() + " HP.");
                        }
                        break; 
                    case 3:
                        System.out.println(i + ". För att använda nyckeln, hitta en låst dörr." );
                        break; 
                    case 4:
                        System.out.println(i + ". Grattis, du har hittat en skatt!" );
                        break; 
                }
            }
        } catch(Exception e) {
            System.out.println("Skriv respektive siffra!");
        }

    }

    public void pickupItems(Room1 room) {
        for (Item item : room.getRoomItems()) {
            switch (item.getItemID()) {
                case 1:
                    addWeapon(item);
                    break; 
                case 2:
                    addItem(item);
                    break;
                case 3:
                    addItem(item);
                    break;
                case 4:
                    addItem(item);
                    break;
            }
        }

        room.removeItems();
    }
}
