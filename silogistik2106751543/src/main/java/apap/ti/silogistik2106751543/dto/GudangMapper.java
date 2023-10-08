package apap.ti.silogistik2106751543.dto;

import org.mapstruct.Mapper;

import apap.ti.silogistik2106751543.dto.request.CreateGudangRequestDTO;
import apap.ti.silogistik2106751543.model.Gudang;

@Mapper(componentModel = "spring")
public interface GudangMapper {

    Gudang createGudangRequestDTOToGudang(CreateGudangRequestDTO createGudangRequestDTO);
    
}
