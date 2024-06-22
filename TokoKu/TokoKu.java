import java.util.ArrayList;
import java.util.Scanner;

import TokoKu.controller.Penjual;
import TokoKu.menu.MainMenu;
import TokoKu.utility.DataTokoKu;
import TokoKu.utility.ScreenHelper;

public class TokoKu {
    private static Scanner input = new Scanner(System.in);
    private static DataTokoKu masterData = new DataTokoKu();
    private static ArrayList<Penjual> dataPenjual;
    private static Penjual activePenjual;
    private static boolean isRunning = true;

    public static void main(String[] args) {
        dataPenjual = masterData.initPenjual();

        while(isRunning) {
            showMenu();
        }
    }

    private static void showMenu() {
        while(activePenjual == null) {
            loginMenu();
        }
        MainMenu menuTokoku = new MainMenu(dataPenjual, activePenjual);
        menuTokoku.tampilMenu();
        
        activePenjual = null;
        System.out.print("Apakah anda ingin menutup program ini? (y | t) : ");
        String jawaban = input.nextLine();
        if (jawaban.equalsIgnoreCase("y")) {
            isRunning = false;
            System.out.println("+==============================================+");
            System.out.println("| TERIMAKASIH SUDAH MENCOBA PROGRAM INI :) |");
            System.out.println("+==============================================+");
        }
    }

    private static void loginMenu() {
        ScreenHelper.clearConsole();
        String username, password;
        System.out.println("+=============================================+");
        System.out.println("|                LOGIN TOKOKU                 |");
        System.out.println("+=============================================+");
        System.out.print("Username : ");
        username = input.nextLine();
        System.out.print("Password : ");
        password = input.nextLine();
        for (Penjual temp: dataPenjual) {
            if (temp.login(username, password)) {
                activePenjual = temp;
            }
        }           
    }
}
