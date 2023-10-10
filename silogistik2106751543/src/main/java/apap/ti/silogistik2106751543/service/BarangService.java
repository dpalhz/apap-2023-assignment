package apap.ti.silogistik2106751543.service;

import java.util.List;

import apap.ti.silogistik2106751543.dto.request.UpdateBarangRequestDTO;
import apap.ti.silogistik2106751543.dto.resoonse.BarangDetailDTO;
import apap.ti.silogistik2106751543.dto.resoonse.BarangWithTotalStokDTO;
import apap.ti.silogistik2106751543.model.Barang;

public interface BarangService {

    void savaBarang(Barang barang);
    List<Barang> getAllBarang();
    Barang getBarangById(String sku);
    String generateSKU(Integer tipeBarang);
    public List<BarangWithTotalStokDTO> getListBarangWithTotalStok();
    public BarangDetailDTO getBarangDetailDTO(String sku);
    Barang UpdateBarang(Barang barangFromDTO);

    
}
