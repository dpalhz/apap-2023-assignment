package apap.ti.silogistik2106751543.dto;

import org.mapstruct.Mapper;
import apap.ti.silogistik2106751543.dto.request.CreateKaryawanRequestDTO;
import apap.ti.silogistik2106751543.model.Karyawan;


@Mapper(componentModel = "spring")
public interface KaryawanMapper {

    Karyawan createGudangRequestDTOToGudang(CreateKaryawanRequestDTO createKaryawaRequestDTO);
    CreateKaryawanRequestDTO gudangToCreateKarywanRequestDTO(Karyawan karyawan);

    
}
