package apap.ti.silogistik2106751543.dto;


import apap.ti.silogistik2106751543.model.Barang;

import org.mapstruct.Mapper;


import apap.ti.silogistik2106751543.dto.request.CreateBarangRequestDTO;

@Mapper(componentModel = "spring")
public interface BarangMapper {

    Barang createBarangRequestDTOToBarang(CreateBarangRequestDTO createBarangRequestDTO);



}
