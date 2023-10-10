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
        throw new UnsupportedOperationException("Unimplemented method 'getBanyakGudang'");
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
        System.out.println("ADDDDAAAA1");

        for (GudangBarang gudangBarang : gudangFromDTO.getListGudangBarang()) {
            if(gudangBarang.getBarang()!=null){
                System.out.println(gudangBarang.getBarang().getSku());
                Barang barang = barangService.getBarangById(gudangBarang.getBarang().getSku());
                 List<GudangBarang> listGudangBarangByBarang = gudangBarangService.getGudangBarangByBarang(barang);
                 if(listGudangBarangByBarang!=null){System.out.println("ADDDDAAAA");}
                
                 for (GudangBarang gudangBarang2 : listGudangBarangByBarang) {
                    if(gudangBarang2.getGudang()==null){
                        gudangBarang2.setGudang(gudang);
                        gudangBarang2.setStok(gudangBarang.getStok());
                        gudangBarangService.save(gudangBarang2);

                    }else if(gudangBarang2.getGudang().equals(gudang)){
                        gudangBarang2.setStok(gudangBarang2.getStok()+gudangBarang.getStok());
                    }else{
                        saveToGudangBarang(gudangBarang.getBarang(), gudang, gudangBarang.getStok());
                    }
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
        // set listGudangBarang di entity barang
        Barang b = barangService.getBarangById(barang.getSku());
        barang.getListGudangBarang().add(gudangBarang);
        barangService.savaBarang(b);

        
    }

    
}
