package TokoKu.controller;

import java.util.Date;
import java.util.HashMap;

public class Pembelian {
    private int idPembelian;
    private Customer pembeli;
    private Penjual PenjualPembelian;
    private Date tanggalPembelian;
    private double totalHarga;
    private HashMap<Produk, Integer> daftarProduk;  // Menyimpan Produk dan jumlah yang dibeli
    private static int idCounter = 1;

    public Pembelian(Customer pembeli, Penjual PenjualPembelian) {
        this.idPembelian = idCounter++;
        this.pembeli = pembeli;
        this.PenjualPembelian = PenjualPembelian;
        this.tanggalPembelian = new Date();
        this.totalHarga = 0.0;
        this.daftarProduk = new HashMap<>();
    }

    public int getIdPembelian() {
        return idPembelian;
    }

    public Customer getPembeli() {
        return pembeli;
    }

    public Penjual getPenjualPembelian() {
        return PenjualPembelian;
    }

    public Date getTanggalPembelian() {
        return tanggalPembelian;
    }

    public double getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(double totalHarga) {
        this.totalHarga = totalHarga;
    }

    public void tambahProduk(Produk Produk, int jumlah) {
        if (daftarProduk.containsKey(Produk)) {
            daftarProduk.put(Produk, daftarProduk.get(Produk) + jumlah);
        } else {
            daftarProduk.put(Produk, jumlah);
        }
        totalHarga += Produk.getHarga() * jumlah;
    }

    public HashMap<Produk, Integer> getDaftarProduk() {
        return daftarProduk;
    }
}
