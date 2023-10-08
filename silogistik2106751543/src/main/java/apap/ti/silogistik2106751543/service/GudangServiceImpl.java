package apap.ti.silogistik2106751543.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106751543.model.Gudang;
import apap.ti.silogistik2106751543.model.GudangBarang;
import apap.ti.silogistik2106751543.repository.GudangRepository;

@Service
public class GudangServiceImpl implements GudangService{

    @Autowired
    GudangRepository gudangDb;

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

    
}
