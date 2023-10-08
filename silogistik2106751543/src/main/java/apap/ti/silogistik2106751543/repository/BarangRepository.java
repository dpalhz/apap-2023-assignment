package apap.ti.silogistik2106751543.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import apap.ti.silogistik2106751543.model.Barang;


@Repository
public interface BarangRepository extends JpaRepository<Barang, String>{
    
}
