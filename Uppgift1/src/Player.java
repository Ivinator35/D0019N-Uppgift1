import java.util.ArrayList;
import java.util.Scanner;

public class Player {

    private Scanner input = new Scanner(System.in);
    private String playerName;
    private Room1 currentRoom;

    private int playerHP;
    private int maxPlayerHP;
    private int playerDMG;

    private ArrayList<Item> inventory = new ArrayList<Item>();

    // konstruktor för spelar klassen, tar namn och rum man börjar i
    public Player(String name, Room1 startRoom) {
        this.playerName = name;
        this.currentRoom = startRoom;
        this.playerHP = 10;
        this.maxPlayerHP = 10;
        this.playerDMG = 1;
    }

    // getter och setters för spelare
    public String getPlayerName() {
        return playerName;
    }

    public int getPlayerHP() {return this.playerHP;   }
    public int getMaxPlayerHP() {return this.maxPlayerHP;}
    public void setPlayerHP(int HP) {this.playerHP = HP;}

    public int getPlayerDMG() {return this.playerDMG;}
    public void setPlayerDMG(int DMG) {this.playerDMG = DMG;}

    public void addItem(Item item){
        inventory.add(item);
        if (item.getItemType().equals("Weapon") && item.getItemType() != null){
            item.useStatIncrease(this);
        }
    }

    public void dropItem(Item item){
        if (item.getItemType().equals("Weapon")){
            item.removeStatIncrease(this);
            inventory.remove(item);
        }
    }

    public Room1 getCurrentRoom() {return this.currentRoom;   }

    public void playerPotionCheck(){
        boolean potionTemp = false;
        for (Item item : inventory) {
            if (item.getItemType().equals("Potion") && this.playerHP <= this.maxPlayerHP) {
                potionTemp = true;
                break;
            }
        }
        if (potionTemp) {
            int i = 0;
            while (potionTemp && this.playerHP < this.maxPlayerHP) {
                int index = 0;
                for (Item item : inventory) {
                    if (item.getItemType().equals("Potion")) {
                        index++;
                    }
                }
                if (index > 0) {
                    ArrayList<Item> tempPotArr = new ArrayList<>();
                    System.out.println("\nDu har " + this.playerHP + " HP kvar, du kan använda en hälsodryck för få tillbaka HP:\n");
                    for (Item item : inventory) {
                        if (item.getItemType().equals("Potion")) {
                            i++;
                            tempPotArr.add(item);
                            System.out.println("[" + i + "] " + item.getItemName() + " - +" + item.getHPamp() + " HP");
                        }
                    }
                    System.out.print("\nVälj vilken dryck du vill använda,\nEller skriv [0] för att starta striden > ");
                    int choice = input.nextInt() -1;
                    if (choice == -1 || tempPotArr.get(choice) != null) {
                        if (choice == -1) {
                            potionTemp = false;
                            System.out.println("\nStriden startar!");
                            break;
                        }
                        else {
                            Item tempPot = tempPotArr.get(choice);
                            tempPot.useStatIncrease(this);
                            System.out.println("Hälsodryck " + tempPot.getItemName() + " användes!, "+ getPlayerName()+ ": "+ getPlayerHP()+ " HP");
                            inventory.remove(tempPot);
                        }
                        tempPotArr.clear();
                        i = 0;
                    }
                    else {
                        return;
                    }
                }else {
                    return;
                }
            }
        }
    }

    // metod för att flytta en spelare mellan rum
    public void move(String dir) {
        // skapar ett rums variabel och hämtar nästa rum från dörren i riktningen "dir"
        Room1 nextRoom = currentRoom.getExit(dir);
        if (this.playerHP > 0) {
            if (nextRoom != null && !currentRoom.checkLock(dir)) {
                // Om det finns en dörr/rum åt riktningen "dir" och dörren inte är låst

                // ifall det är bråk i rummet ska doBattle köras innan current room byts och loot ges ut
                if (nextRoom.getMonsters().size() > 0) {
                    
                    System.out.print("\nDet finns " + nextRoom.getMonsters().size() + " monster i rummet!\n-Vill du kaosa ellä(y/n)? > ");
                    String command = input.nextLine().toLowerCase();
                    if (command.equals("y")) {
                        // Läser av inventory och skriver ut ifall du kan "heala" innan striden e.g. har en potion och inte maxhp
                        playerPotionCheck();

                        nextRoom.doBattle(this);
                        // ändra currentRoom till nextRoom och skriv ut nextRoom "Narrative"
                        if (this.playerHP > 0) {
                            currentRoom = nextRoom;
                            nextRoom.doNarrative();
                        }
                    } else {
                        currentRoom.doNarrative();
                    }
                } else {
                    // ändra currentRoom till nextRoom och skriv ut nextRoom "Narrative"
                    currentRoom = nextRoom;
                    nextRoom.doNarrative();
                }


            } else if (nextRoom != null && currentRoom.checkLock(dir)) {
                // använder metod checkLock för se om dörren är låst och skriver meddelande om sant
                System.out.println("Dörren är låst men du kan öppna den med en nyckel!");
                for (Item item : inventory) {
                    if (item.getItemType().equals("Key")) {

                        System.out.print("\nDu har en nyckeln " + item.getItemName() + " som kan öppna dörren! \n-Vill du använda den(y/n)? > ");
                        String command = input.nextLine().toLowerCase();
                        if (command.equals("y")) {
                            currentRoom.unlockDoors(dir);
                            inventory.remove(item);
                            break;
                        }
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
    }
}
