package TokoKu.menu;

import java.util.ArrayList;
import java.util.Scanner;

import TokoKu.controller.*;
import TokoKu.utility.DataTokoKu;
import TokoKu.utility.ScreenHelper;

public class MainMenu {
    private DataTokoKu masterData = new DataTokoKu();
    private ArrayList<Produk> dataProduk = masterData.initProduk();
    private ArrayList<Kategori> dataKategori = masterData.initKategori();
    private ArrayList<Customer> dataPelanggan = masterData.initCustomer();
    private ArrayList<Pembelian> dataPembelian = new ArrayList<>();
    private Scanner input = new Scanner(System.in);
    private Penjual activePenjual;
    private MenuProduk menuProduk;
    private MenuKategori menuKategori;
    private MenuPenjual menuPenjual;
    private MenuCustomer menuCustomer;
    private MenuPembelian menuPembelian;

    public MainMenu(ArrayList<Penjual> dataPetugas, Penjual petugas) {
        this.activePenjual = petugas;
        this.menuProduk = new MenuProduk(dataProduk, masterData);
        this.menuKategori = new MenuKategori(dataKategori);
        this.menuPenjual = new MenuPenjual(dataPetugas);
        this.menuCustomer = new MenuCustomer(dataPelanggan);
        this.menuPembelian = new MenuPembelian(dataPembelian, dataProduk, dataPelanggan,
                menuCustomer, menuProduk, activePenjual);
    }

    public void tampilMenu() {
        int pilihan;
        do {
            ScreenHelper.clearConsole();
            System.out.println("+=============================================+");
            System.out.println("|                  MAIN MENU                  |");
            System.out.println("+=============================================+");
            System.out.println("|    TOKOKU PEMROGRAMAN BERORIENTASI OBJEK    |");
            System.out.println("+=============================================+");
            System.out.println("| 1 | Menu Produk                             |");
            System.out.println("+---+-----------------------------------------+");
            System.out.println("| 2 | Menu Kategori Produk                    |");
            System.out.println("+---+-----------------------------------------+");
            System.out.println("| 3 | Menu Customer                           |");
            System.out.println("+---+-----------------------------------------+");
            System.out.println("| 4 | Menu Penjual                            |");
            System.out.println("+---+-----------------------------------------+");
            System.out.println("| 5 | Pembelian Produk                        |");
            System.out.println("+---+-----------------------------------------+");
            System.out.println("| 0 | Logout                                  |");
            System.out.println("+=============================================+");
            System.out.print("\nSilakan masukan pilihan anda (0...5) : ");
            pilihan = input.nextInt();
            input.nextLine();
            switch (pilihan) {
                case 1:
                    menuProduk.tampilMenu();
                    break;
                case 2:
                    menuKategori.tampilMenu();
                    break;
                case 3:
                    menuCustomer.tampilMenu();
                    break;
                case 4:
                    menuPenjual.tampilMenu();
                    break;
                case 5:
                    menuPembelian.tampilMenu();
                    break;
                case 0:
                    ScreenHelper.clearConsole();
                    System.out.println("+=============================================+");
                    System.out.println("|             KELUAR DARI PROGRAM             |");
                    System.out.println("+=============================================+\n");
                    break;
                default:
                    System.out.println("Pilihan yang anda input tidak tersedia, silakan ulangi kembali.");
                    input.next();
            }
        } while (pilihan != 0);
    }
}
