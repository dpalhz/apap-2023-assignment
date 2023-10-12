package apap.ti.silogistik2106751543.dto;

import org.mapstruct.Mapper;

import apap.ti.silogistik2106751543.dto.request.CreatePermintaanPengirimanBarangDTO;
import apap.ti.silogistik2106751543.model.PermintaanPengirimanBarang;

@Mapper(componentModel = "spring")
public interface PermintaanPengirimanBarangMapper {

    PermintaanPengirimanBarang createPermintaanPengirimanBarangfromDTO(CreatePermintaanPengirimanBarangDTO createPermintaanPengirimanBarangDTO);
    
}
