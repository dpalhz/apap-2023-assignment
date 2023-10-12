package apap.ti.silogistik2106751543.dto;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import apap.ti.silogistik2106751543.dto.request.CreatePermintaanPengirimanBarangDTO;
import apap.ti.silogistik2106751543.dto.resoonse.DetailPermintaanPengirimanDTO;
import apap.ti.silogistik2106751543.model.PermintaanPengiriman;


@Mapper(componentModel = "spring")
public interface PermintaanPengirimanMapper {

    PermintaanPengiriman createPermintaanPengirimanBarangRequestDTOtoPermintaanPengiriman(CreatePermintaanPengirimanBarangDTO createPermintaanPengirimanBarangDTO);


    DetailPermintaanPengirimanDTO permintanPengirimanToDetailDTO(PermintaanPengiriman permintaanPengiriman);


    @AfterMapping
    default void mapJenisLayananToString(PermintaanPengiriman permintaanPengiriman, @MappingTarget DetailPermintaanPengirimanDTO detailDTO) {
        if (permintaanPengiriman.getJenisLayanan() != null) {
            switch (permintaanPengiriman.getJenisLayanan()) {
                case 1:
                    detailDTO.setJenisLayanan("Same Day");
                    break;
                case 2:
                    detailDTO.setJenisLayanan("Kilat");
                    break;
                case 3:
                    detailDTO.setJenisLayanan("Reguler");
                    break;
                case 4:
                    detailDTO.setJenisLayanan("Hemat");
                    break;
                default:
                    detailDTO.setJenisLayanan("Unknown");
                    break;
            }
        } else {
            detailDTO.setJenisLayanan("Unknown");
        }


        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy; HH:mm");
        String formattedDate = dateFormat.format(permintaanPengiriman.getWaktuPermintaan());
        detailDTO.setWaktuPermintaan(formattedDate);
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate2 = dateFormat2.format(permintaanPengiriman.getTanggalPengiriman());
        detailDTO.setTanggalPengiriman(formattedDate2);

    }

 
    
    
    
}
