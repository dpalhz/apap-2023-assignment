package apap.ti.silogistik2106751543.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106751543.model.Barang;
import apap.ti.silogistik2106751543.model.Gudang;
import apap.ti.silogistik2106751543.model.GudangBarang;
import apap.ti.silogistik2106751543.repository.GudangBarangRepository;

@Service
public class GudangBarangServiceImpl implements GudangBarangService {


    @Autowired
    GudangBarangRepository gudangBarangDb;

    @Override
    public List<GudangBarang> getAllGudangBarang() {
        // TODO Auto-generated method stub
        return gudangBarangDb.findAll();
    }

    @Override
    public List<GudangBarang> getGudangBarangByBarang(Barang barang) {
        // TODO Auto-generated method stub
        return gudangBarangDb.findByBarang(barang);
    }

    @Override
    public List<GudangBarang> getGudangBarangByGudang(Gudang gudang) {
        // TODO Auto-generated method stub
       return gudangBarangDb.findByGudang(gudang);
    }

    @Override
    public GudangBarang save(GudangBarang gudangBarang) {
        // TODO Auto-generated method stub
        return gudangBarangDb.save(gudangBarang);
    }



}