package apap.ti.silogistik2106751543.dto.resoonse;

import apap.ti.silogistik2106751543.model.Gudang;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GudangWithStok {
    Gudang gudang;
    Integer stokSuatuBarang;
    
}
