
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class DragonTreasure{
    private Player player;
    private Scanner input = new Scanner(System.in);
    private ArrayList<Room1> rooms = new ArrayList<Room1>();

    // metod för att skapa rum, dörrar och spelare
    public void setupGame() {
        //Skapar rum ur klassen Room1
        Room1 entre = new Room1("Grottöppning");
        Room1 rum1 = new Room1("Rum1");
        Room1 rum2 = new Room1("Rum2");

        // Skapar dörrar/"exits"
        entre.setExits("n", rum1, false);
        rum1.setExits("n", rum2, true);

        // lägger till rum i en ArrayList, ingen använding än så länge men var noterat i uppgiften
        Collections.addAll(rooms, entre, rum1, rum2);

        rum1.addKey("pungalo");
        rum1.addPotion("Pungalo Potion", 4);
        rum1.addPotion("Pungalo Potion Tjack edition", 4);
        rum2.addKey("gargamel Key + taint stank");
        rum2.addMonster("Gammelsmurfen", "Gammelsmurf descriptyion", 2, 1);

        rum1.addWeapon("Excalibur", 2);


        // Välkomstmeddelande och skapande av spelare
        System.out.println("Välkommen till Dragon Treasure");
        System.out.print("Skriv ditt namn: ");
        String name = input.nextLine();
        player = new Player(name, entre);

        System.out.println("Välkommen " + player.getPlayerName() + " använd [n],[s],[e],[w] för att navigera i grottan.");
        System.out.println("Skriv [q] för att avsluta spelet.");
        System.out.println("------------------ Start ------------------");
        entre.doNarrative();
    }

    // While loop som kör spelet, anropar metoden move() om input inte är "q"
    public void playGame() {
        int moves = 0;
        boolean playing = true;
        while (playing && player.getPlayerHP() > 0) {
            moves = moves +1;
            // toLowerCase används för att alla kommandon ska funka
            System.out.print("Vad väljer du? > ");
            String command = input.nextLine().toLowerCase();

            // "==" fungerade inte så behövde använda ".equals()"
            if (command.equals("q")) {
                System.out.println("Spelet avslutas!");
                playing = false;
            } else if (player.getCurrentRoom().getItems().size() > 0) {
                if (command.equals("p")) {
                    for (Item item : player.getCurrentRoom().getItems()){
                        player.addItem(item);
                    }
                    System.out.println("Du plockade upp "+ player.getCurrentRoom().getItems().size() + "x föremål");

                    player.getCurrentRoom().clearItems();
                    player.getCurrentRoom().doNarrative();

                }
                else {
                    System.out.println("------------------ Val "+ moves + " ------------------");
                    player.move(command);
                }
            } else {
                System.out.println("------------------ Val "+ moves + " ------------------");
                player.move(command);

            }
        }
        System.out.println("\nDu förlorade!! x9 axaxaxaxaaa, spelets avslutas");
    }

    public void printDragonTreasure(){
        System.out.println(
                "                  _.--.\n"+
                "              _.-'_:-'||\n"+
                "          _.-'_.-::::'||\n"+
                "     _.-:'_.-::::::'  ||\n"+
                "   .'`-.-:::::::'     ||\n"+
                "  /.'`;|:::::::'      ||_\n"+
                " ||   ||::::::'      _.;._'-._\n"+
                " ||   ||:::::'   _.-!oo @.!-._'-.\n"+
                " \'.  ||:::::.-!() oo @!()@.-'_.||\n"+
                "   '.'-;|:.-'.&$@.& ()$%-'o.'\\U||\n"+
                "     `>'-.!@%()@'@_%-'_.-o _.|'||\n"+
                "      ||-._'-.@.-'_.-' _.-o  |'||\n"+
                "      ||=[ '-._.-\\U/.-'    o |'||\n"+
                "      || '-.]=|| |'|      o  |'||\n"+
                "      ||      || |'|        _| ';\n"+
                "      ||      || |'|    _.-'_.-'\n"+
                "      |'-._   || |'|_.-'_.-'\n"+
                "      '-._'-.|| |' `_.-'\n"+
                "           '-.||_/.-'\n");
    }

    public void printDragon(){
        System.out.println(
                "                                                  .~))>>\n"+
                "                                                 .~)>>\n"+
                "                                               .~))))>>>\n"+
                "                                             .~))>>             ___\n"+
                "                                           .~))>>)))>>      .-~))>>\n"+
                "                                         .~)))))>>       .-~))>>)>\n"+
                "                                       .~)))>>))))>>  .-~)>>)>\n"+
                "                   )                 .~))>>))))>>  .-~)))))>>)>\n"+
                "                ( )@@*)             //)>))))))  .-~))))>>)>\n"+
                "              ).@(@@               //))>>))) .-~))>>)))))>>)>\n"+
                "            (( @.@).              //))))) .-~)>>)))))>>)>\n"+
                "          ))  )@@*.@@ )          //)>))) //))))))>>))))>>)>\n"+
                "       ((  ((@@@.@@             |/))))) //)))))>>)))>>)>\n"+
                "      )) @@*. )@@ )   (\\_(\\-\\b  |))>)) //)))>>)))))))>>)>\n"+
                "    (( @@@(.@(@ .    _/`-`  ~|b |>))) //)>>)))))))>>)>\n"+
                "     )* @@@ )@*     (@)  (@) /\\b|))) //))))))>>))))>>\n"+
                "   (( @. )@( @ .   _/  /    /  \\b)) //))>>)))))>>>_._\n"+
                "    )@@ (@@*)@@.  (6///6)- / ^  \\b)//))))))>>)))>>   ~~-.\n"+
                " ( @jgs@@. @@@.*@_ VvvvvV//  ^  \\b/)>>))))>>      _.     `bb\n"+
                " ((@@ @@@*.(@@ . - | o |' \\ (  ^   \\b)))>>        .'       b`,\n"+
                "   ((@@).*@@ )@ )   \\^^^/  ((   ^  ~)_        \\  /           b `,\n"+
                "     (@@. (@@ ).     `-'   (((   ^    `\\ \\ \\ \\ \\|             b  `.\n"+
                "       (*.@*              / ((((        \\| | |  \\       .       b `.\n"+
                "                         / / (((((  \\    \\ /  _.-~\\     Y,      b  ;\n"+
                "                        / / / (((((( \\    \\.-~   _.`\" _.-~`,    b  ;\n"+
                "                       /   /   `(((((()    )    (((((~      `,  b  ;\n"+
                "                     _/  _/      `\"\"\"/   /'                  ; b   ;\n"+
                "                 _.-~_.-~           /  /'                _.'~bb _.'\n"+
                "               ((((~~              / /'              _.'~bb.--~\n"+
                "                                  ((((          __.-~bb.-~\n"+
                "                                              .'  b .~~\n"+
                "                                              :bb ,' \n"+
                "                                              ~~~~\n");
    }

}