package apap.ti.silogistik2106751543.service;

import java.util.List;

import apap.ti.silogistik2106751543.model.Barang;
import apap.ti.silogistik2106751543.model.PermintaanPengiriman;
import apap.ti.silogistik2106751543.model.PermintaanPengirimanBarang;

public interface PermintaanPengirimanBarangService {

    void save(List<PermintaanPengirimanBarang> permintaanPengirimanBarang, PermintaanPengiriman permintaanPengiriman);
    List<PermintaanPengirimanBarang> getPermintaanPengirimanBarangByReq(PermintaanPengiriman permintaanPengiriman);
    PermintaanPengirimanBarang getPermintaanPengirimanBarangByBarangAndPermintaanPengiriman(Barang barang, PermintaanPengiriman permintaanPengiriman);
    
}
