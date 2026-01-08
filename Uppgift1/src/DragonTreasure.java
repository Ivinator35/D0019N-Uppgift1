
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class DragonTreasure{
    private Player player;
    private Scanner input = new Scanner(System.in);
    private ArrayList<Room1> rooms = new ArrayList<Room1>();

    // metod för att skapa rum, dörrar och spelare
    public void setupGame() throws InterruptedException{
        //skapar rum ur klassen Room1
        Room1 entre = new Room1("Grottöppning\nDu står framför ett berg med en grottöppning.");
        Room1 rum1 = new Room1("Rum1\nDet består endast av sten och är helt tomt.");
        Room1 rum2 = new Room1("Rum2\nDet ligger ett förmultnat skelett i hörnet av rummet.");
        Room1 rum3 = new Room1("Rum3\nDet står en tyst tjomme i rummets hörn och stirrar.");
        Room1 rum4 = new Room1("Rum4\nGargamels rum var mörkt och luktade unket.");
        Room1 rum5 = new Room1("Rum5\nRummet har en pedestal med plats för ett föremål.");
        Room1 rum6 = new Room1("Rum6\nI mitten av rummet finns en stor sten!");
        Room1 drakRummet = new Room1("Drakrummet\nFyfan vad drakens lik stinker, dags att dipp me guldet! ");


        // Skapar dörrar/"exits"
        entre.setExits("e", rum1, false);
        rum1.setExits("n", rum2, true);
        rum1.setExits("s", rum3, false);
        rum2.setExits("e", rum4, false);
        rum4.setExits("e", rum5, false);
        rum4.setExits("s", rum6, true);
        rum6.setExits("e", drakRummet, false);

        //skapar monster
        rum4.addMonster("Gargamel", "Gargamel skricker och kastar eldbollar", 8, 2, false);
        drakRummet.addMonster("Draken",printDragon, 15, 3, true);

        //Skapar föremål
        Key merchantKey = new Key("Grottnyckel", "Främlingen kastar ut något på golvet.");
        Weapon skelettSvärd = new Weapon("Svärd", "I skelettets bröst sitter ett svärd.", 3);
        Potion gargamelPotion = new Potion("Hälsodryck", "En dryck rullar ut från Gargamelskropp", 5);
        Key draknyckeln = new Key("Draknyckeln", "På podiumet sitter en nyckel som ser ut som en drake.");
        Weapon excalibur = new Weapon("Excalibur", "I stenen sitter ett svärd som ser tvär op ut", 4);
        Treasure drakskatten = new Treasure("Drakskatten", "Du har dräpt draken och kan nu stjäla allt hans skit");

        // lägger till föremål i rum
        rum3.addItems(merchantKey);
        rum2.addItems(skelettSvärd);
        rum4.addItems(gargamelPotion);
        rum5.addItems(draknyckeln);
        rum6.addItems(excalibur);
        drakRummet.addItems(drakskatten);


        // lägger till rum i en ArrayList, ingen använding än så länge men var noterat i uppgiften
        Collections.addAll(rooms, entre, rum1, rum2, rum3, rum4, rum5, rum6, drakRummet);

        // Välkomstmeddelande och skapande av spelare
        System.out.println("Välkommen till Dragon Treasure");
        System.out.print("Skriv ditt namn: ");
        String name = input.nextLine();
        player = new Player(name, entre);

        System.out.println("Välkommen " + player.getPlayerName() + " använd [n],[s],[e],[w] för att navigera i grottan.");
        System.out.println("Skriv [q] för att avsluta spelet.");
        entre.doNarrative();
    }

    // While loop som kör spelet, anropar metoden move() om input inte är "q"
    // kollar även så att man inte loopar igen om spelaren har <= 0 HP
    public void playGame() throws InterruptedException {
        String command;
        Room1 startingRoom = player.getCurrentRoom();
        while (player.getPlayerHP() > 0) {
            // kollar om det finns föremål i rummet och låter spelaren plocka upp dem
            if (!player.getCurrentRoom().getRoomItems().isEmpty()) {
                System.out.print("Vill du plocka upp föremålen, tryck [p]\n");
            }

            // toLowerCase används för att alla kommandon ska funka
            System.out.print("Vad gör du? > ");
            command = input.nextLine().toLowerCase();

            // "==" fungerade inte så behövde använda ".equals()"
            if (command.equals("q")) {
                System.out.println("Spelet avslutas!");
                break;
            }
            // om använder skriver i får man se spelarens inventory
            else if (command.equals("i")) {
                player.checkWeaponInv();
                player.getCurrentRoom().doNarrative();
            } else if (command.equals("p")) {
                player.pickupItems(player.getCurrentRoom());
                    System.out.println("");
            }
            // ifall det inte är "i" eller "q" körs move metoden
            else {
                player.move(command);
                // ifall spelaren har en skatt kan hen komma ut ur spelet
                if (player.getCurrentRoom() == startingRoom) {
                    for (Item item : player.getPlayerInv()) {
                        if (item.getItemID() == 4) {
                            printDragonTreasure();
                            System.out.println("Du lämnar grottan med skatten. Grattis, du vann!");
                            player.setPlayerHP(0);
                        }
                    }

                }
            }
        }
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

    public String printDragon =

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
                "                                              ~~~~\n";


}