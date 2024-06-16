package apap.ti.silogistik2106751543.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106751543.dto.GudangBarangMapper;
import apap.ti.silogistik2106751543.dto.request.CreateGudangBarangRequestDTO;
import apap.ti.silogistik2106751543.model.Barang;
import apap.ti.silogistik2106751543.model.Gudang;
import apap.ti.silogistik2106751543.model.GudangBarang;
import apap.ti.silogistik2106751543.repository.GudangRepository;

@Service
public class GudangServiceImpl implements GudangService{

    @Autowired
    GudangRepository gudangDb;

    @Autowired
    GudangBarangService gudangBarangService;

    @Autowired
    BarangService barangService;

   


    @Autowired
    GudangBarangMapper gudangBarangMapper;

    @Override
    public void saveGudang(Gudang gudang) {
        // TODO Auto-generated method stub
        gudangDb.save(gudang);
    }

    @Override
    public List<Gudang> getAllGudang() {
        // TODO Auto-generated method stub
        return gudangDb.findAll();
    }

    @Override
    public Integer getBanyakGudang() {
        // TODO Auto-generated method stub
        return gudangDb.findAll().size();
    }

    @Override
    public Gudang getGudangById(Long id) {
        // TODO Auto-generated method stub
        return gudangDb.findById(id).get();
    }

    @Override
    public List<GudangBarang> getGudangBarang(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getGudangBarang'");
    }

    @Override
    public void restokBarang(Gudang gudangFromDTO) {
        // TODO Auto-generated method stub
        Gudang gudang = getGudangById(gudangFromDTO.getIdGudang());

        for (GudangBarang gudangBarang : gudangFromDTO.getListGudangBarang()) {
            System.out.println(gudangBarang.getBarang());
            if(gudangBarang.getBarang()!=null){
                if(gudangBarang.getStok()==null){
                    gudangBarang.setStok(0);
                }
                 GudangBarang gudangBarangPointer = gudangBarangService.getGudangBarangByGudangAndBarang(gudang, gudangBarang.getBarang());
                if (gudangBarangPointer!=null){
                    gudangBarangPointer.setStok(gudangBarangPointer.getStok()+gudangBarang.getStok());
                    gudangBarangService.save(gudangBarangPointer);
                }else{
                    saveToGudangBarang(gudangBarang.getBarang(), gudang, gudangBarang.getStok());
                }
            }
        }

        gudangDb.save(gudang);
        
        
    }



    public void saveToGudangBarang(Barang barang, Gudang gudang, Integer stok ){
        var gudangBarangDTO = new CreateGudangBarangRequestDTO();
        gudangBarangDTO.setBarang(barang);
        gudangBarangDTO.setStok(stok);
        gudangBarangDTO.setGudang(gudang);
        var gudangBarang = gudangBarangMapper.createRequestGudangBarangRequestDTOToGudangBarang(gudangBarangDTO);
        gudangBarangService.save(gudangBarang);
    }

    @Override
    public Integer totalStok(Gudang gudang) {
        // TODO Auto-generated method stub
        int result =0;
        List<GudangBarang> listGudangBarang = gudangBarangService.getGudangBarangByGudang(gudang);
        if (listGudangBarang!=null) {
            for (GudangBarang gudangBarang : listGudangBarang) {
            result += gudangBarang.getStok();
        }
            
        }
        
        return result;
    }

    @Override
    public List<Gudang> findAllGudangSortedByTotalStok() {
        // TODO Auto-generated method stub
        return gudangDb.findAllGudangSortedByTotalStok();
    }

    
}
