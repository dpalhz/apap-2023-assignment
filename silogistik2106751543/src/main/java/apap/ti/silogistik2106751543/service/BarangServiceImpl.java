package apap.ti.silogistik2106751543.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106751543.model.Barang;
import apap.ti.silogistik2106751543.repository.BarangRepository;

@Service
public class BarangServiceImpl implements BarangService{

    @Autowired
    BarangRepository barangDb;

    @Override
    public void savaBarang(Barang barang) {
        // TODO Auto-generated method stub
        barangDb.save(barang);
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
    
}
