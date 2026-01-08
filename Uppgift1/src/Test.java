import java.util.Scanner;

public class Test {
    private Scanner input = new Scanner(System.in);
    
    public void testRun(){
        Room1 entre = new Room1("Grottöppning");
        Player  player = new Player("isak", entre);

        Weapon testWeapon = new Weapon("Svärd", "Ett Svärd ligger på marken.", 2);
        player.addWeapon(testWeapon);

        Weapon testWeapon2 = new Weapon("Yxa", "En yxa ligger på marken.", 4);
        player.addWeapon(testWeapon2);
        
        entre.addItems(testWeapon);
        entre.addItems(testWeapon2);
        
        entre.showItems();
        
        //String command = input.nextLine();
/*         System.out.println(player.getPlayerDMG()); 
        player.checkWeaponInv();
        System.out.println(player.getPlayerDMG());  */
    }
}
