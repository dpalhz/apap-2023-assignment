package apap.ti.silogistik2106751543.dto.request;

import apap.ti.silogistik2106751543.model.Barang;
import apap.ti.silogistik2106751543.model.Karyawan;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PermintaanPengirimanBarangRequestDTO {
    Karyawan karyawan;
    Integer kuantitasPengiriman;
    Barang barang;
    
}
