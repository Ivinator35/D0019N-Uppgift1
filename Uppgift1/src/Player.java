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

    // används för att kunna se inventory utanför klassen, behövs i bla dragonTreasure
    public ArrayList<Item> getPlayerInv() {
        return this.playerInv;
    }

    public void setPlayerHP(int hp) {this.playerHp = hp;}

    public Room1 getCurrentRoom(){return currentRoom;}

    // metod för att kolla om spelare har nyckel i sitt inventory
    public boolean checkInvKey(){
        for (Item item : this.playerInv) {
            if (item.getItemID() == 3) {
                return true;
            }
        }
        return false;
    }

    // metod som tar bort den första nyckeln spelaren tar upp first in first out
    public void removeAKey(){
        for (Item item : this.playerInv) {
            if (item.getItemID() == 3) {
                this.playerInv.remove(item);
                break;
            }
        }
    }

    // metod för att flytta en spelare mellan rum
    public void move(String dir){
        // skapar ett rums variabel och hämtar nästa rum från dörren i riktningen "dir"
        Room1 nextRoom = currentRoom.getExit(dir);
        String command;
        // Om det finns en dörr/rum åt riktningen "dir" och dörren inte är låst
        if (nextRoom != null && !currentRoom.checkLock(dir)){
            //Kollar om rummet har ett monster i sig och isåfall startar den en strid
            if (nextRoom.getMonster() != null){
                nextRoom.doBattle(this);
                if (this.playerHp > 0){
                    // ändra currentRoom till nextRoom och skriv ut nextRoom "Narrative"
                    currentRoom = nextRoom;
                    nextRoom.doNarrative();
                }
            } else{
                // ändra currentRoom till nextRoom och skriv ut nextRoom "Narrative"
                currentRoom = nextRoom;
                nextRoom.doNarrative();
            }
        } else if (nextRoom != null && currentRoom.checkLock(dir)) {
            // använder metod checkLock för se om dörren är låst och skriver meddelande om sant
            System.out.println("Dörren är låst");
            // använder metod för att se om player har nyckel och frågar om spelaren vill använda det
            if (checkInvKey()){
                System.out.print("Du har en nyckel som kan öppna dörren, vill du använda den? [Y/N] > ");
                command = input.nextLine().toLowerCase();
                if (command.equals("y")){
                    // tar bort nyckeln och låser upp dörrarna och återkallar move metoden rekkursivt
                    removeAKey();
                    currentRoom.unlockDoors(dir);
                    move(dir);
                }
                else {currentRoom.doNarrative();}
            }

        } else {
            // skrivs ut om det inte finns någon dörr eller om man skrivit något annat än n,s,e eller w
            System.out.println("Du kan inte gå i denna riktning!");
            System.out.println(" ");
            currentRoom.doNarrative();
        }
    }

    // lägger till föremål(ex vapen) i spelarens inventory
    public void addItem(Item item) {
        playerInv.add(item);
    }
    // Lägger till vapen i inventory och ändrar player dmg till vapnets
    public void addWeapon(Item weapon) {
        if (currentWeapon == null) {
            playerInv.add(weapon);
            this.playerDmg = weapon.getAmp();
            this.currentWeapon = weapon; 
        } else {
            playerInv.add(weapon);
            System.out.println("Du fick ett nytt vapen. För att byta till det tryck [i]."); 
        }
    }
    // Metod för att kolla sin spelares inventory
    public void checkWeaponInv() {
        int i = 0;
        Item input;
        int command;
        // loopar igenom player inventory och skriver ut vad man har
        // Switch används för att se vilken typ av Item det är
        for (Item item : playerInv) {
            i++;
            switch (item.getItemID()) {
                case 1:
                    System.out.println(i + ". " + item.getItemName() + " DMG: " + item.getAmp());
                    break; 
                case 2:
                    System.out.println(i + ". " + item.getItemName() + " HP: " + item.getAmp());
                    break;
                default:
                    System.out.println(i + ". " + item.getItemName());
                    
            }
            
        }
        System.out.println("Skriv nummret för de föremål du vill använda");
        // fel hantering med try cath om man valt något som är utanför inventory size
        try {
            command = this.input.nextInt();
            
            if (command > playerInv.size()) {
                System.out.println("Du har inte så många grejer!");
            } else {
                input = playerInv.get(command - 1);
                // Switch för de olika typer av föremålen
                // Man ska kunna byta vapen och använda drycker annars skrivs bara en enkel text ut för nycklar och skatter
                switch (input.getItemID()) {
                    case 1:
                        currentWeapon = input;
                        this.playerDmg = currentWeapon.getAmp();
                        System.out.println("Du använder nu " + input.getItemName() + ".");
                        break; 
                    case 2: 
                        if (this.playerHp + input.getAmp() > 10) {
                            this.playerHp = 10;
                            System.out.println("Du är tillbaka till max HP!: " + this.playerHp);
                        } else {
                            this.playerHp = this.playerHp + input.getAmp();
                            System.out.println("Du fick tillbaka " + input.getAmp() + " HP.");
                        }
                        break; 
                    case 3:
                        System.out.println(i + ". För att använda nyckeln, hitta en låst dörr." );
                        break; 
                    case 4:
                        System.out.println(i + ". Grattis, du har hittat en skatt! Försök hitta ut nu!" );
                        break; 
                }
            }
            // ifall man skriver något annat än en siffra fångas även det
        } catch(Exception e) {
            System.out.println("Skriv respektive siffra!");
        }

    }
// Metod för att plocka upp items, än igen med switch case då föremål behandlas olika nör spelare tar upp dem
    // Tar även bort items ur rummet när det lagts till i inventoryt
    public void pickupItems(Room1 room) {
        for (Item item : room.getRoomItems()) {
            switch (item.getItemID()) {
                case 1:
                    addWeapon(item);                    
                    break; 
                default:
                    addItem(item);
            }
            System.out.println("Du plockade upp '" + item.getItemName() + "'");
        }

        room.removeItems();
    }
}
