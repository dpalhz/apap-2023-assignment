package apap.ti.silogistik2106751543.service;

import java.util.List;

import apap.ti.silogistik2106751543.model.Gudang;
import apap.ti.silogistik2106751543.model.GudangBarang;

public interface GudangService {

    void saveGudang(Gudang gudang);

    List<Gudang> getAllGudang();

    Integer getBanyakGudang();

    Gudang getGudangById(Long id);


    List<GudangBarang> getGudangBarang(Long id);

    void restokBarang(Gudang gudangFromDTO);
    Integer totalStok (Gudang gudang);
    List<Gudang> findAllGudangSortedByTotalStok();
    

    
    
}
