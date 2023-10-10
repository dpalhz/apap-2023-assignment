package apap.ti.silogistik2106751543.service;

import java.util.List;

import apap.ti.silogistik2106751543.model.Barang;
import apap.ti.silogistik2106751543.model.Gudang;
import apap.ti.silogistik2106751543.model.GudangBarang;

public interface GudangBarangService {

    List<GudangBarang> getAllGudangBarang();

    List<GudangBarang> getGudangBarangByBarang(Barang barang);

    List<GudangBarang> getGudangBarangByGudang(Gudang gudang);

    GudangBarang save(GudangBarang gudangBarang);

    


    
}
