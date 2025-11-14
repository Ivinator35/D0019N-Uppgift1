/*public class Room {

    private String roomDesc;
    private Door north, south, west, east;

    public Room(String roomDesc){
        this.roomDesc = roomDesc;
    }

    public String getRoomDesc(){
        return roomDesc;
    }

    public void createRoomExit(String dir, Room nextRoom){
         switch (dir){
             case "n" :
                 Door north = new Door(dir, nextRoom);
                 this.north = north;
                 break;
             case "s" :
                 Door south = new Door(dir, nextRoom);
                 this.south = south;
                 break;
             case "w" :
                 Door west = new Door(dir, nextRoom);
                 this.west = west;
                 break;
             case "e" :
                 Door east = new Door(dir, nextRoom);
                 this.east = east;
                 break;
             default:
                 break;
         }
    }

    public Room getNextRoom(String dir) {
        switch (dir) {
            case "n":
                return north.getNextRoom();

            case "s":
                return south.getNextRoom();

            case "w":
                return west.getNextRoom();

            case "e":
                return east.getNextRoom();

            default:
                return null;

        }
    }

    public void doNarrative(){
        System.out.println(roomDesc);
        if (this.north != null){ System.out.println("Det finns en dörr åt norr");    }
        if (this.south != null){ System.out.println("Det finns en dörr åt söder");    }
        if (this.east != null){ System.out.println("Det finns en dörr åt öst");    }
        if (this.west != null){ System.out.println("Det finns en dörr åt väst");    }
    }
}
*/