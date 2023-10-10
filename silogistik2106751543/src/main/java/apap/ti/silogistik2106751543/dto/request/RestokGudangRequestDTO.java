package apap.ti.silogistik2106751543.dto.request;

import java.util.List;


import apap.ti.silogistik2106751543.model.GudangBarang;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RestokGudangRequestDTO {
    private Long idGudang;
    private String namaGudang;

    private String alamatGudang;

    private List<GudangBarang> listGudangBarang;
    
}
