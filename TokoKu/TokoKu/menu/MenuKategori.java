package TokoKu.menu;

import java.util.ArrayList;

import TokoKu.controller.Kategori;
import TokoKu.controller.Menu;
import TokoKu.utility.ScreenHelper;

public class MenuKategori extends Menu {
    ArrayList<Kategori> data;

    public MenuKategori(ArrayList<Kategori> data) {
        this.data = data;
    }

    @Override
    public void tampilMenu() {
        int pilihan;
        do {
            ScreenHelper.clearConsole();
            System.out.println("+=============================================+");
            System.out.println("|                DATA KATEGORI                |");
            System.out.println("+=============================================+");
            System.out.println("| 1 | Tampil Kategori                         |");
            System.out.println("+---+-----------------------------------------+");
            System.out.println("| 2 | Tambah Kategori                         |");
            System.out.println("+---+-----------------------------------------+");
            System.out.println("| 3 | Edit Kategori                           |");
            System.out.println("+---+-----------------------------------------+");
            System.out.println("| 4 | Hapus Kategori                          |");
            System.out.println("+---+-----------------------------------------+");
            System.out.println("| 0 | Kembali                                 |");
            System.out.println("+=============================================+");
            System.out.print("\nSilakan masukan pilihan anda (0...4) : ");
            pilihan = input.nextInt();
            input.nextLine();
            switch (pilihan) {
                case 1:
                    tampilData();
                    break;
                case 2:
                    tambah();
                    break;
                case 3:
                    edit();
                    break;
                case 4:
                    hapus();
                    break;
                case 0:
                    System.out.println("+=============================================+");
                    System.out.println("|            KEMBALI KE MENU UTAMA            |");
                    System.out.println("+=============================================+\n");
                    break;
                default:
                    System.out.println("Pilihan yang anda input tidak tersedia, silakan ulangi kembali.");
                    input.next();
            }
        } while (pilihan != 0);
    }

    @Override
    public void tampilData() {
        ScreenHelper.clearConsole();
        if (data.size() > 0) {
            System.out.println("+=============================================+");
            System.out.println("|             TAMPIL DATA KATEGORI            |");
            System.out.println("+=============================================+");
            for (Kategori tempKategori : data) {
                System.out.println("Kode Kategori     : " + tempKategori.getKode());
                System.out.println("Nama Kategori     : " + tempKategori.getKategori());
                System.out.println("+=============================================+");
            }
            input.nextLine();
        } else {
            System.out.println("Data Kategori kosong, silakan tambahkan data.");
            input.nextLine();
        }
    }

    @Override
    public int pilih() {
        ScreenHelper.clearConsole();
        String kodeKategori = "";
        int KategoriDipilih = -1;

        if (data.size() > 0) {
            do {
                System.out.println("+=============================================+");
                System.out.println("|                PILIH KATEGORI               |");
                System.out.println("+=============================================+");
                for (Kategori tempKategori : data) {
                    System.out.println("Kode Kategori     : " + tempKategori.getKode());
                    System.out.println("Judul Kategori    : " + tempKategori.getKategori());
                    System.out.println("+=============================================+");
                }

                System.out.print("Silakan pilih kode Kategori : ");
                kodeKategori = input.nextLine();
                for (int i = 0; i < data.size(); i++) {
                    if (data.get(i).getKode().equals(kodeKategori)) {
                        KategoriDipilih = i;
                        break;
                    }
                }
            } while (KategoriDipilih == -1);
        } else {
            System.out.println("Data Kategori kosong, silakan tambahkan data.");
            input.nextLine();
        }
        return KategoriDipilih;
    }

    @Override
    public void tambah() {
        ScreenHelper.clearConsole();
        System.out.println("+=============================================+");
        System.out.println("|             TAMBAH DATA KATEGORI            |");
        System.out.println("+=============================================+");
        Kategori tempKategori = new Kategori();
        System.out.print("Kode Kategori     : ");
        tempKategori.setKode(input.nextLine());
        System.out.print("Nama Kat egori    : ");
        tempKategori.setKategori(input.nextLine());
        data.add(tempKategori);
        System.out.println("+=============================================+");
        System.out.println("|            DATA KATEGORI TERSIMPAN           |");
        System.out.println("+=============================================+");
        input.nextLine();
        input.nextLine();
    }

    @Override
    public void edit() {
        ScreenHelper.clearConsole();
        int indexKategori = pilih();
        if (indexKategori != -1) {
            Kategori editKategori = data.get(indexKategori);
            System.out.println("+=============================================+");
            System.out.println("|              EDIT DATA KATEGORI             |");
            System.out.println("+=============================================+");
            System.out.print("Kode Kategori     : ");
            editKategori.setKode(input.nextLine());
            System.out.print("Nama Kategori     : ");
            editKategori.setKategori(input.nextLine());
            data.set(indexKategori, editKategori);
            System.out.println("+=============================================+");
            System.out.println("|            DATA KATEGORI TERSIMPAN          |");
            System.out.println("+=============================================+");
            input.nextLine();
            input.nextLine();
        }
    }

    public void hapus() {
        ScreenHelper.clearConsole();
        int indexKategori = pilih();
        if (indexKategori != -1) {
            data.remove(indexKategori);
            System.out.println("+=============================================+");
            System.out.println("|             DATA KATEGORI DIHAPUS           |");
            System.out.println("+=============================================+");
            input.nextLine();
        }
    }

}
