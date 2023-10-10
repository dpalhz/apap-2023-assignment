package apap.ti.silogistik2106751543.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106751543.dto.GudangBarangMapper;
import apap.ti.silogistik2106751543.dto.request.CreateGudangBarangRequestDTO;
import apap.ti.silogistik2106751543.dto.resoonse.BarangDetailDTO;
import apap.ti.silogistik2106751543.dto.resoonse.BarangWithTotalStokDTO;
import apap.ti.silogistik2106751543.dto.resoonse.GudangWithStok;
import apap.ti.silogistik2106751543.model.Barang;
import apap.ti.silogistik2106751543.model.Gudang;
import apap.ti.silogistik2106751543.model.GudangBarang;
import apap.ti.silogistik2106751543.repository.BarangRepository;
import apap.ti.silogistik2106751543.repository.GudangBarangRepository;

@Service
public class BarangServiceImpl implements BarangService{

    @Autowired
    BarangRepository barangDb;

    @Autowired
    GudangBarangMapper gudangBarangMapper;


    @Autowired
    GudangBarangService gudangBaranService;

    @Override
    public void savaBarang(Barang barang) {
        // TODO Auto-generated method stub
        barangDb.save(barang);
        saveToGudangBarang(barang);
    }

    @Override
    public List<Barang> getAllBarang() {
        // TODO Auto-generated method stub
        return barangDb.findAll();
    }

    @Override
    public Barang getBarangById(String sku) {
        // TODO Auto-generated method stub
        return barangDb.findById(sku).get();
    }

    @Override
    public String  generateSKU(Integer tipeBarang) {
        String tipe;
        switch (tipeBarang) {
            case 1:
                tipe = "ELEC";
                break;
            case 2:
                tipe = "CLOT";
                break;
            case 3:
                tipe = "FOOD";
                break;
            case 4:
                tipe = "COSM";
                break;
            case 5:
                tipe = "TOOL";
                break;
            default:
                throw new IllegalArgumentException("Tipe barang tidak valid");
        }

        String nomorSKU = generateNomorSKU();
        return tipe + nomorSKU;
    
    }

    private String generateNomorSKU() {
        Integer size = getAllBarang().size()+1; // Mengambil ID yang digenerate oleh database
        String nomorSKU = String.format("%03d", size);
        return nomorSKU;
    }

    public void saveToGudangBarang(Barang barang){
        var gudangBarangDTO = new CreateGudangBarangRequestDTO();
        gudangBarangDTO.setBarang(barang);
        gudangBarangDTO.setStok(0);
        var gudangBarang = gudangBarangMapper.createRequestGudangBarangRequestDTOToGudangBarang(gudangBarangDTO);
        gudangBaranService.save(gudangBarang);
    }

    @Override
    public List<BarangWithTotalStokDTO> getListBarangWithTotalStok() {
        List<BarangWithTotalStokDTO> result = new ArrayList<>();

        // Ambil semua barang dari repositori barang
        List<Barang> listBarang = barangDb.findAll();

        for (Barang barang : listBarang) {
            // Hitung total stok untuk setiap barang
            int totalStok = 0;
            List<GudangBarang> gudangBarangList = gudangBaranService.getAllGudangBarang();
            for (GudangBarang gudangBarang : gudangBarangList) {
                if(barang.equals(gudangBarang.getBarang())){
                    totalStok += gudangBarang.getStok();
                }
                
            }

            // Buat DTO yang berisi informasi barang dan total stoknya
            BarangWithTotalStokDTO barangDTO = new BarangWithTotalStokDTO();
            barangDTO.setSku(barang.getSku());
            barangDTO.setMerk(barang.getMerk());
            
            barangDTO.setHargaBarang(barang.getHargaBarang());
            barangDTO.setTotalStok(totalStok);

            // Tambahkan DTO ke dalam hasil akhir
            result.add(barangDTO);
        }

        return result;
    }

    @Override
    public BarangDetailDTO getBarangDetailDTO(String sku) {
        // Mencari barang berdasarkan SKU
        Barang barang = barangDb.findById(sku).get();

        // Membuat objek BarangDetailDTO
        BarangDetailDTO barangDetailDTO = new BarangDetailDTO();
        barangDetailDTO.setSku(barang.getSku());
        barangDetailDTO.setMerk(barang.getMerk());
        barangDetailDTO.setHargaBarang(barang.getHargaBarang());
        barangDetailDTO.setTipeBarang(getTipeBarangString(barang.getTipeBarang()));
      
      
        int totalStok = 0;
        List<GudangBarang> gudangBarangList = gudangBaranService.getAllGudangBarang();

        for (GudangBarang gudangBarang : gudangBarangList) {
            if(barang.equals(gudangBarang.getBarang())){
                totalStok += gudangBarang.getStok();
                if(gudangBarang.getGudang()!=null){
                    GudangWithStok gudangWithStok = new GudangWithStok();
                    gudangWithStok.setGudang(gudangBarang.getGudang());
                    gudangWithStok.setStokSuatuBarang(gudangBarang.getStok());
                    barangDetailDTO.getGudangWithStok().add(gudangWithStok);
                }
            }
            
        }
        barangDetailDTO.setTotalStok(totalStok);

      

        


        return barangDetailDTO;
    }


    private String getTipeBarangString(Integer tipeBarang) {
        switch (tipeBarang) {
            case 1:
                return "Produk Elektronik";
            case 2:
                return "Pakaian & Aksesoris";
            case 3:
                return "Makanan & Minuman";
            case 4:
                return "Kosmetik";
            case 5:
                return "Perlengkapan Rumah";
            default:
                return "Tipe Barang Tidak Valid";
        }
    }

    @Override
    public Barang UpdateBarang(Barang barangFromDTO) {
        // TODO Auto-generated method stub
        Barang barang = getBarangById(barangFromDTO.getSku());
        barang.setMerk(barangFromDTO.getMerk());
        barang.setHargaBarang(barangFromDTO.getHargaBarang());
        barangDb.save(barang);

        return barang;

        
    }

    
    
}
