package apap.ti.silogistik2106751543.dto.request;
import java.util.List;

import apap.ti.silogistik2106751543.model.GudangBarang;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateGudangRequestDTO {

    @NotBlank
    private String namaGudang;

    @NotBlank
    private String alamatGudang;

    @NotNull
    private GudangBarang penerbit;

    private List<GudangBarang> listgudangBarang;


    
}
