package apap.ti.silogistik2106751543.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import apap.ti.silogistik2106751543.model.PermintaanPengiriman;

public interface PermintaaPengirimanRepository extends JpaRepository<PermintaanPengiriman, Long> {



      @Query("SELECT DISTINCT pp FROM PermintaanPengiriman pp " +
            "JOIN pp.daftarPermintaanBarang ppb " +
            "WHERE ppb.barang.sku = :sku AND " +
            "pp.tanggalPengiriman BETWEEN :startDate AND :endDate")
    List<PermintaanPengiriman> findPermintaanPengirimanByTanggalPengirimanAndSku(@Param("startDate") Date startDate,
                                                                                 @Param("endDate") Date endDate,
                                                                                 @Param("sku") String sku);
    
}
