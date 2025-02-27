package apap.ti.silogistik2106751543.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import apap.ti.silogistik2106751543.model.Barang;
import apap.ti.silogistik2106751543.model.PermintaanPengiriman;
import apap.ti.silogistik2106751543.model.PermintaanPengirimanBarang;

public interface PermintaanPengirimanBarangRepository extends JpaRepository<PermintaanPengirimanBarang, Long> {
    PermintaanPengirimanBarang findByBarangAndPermintaanPengiriman(Barang barang, PermintaanPengiriman permintaanPengiriman);
    
}
