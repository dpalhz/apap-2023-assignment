package apap.ti.silogistik2106751543.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import apap.ti.silogistik2106751543.model.Gudang;


@Repository
public interface GudangRepository extends JpaRepository<Gudang, Long>{

    @Query("SELECT g FROM Gudang g " + 
    "LEFT JOIN GudangBarang gb ON g.idGudang = gb.gudang.idGudang "+ 
    "GROUP BY g.idGudang " +
    "ORDER BY SUM(gb.stok) ASC")
    List<Gudang> findAllGudangSortedByTotalStok();

    
}
