package apap.ti.silogistik2106751543.service;

import java.util.List;

import apap.ti.silogistik2106751543.model.Karyawan;

public interface KaryawanService {

    List<Karyawan> getAllKaryawan();

    void save(Karyawan karyawan);

    
}
