package TokoKu.utility;

import java.util.ArrayList;

import TokoKu.controller.*;

public class DataTokoKu {
    private ArrayList<Kategori> dataKategori = new ArrayList<>();
    private ArrayList<Penjual> dataPenjual = new ArrayList<>();
    private ArrayList<Produk> dataProduk = new ArrayList<>();
    private ArrayList<Customer> dataCustomer = new ArrayList<>();
    
    public ArrayList<Kategori> initKategori(){
        Kategori tempKategori = new Kategori();
        tempKategori.setKode("KT1");
        tempKategori.setKategori("Baju");
        dataKategori.add(tempKategori);

        tempKategori = new Kategori();
        tempKategori.setKode("KT2");
        tempKategori.setKategori("Celana");
        dataKategori.add(tempKategori);

        return dataKategori;
    }

    public String getNamaKategori(String kodeKategori) {
        for (Kategori kategori : dataKategori) {
            if (kategori.getKode().equals(kodeKategori)) {
                return kategori.getKategori();
            }
        }
        return "Kategori tidak ditemukan";
    }

    public ArrayList<Produk> initProduk(){
        Produk tempProduk = new Produk();
        tempProduk.setKode("PR01");
        tempProduk.setNama("Kemeja");
        tempProduk.setKategori("KT1");
        tempProduk.setHarga(1000000);
        tempProduk.setStok(10);
        dataProduk.add(tempProduk);

        tempProduk = new Produk();
        tempProduk.setKode("PR02");
        tempProduk.setNama("Celana Panjang");
        tempProduk.setKategori("KT2");
        tempProduk.setHarga(3000000);
        tempProduk.setStok(30);
        dataProduk.add(tempProduk);

        return dataProduk;
    }

    public ArrayList<Penjual> initPenjual(){
        Penjual tempPenjual = new Penjual(
            "Yana",
            "Ubud",
            "Yana@gmail.com",
            "081266355255",
            "yana",
            "yana"
        );
        dataPenjual.add(tempPenjual);

        return dataPenjual;
    }    

    public ArrayList<Customer> initCustomer(){
        Customer tempCustomer = new Customer(
            "Asep",
            "Denpasar",
            "asep@gmail.com",
            "08129213424"
        );
        dataCustomer.add(tempCustomer);

        tempCustomer = new Customer(
            "Ujang",
            "Ubud",
            "ujang@gmail.com",
            "0812921334235"
        );
        dataCustomer.add(tempCustomer);

        return dataCustomer;
    }
}
