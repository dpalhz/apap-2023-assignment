package apap.ti.silogistik2106751543.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106751543.model.Barang;
import apap.ti.silogistik2106751543.model.PermintaanPengiriman;
import apap.ti.silogistik2106751543.model.PermintaanPengirimanBarang;
import apap.ti.silogistik2106751543.repository.PermintaanPengirimanBarangRepository;
@Service
public class PermintaanPengirimanBarangServiceImpl implements PermintaanPengirimanBarangService {


    @Autowired
    PermintaanPengirimanBarangRepository ppbDb;

    @Autowired 
    PermintaanPengirimanService ppService;

    @Override
    public void save(List<PermintaanPengirimanBarang> permintaanPengirimanBarang, PermintaanPengiriman permintaanPengiriman) {
        // TODO Auto-generated method stub

        for (PermintaanPengirimanBarang permintaanPengirimanBarang2 : permintaanPengirimanBarang) {

            PermintaanPengirimanBarang ppbChecking = getPermintaanPengirimanBarangByBarangAndPermintaanPengiriman(permintaanPengirimanBarang2.getBarang(), permintaanPengiriman);
            if(ppbChecking==null){
                permintaanPengirimanBarang2.setPermintaanPengiriman(permintaanPengiriman);
                ppbDb.save(permintaanPengirimanBarang2);

            }else{
                ppbChecking.setKuantitasPengiriman(ppbChecking.getKuantitasPengiriman()+permintaanPengirimanBarang2.getKuantitasPengiriman());
                ppbDb.save(ppbChecking);
            }

            
            
            
        }
    }

    @Override
    public List<PermintaanPengirimanBarang> getPermintaanPengirimanBarangByReq(
            PermintaanPengiriman permintaanPengiriman) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPermintaanPengirimanBarangByReq'");
    }

    @Override
    public PermintaanPengirimanBarang getPermintaanPengirimanBarangByBarangAndPermintaanPengiriman(Barang barang,
            PermintaanPengiriman permintaanPengiriman) {
        // TODO Auto-generated method stub
        return ppbDb.findByBarangAndPermintaanPengiriman(barang, permintaanPengiriman);
    }
    
}
