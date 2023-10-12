package apap.ti.silogistik2106751543.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106751543.model.Karyawan;
import apap.ti.silogistik2106751543.repository.KaryawanRepository;
@Service
public class KaryawanServiceImpl implements KaryawanService {

    @Autowired
    KaryawanRepository karyawanDb;

    @Override
    public List<Karyawan> getAllKaryawan() {
        // TODO Auto-generated method stub
       return karyawanDb.findAll();
    }

    @Override
    public void save(Karyawan karyawan) {
        // TODO Auto-generated method stub
        karyawanDb.save(karyawan);
        
    }
    
}
