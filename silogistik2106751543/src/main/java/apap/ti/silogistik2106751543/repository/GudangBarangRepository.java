package apap.ti.silogistik2106751543.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.ti.silogistik2106751543.model.Barang;
import apap.ti.silogistik2106751543.model.Gudang;
import apap.ti.silogistik2106751543.model.GudangBarang;
import java.util.List;



@Repository
public interface GudangBarangRepository extends JpaRepository<GudangBarang, Long> {

    List<GudangBarang> findByBarang(Barang barang);
    List<GudangBarang> findByGudang(Gudang barang);
    GudangBarang findByGudangAndBarang(Gudang gudang, Barang barang);

    
}
