package apap.ti.silogistik2106751543.service;

import java.util.Date;
import java.util.List;

import apap.ti.silogistik2106751543.model.PermintaanPengiriman;
import apap.ti.silogistik2106751543.model.PermintaanPengirimanBarang;


public interface PermintaanPengirimanService {

    void save(PermintaanPengiriman permintaanPengiriman);
    List<PermintaanPengiriman> getAllPPB();
    PermintaanPengiriman getPermintaanPengirimanById(Long id);
    void savePermintaanPengirimanBarang(List<PermintaanPengirimanBarang> permintaanPengirimanBarang, PermintaanPengiriman pengiriman);
    public void deletePermintaanPengiriman(PermintaanPengiriman permintaanPengiriman);
    List<PermintaanPengiriman> findPermintaanPengirimanByTanggalPengirimanAndSku(Date startDate,Date endDate, String sku);
    
    
}
