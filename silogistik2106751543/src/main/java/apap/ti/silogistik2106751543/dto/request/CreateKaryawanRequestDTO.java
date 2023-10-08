package apap.ti.silogistik2106751543.dto.request;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateKaryawanRequestDTO {

  
    @NotBlank
    private String nama;
    @NotBlank
    private Integer jenisKelamin;
    @NotBlank
    private Date tanggalLahir;
}
