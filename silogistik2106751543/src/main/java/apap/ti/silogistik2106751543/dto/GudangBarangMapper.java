package apap.ti.silogistik2106751543.dto;

import org.mapstruct.Mapper;

import apap.ti.silogistik2106751543.dto.request.CreateGudangBarangRequestDTO;
import apap.ti.silogistik2106751543.model.GudangBarang;


@Mapper(componentModel = "spring")
public interface GudangBarangMapper {

    GudangBarang createRequestGudangBarangRequestDTOToGudangBarang(CreateGudangBarangRequestDTO createGudangBarangRequestDTO);
    
    
}