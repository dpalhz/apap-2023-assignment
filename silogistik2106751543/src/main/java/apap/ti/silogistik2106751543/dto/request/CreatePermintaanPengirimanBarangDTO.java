package apap.ti.silogistik2106751543.dto.request;
import java.sql.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import apap.ti.silogistik2106751543.model.Karyawan;
import apap.ti.silogistik2106751543.model.PermintaanPengirimanBarang;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatePermintaanPengirimanBarangDTO {


    Karyawan karyawan;
    String namaPenerima;
    String alamatPenerima;
    Integer biayaPengiriman;
    Integer jenisLayanan;
    List<PermintaanPengirimanBarang> daftarPermintaanBarang;
    Date tanggalPengiriman;
    
    
}
