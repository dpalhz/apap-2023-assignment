package apap.ti.silogistik2106751543.dto.resoonse;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import apap.ti.silogistik2106751543.model.Gudang;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BarangDetailDTO {
    private String sku;
    private String merk;
    private BigDecimal hargaBarang;
    private Integer totalStok;
    private String tipeBarang;

    private List<GudangWithStok> gudangWithStok;


    
}
