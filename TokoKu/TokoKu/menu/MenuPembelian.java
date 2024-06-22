package TokoKu.menu;

import java.util.ArrayList;

import TokoKu.controller.Produk;
import TokoKu.controller.Menu;
import TokoKu.controller.Customer;
import TokoKu.controller.Pembelian;
import TokoKu.controller.Penjual;
import TokoKu.utility.ScreenHelper;

public class MenuPembelian extends Menu {
    private ArrayList<Pembelian> data;
    private ArrayList<Customer> dataCustomer;
    private ArrayList<Produk> dataProduk;
    private MenuCustomer menuCustomer;
    private Penjual PenjualAktif;

    public MenuPembelian(
            ArrayList<Pembelian> data,
            ArrayList<Produk> dataProduk,
            ArrayList<Customer> dataCustomer,
            MenuCustomer mCustomer,
            MenuProduk mProduk,
            Penjual Penjual) {
        this.data = data;
        this.dataProduk = dataProduk;
        this.dataCustomer = dataCustomer;
        this.menuCustomer = mCustomer;
        this.PenjualAktif = Penjual;
    }

    public void tampilMenu() {
        int pilihan;
        do {
            ScreenHelper.clearConsole();
            System.out.println("+=============================================+");
            System.out.println("|                DATA PEMBELIAN               |");
            System.out.println("+=============================================+");
            System.out.println("| 1 | Tampil Pembelian                        |");
            System.out.println("+---+-----------------------------------------+");
            System.out.println("| 2 | Tambah Pembelian                        |");
            System.out.println("+---+-----------------------------------------+");
            System.out.println("| 3 | Detail Pembelian                        |");
            System.out.println("+---+-----------------------------------------+");
            System.out.println("| 4 | Hapus Pembelian                         |");
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
                    detail();
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
            System.out.println("|              TAMPIL DATA PEMBELIAN          |");
            System.out.println("+=============================================+");
            for (Pembelian tempPembelian : data) {
                System.out.println("ID Pembelian       : " + tempPembelian.getIdPembelian());
                System.out.println("Nama Pembeli       : " + tempPembelian.getPembeli().getNama());
                System.out.println("Penjual            : " + tempPembelian.getPenjualPembelian().getNama());
                System.out.println("Tanggal Pembelian  : " + tempPembelian.getTanggalPembelian());
                System.out.println("Total Harga        : Rp " + tempPembelian.getTotalHarga());
                System.out.println("+=============================================+");
            }
            input.nextLine();
        } else {
            System.out.println("Data Pembelian kosong, silakan tambahkan data.");
            input.nextLine();
        }
    }

    @Override
    public void tambah() {
        ScreenHelper.clearConsole();
        System.out.println("+=============================================+");
        System.out.println("|             TAMBAH DATA PEMBELIAN           |");
        System.out.println("+=============================================+");
    
        System.out.println("Silahkan pilih customer yang akan membeli!");
        int indexCustomer = menuCustomer.pilih();
        Customer pembeli = dataCustomer.get(indexCustomer);
    
        Pembelian tempPembelian = new Pembelian(pembeli, PenjualAktif);
    
        System.out.println("Daftar Obat yang Tersedia:");
        for (int i = 0; i < dataProduk.size(); i++) {
            Produk Produk = dataProduk.get(i);
            System.out.println(i + ". " + Produk.getNama() + " - Rp " + Produk.getHarga() + " - Stok: " + Produk.getStok());
        }
    
        System.out.print("Masukkan indeks Produk yang ingin dibeli: ");
        int indexProduk = input.nextInt();
        input.nextLine();
        if (indexProduk >= 0 && indexProduk < dataProduk.size()) {
            Produk ProdukDipilih = dataProduk.get(indexProduk);
            System.out.print("Masukkan jumlah yang ingin dibeli: ");
            int jumlah = input.nextInt();
            input.nextLine();
            if (jumlah <= ProdukDipilih.getStok()) {
                tempPembelian.tambahProduk(ProdukDipilih, jumlah);
                ProdukDipilih.beli(jumlah);
                // Tampilkan total harga
                double totalHarga = tempPembelian.getTotalHarga();
                System.out.println("+=============================================+");
                System.out.println("|        TOTAL HARGA PEMBELIAN: Rp " + totalHarga + "       |");
                System.out.println("+=============================================+");
                System.out.print("Tekan Enter untuk menyelesaikan pembelian atau ketik 'batalkan' untuk membatalkan...");
                String konfirmasi = input.nextLine();
                if (konfirmasi.equalsIgnoreCase("batalkan")) {
                    System.out.println("Pembelian dibatalkan.");
                    return;
                }
                data.add(tempPembelian);
                System.out.println("+=============================================+");
                System.out.println("|            DATA PEMBELIAN TERSIMPAN         |");
                System.out.println("+=============================================+");
            } else {
                System.out.println("Stok Produk tidak mencukupi. Pembelian dibatalkan.");
            }
        } else {
            System.out.println("Indeks obat tidak valid. Pembelian dibatalkan.");
        }
        input.nextLine();
    }
    

    @Override
    public void hapus() {
        int indexPembelian = pilih();
        if (indexPembelian != -1) {
            data.remove(indexPembelian);
            System.out.println("+=============================================+");
            System.out.println("|             DATA PEMBELIAN DIHAPUS          |");
            System.out.println("+=============================================+");
            input.nextLine();
        }
    }

    @Override
    public int pilih() {
        ScreenHelper.clearConsole();
        int pembelianDipilih = -1;

        if (data.size() > 0) {
            do {
                System.out.println("+=============================================+");
                System.out.println("|                PILIH PEMBELIAN              |");
                System.out.println("+=============================================+");
                for (int index = 0; index < data.size(); index++) {
                    Pembelian tempPembelian = data.get(index);
                    System.out.println("INDEX               : " + index);
                    System.out.println("ID Pembelian        : " + tempPembelian.getIdPembelian());
                    System.out.println("Nama Pembeli        : " + tempPembelian.getPembeli().getNama());
                    System.out.println("Penjual            : " + tempPembelian.getPenjualPembelian().getNama());
                    System.out.println("Tanggal Pembelian   : " + tempPembelian.getTanggalPembelian());
                    System.out.println("Total Harga         : Rp " + tempPembelian.getTotalHarga());
                    System.out.println("+=============================================+");
                }

                System.out.print("Silakan pilih INDEX Pembelian : ");
                pembelianDipilih = input.nextInt();
                input.nextLine();
            } while (pembelianDipilih == -1);
        } else {
            System.out.println("Data Pembelian kosong, silakan tambahkan data.");
            input.nextLine();
        }
        return pembelianDipilih;
    }

    public void detail() {
        int indexPembelian = pilih();
        if (indexPembelian != -1) {
            Pembelian pembelian = data.get(indexPembelian);
            ScreenHelper.clearConsole();
            System.out.println("+=============================================+");
            System.out.println("|              DETAIL PEMBELIAN               |");
            System.out.println("+=============================================+");
            System.out.println("ID Pembelian       : " + pembelian.getIdPembelian());
            System.out.println("Nama Pembeli       : " + pembelian.getPembeli().getNama());
            System.out.println("Penjual            : " + pembelian.getPenjualPembelian().getNama());
            System.out.println("Tanggal Pembelian  : " + pembelian.getTanggalPembelian());
            System.out.println("Total Harga        : Rp " + pembelian.getTotalHarga());
            System.out.println("+=============================================+");
            System.out.println("Detail Produk yang Dibeli:");
            for (Produk Produk : pembelian.getDaftarProduk().keySet()) {
                int jumlah = pembelian.getDaftarProduk().get(Produk);
                System.out.println("- " + Produk.getNama() + " x " + jumlah + " = Rp " + (Produk.getHarga() * jumlah));
            }
            System.out.println("+=============================================+");
            input.nextLine();
        }
    }

    @Override
    public void edit() {
        throw new UnsupportedOperationException("Unimplemented method 'edit'");
    }
}
