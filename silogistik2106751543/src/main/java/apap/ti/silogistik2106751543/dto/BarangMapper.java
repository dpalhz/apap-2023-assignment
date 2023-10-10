package apap.ti.silogistik2106751543.dto;


import apap.ti.silogistik2106751543.model.Barang;

import org.mapstruct.Mapper;


import apap.ti.silogistik2106751543.dto.request.CreateBarangRequestDTO;
import apap.ti.silogistik2106751543.dto.request.UpdateBarangRequestDTO;

@Mapper(componentModel = "spring")
public interface BarangMapper {

    Barang createBarangRequestDTOToBarang(CreateBarangRequestDTO createBarangRequestDTO);

    Barang updateBarangRequestDTOToBarang(UpdateBarangRequestDTO updateBarangRequestDTOToBarang);

    UpdateBarangRequestDTO barangToUpdateBarangRequestDTO (Barang barang);



}
