package apap.ti.silogistik2106751543.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateBarangRequestDTO {

    private String sku;

    @NotNull
    private Integer tipeBarang;

    @NotNull
    private String merk;

    @NotNull
    private BigDecimal hargaBarang;

    
}
