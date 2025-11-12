public static void main(String[] args) {

    Room currentroom;

    Room entre = new Room("Snoppen luktar svavel svär fr");
    Room rum1 = new Room("Snoppen luktar svavel svär fr men nummer 1");

    entre.createRoomExit("n", rum1);

    currentroom = entre.getNextRoom("n");

    System.out.print(currentroom.getRoomDesc());
}