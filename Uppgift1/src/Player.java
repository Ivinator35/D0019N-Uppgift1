
public class Player {
    private String playerName;
    private Room1 currentRoom;

    // konstruktor för spelar klassen, tar namn och rum man börjar i
    public Player(String name, Room1 startRoom) {
        this.playerName = name;
        this.currentRoom = startRoom;
    }

    // getter och setters för namn samt currentRoom( används inte än men kan vara bra till del 2)
    public String getPlayerName() {
        return playerName;
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
}
