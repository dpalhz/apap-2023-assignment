package apap.ti.silogistik2106751543.dto.resoonse;


import java.util.Date;
import java.util.List;

import apap.ti.silogistik2106751543.model.Karyawan;
import apap.ti.silogistik2106751543.model.PermintaanPengirimanBarang;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class DetailPermintaanPengirimanDTO {


    Long idPermintaanPengiriman;
    String nomorPengiriman;
    Karyawan karyawan;
    String namaPenerima;
    String alamatPenerima;
    Integer biayaPengiriman;
    String jenisLayanan;
    List<PermintaanPengirimanBarang> daftarPermintaanBarang;
    String tanggalPengiriman;
    String waktuPermintaan;
    
    
    
}

