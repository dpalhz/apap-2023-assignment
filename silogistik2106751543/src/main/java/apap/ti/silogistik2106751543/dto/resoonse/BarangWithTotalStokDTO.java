package apap.ti.silogistik2106751543.dto.resoonse;

import java.math.BigDecimal;

import apap.ti.silogistik2106751543.model.Barang;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BarangWithTotalStokDTO {

    private String sku;
    private String merk;
    private BigDecimal hargaBarang;
    private Integer totalStok;

    
}
