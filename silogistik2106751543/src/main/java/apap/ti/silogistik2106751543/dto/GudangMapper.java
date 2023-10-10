package apap.ti.silogistik2106751543.dto;

import org.mapstruct.Mapper;

import apap.ti.silogistik2106751543.dto.request.CreateGudangBarangRequestDTO;
import apap.ti.silogistik2106751543.dto.request.CreateGudangRequestDTO;
import apap.ti.silogistik2106751543.dto.request.RestokGudangRequestDTO;
import apap.ti.silogistik2106751543.model.Gudang;

@Mapper(componentModel = "spring")
public interface GudangMapper {

    Gudang createGudangRequestDTOToGudang(CreateGudangRequestDTO createGudangRequestDTO);

    CreateGudangBarangRequestDTO gudangToCreateGudangRequestDTO(Gudang gudang);

    RestokGudangRequestDTO gudangToRestokGudangRequestDTO(Gudang gudang);

    Gudang restokGudangRequestDTOToGudang (RestokGudangRequestDTO restokGudangRequestDTO);

    
}
