package TokoKu.menu;

import java.util.ArrayList;

import TokoKu.controller.Produk;
import TokoKu.controller.Kategori;
import TokoKu.controller.Menu;
import TokoKu.utility.DataTokoKu;
import TokoKu.utility.ScreenHelper;


public class MenuProduk extends Menu {
    ArrayList<Produk> data;
    DataTokoKu dataTokoKu;

    public MenuProduk(ArrayList<Produk> data, DataTokoKu dataTokoku) {
        this.data = data;
        this.dataTokoKu = dataTokoku;
    }

    @Override
    public void tampilMenu() {
        int pilihan;
        do {
            ScreenHelper.clearConsole();
            System.out.println("+=============================================+");
            System.out.println("|                  DATA PRODUK                |");
            System.out.println("+=============================================+");
            System.out.println("| 1 | Tampil Produk                           |");
            System.out.println("+---+-----------------------------------------+");
            System.out.println("| 2 | Tambah Produk                           |");
            System.out.println("+---+-----------------------------------------+");
            System.out.println("| 3 | Edit Produk                             |");
            System.out.println("+---+-----------------------------------------+");
            System.out.println("| 4 | Hapus Produk                            |");
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
            System.out.println("|               TAMPIL DATA PRODUK            |");
            System.out.println("+=============================================+");
            for (Produk tempProduk : data) {
                System.out.println("Kode Produk     : " + tempProduk.getKode());
                System.out.println("Nama Produk     : " + tempProduk.getNama());
                String namaKategori = dataTokoKu.getNamaKategori(tempProduk.getKategori());
                System.out.println("Kategori Produk : " + namaKategori);
                System.out.println("Harga Produk    : " + tempProduk.getHarga());
                System.out.println("Stok Produk     : " + tempProduk.getStok());
                System.out.println("+=============================================+");
            }
            input.nextLine();
        } else {
            System.out.println("Data Produk kosong, silakan tambahkan data.");
            input.nextLine();
        }
    }

        public void tampilDataKategori() {
        ScreenHelper.clearConsole();
        ArrayList<Kategori> dataKategori = dataTokoKu.initKategori();
        if (dataKategori.size() > 0) {
            System.out.println("+=============================================+");
            System.out.println("|                 DATA KATEGORI               |");
            System.out.println("+=============================================+");
            for (Kategori tempKategori : dataKategori) {
                System.out.println("Kode Kategori : " + tempKategori.getKode());
                System.out.println("Nama Kategori : " + tempKategori.getKategori());
                System.out.println("+=============================================+");
            }
        } else {
            System.out.println("Data Kategori kosong, silakan tambahkan data.");
        }
    }

    @Override
    public int pilih() {
        ScreenHelper.clearConsole();
        String kodeProduk = "";
        int ProdukDipilih = -1;

        if (data.size() > 0) {
            do {
                System.out.println("+=============================================+");
                System.out.println("|                  PILIH PRODUK               |");
                System.out.println("+=============================================+");
                for (Produk tempProduk : data) {
                    System.out.println("Kode Produk     : " + tempProduk.getKode());
                    System.out.println("Nama Produk     : " + tempProduk.getNama());
                    String namaKategori = dataTokoKu.getNamaKategori(tempProduk.getKategori());
                    System.out.println("Kategori Produk : " + namaKategori);
                    System.out.println("Harga Produk    : " + tempProduk.getHarga());
                    System.out.println("Stok Produk     : " + tempProduk.getStok());
                    System.out.println("+=============================================+");
                }

                System.out.print("Silakan pilih kode Produk : ");
                kodeProduk = input.nextLine();
                for (int i = 0; i < data.size(); i++) {
                    if (data.get(i).getKode().equals(kodeProduk)) {
                        ProdukDipilih = i;
                        break;
                    }
                }
            } while (ProdukDipilih == -1);
        } else {
            System.out.println("Data Produk kosong, silakan tambahkan data.");
            input.nextLine();
        }
        return ProdukDipilih;
    }

    @Override
    public void tambah() {
        ScreenHelper.clearConsole();
        System.out.println("+=============================================+");
        System.out.println("|               TAMBAH DATA PRODUK            |");
        System.out.println("+=============================================+");
        Produk tempProduk = new Produk();
        System.out.print("Kode Produk     : ");
        tempProduk.setKode(input.nextLine());
        System.out.print("Nama Produk     : ");
        tempProduk.setNama(input.nextLine());
        tampilDataKategori();
        System.out.print("Kategori Produk : ");
        tempProduk.setKategori(input.nextLine());
        System.out.print("Harga Produk    : ");
        tempProduk.setHarga(input.nextInt());
        System.out.print("Stok Produk     : ");
        tempProduk.setStok(input.nextInt());
        data.add(tempProduk);
        System.out.println("+=============================================+");
        System.out.println("|              DATA PRODUK TERSIMPAN          |");
        System.out.println("+=============================================+");
        input.nextLine();
        input.nextLine();
    }

    @Override
    public void edit() {
        ScreenHelper.clearConsole();
        int indexProduk = pilih();
        if (indexProduk != -1) {
            Produk editProduk = data.get(indexProduk);
            System.out.println("+=============================================+");
            System.out.println("|                EDIT DATA PRODUK              |");
            System.out.println("+=============================================+");
            System.out.print("Kode Produk     : ");
            editProduk.setKode(input.nextLine());
            System.out.print("Nama Produk     : ");
            editProduk.setNama(input.nextLine());
            tampilDataKategori();
            System.out.print("Kategori Produk : ");
            editProduk.setKategori(input.nextLine());
            System.out.print("Harga Produk    : ");
            editProduk.setHarga(input.nextInt());
            System.out.print("Stok Produk     : ");
            editProduk.setStok(input.nextInt());
            data.set(indexProduk, editProduk);
            System.out.println("+=============================================+");
            System.out.println("|              DATA PRODUK TERSIMPAN          |");
            System.out.println("+=============================================+");
            input.nextLine();
            input.nextLine();
        }
    }

    public void hapus() {
        ScreenHelper.clearConsole();
        int indexProduk = pilih();
        if (indexProduk != -1) {
            data.remove(indexProduk);
            System.out.println("+=============================================+");
            System.out.println("|               DATA PRODUK DIHAPUS             |");
            System.out.println("+=============================================+");
            input.nextLine();
        }
    }

}
