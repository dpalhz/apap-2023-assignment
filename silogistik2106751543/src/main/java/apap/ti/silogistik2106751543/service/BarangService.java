package apap.ti.silogistik2106751543.service;

import java.util.List;

import apap.ti.silogistik2106751543.model.Barang;

public interface BarangService {

    void savaBarang(Barang barang);
    List<Barang> getAllBarang();
    Barang getBarangById(String sku);
    String generateSKU(Integer tipeBarang);
    
}
