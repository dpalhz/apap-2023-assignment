package apap.ti.silogistik2106751543.service;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106751543.model.PermintaanPengiriman;
import apap.ti.silogistik2106751543.model.PermintaanPengirimanBarang;
import apap.ti.silogistik2106751543.repository.PermintaaPengirimanRepository;

@Service
public class PermintaanPengirimanServiceImpl implements PermintaanPengirimanService {

    @Autowired
    PermintaaPengirimanRepository permintaaPengirimanDb;

    @Override
    public void save(PermintaanPengiriman permintaanPengiriman) {
        // TODO Auto-generated method stub
        Integer totalReq = countReq(permintaanPengiriman.getDaftarPermintaanBarang());
        int jenisLayanan = permintaanPengiriman.getJenisLayanan();
        permintaanPengiriman.setNomorPengiriman(generateNomorPengiriman(totalReq, jenisLayanan));
        permintaanPengiriman.setDaftarPermintaanBarang(null);
        permintaaPengirimanDb.save(permintaanPengiriman);
        
    }

    

    @Override
    public List<PermintaanPengiriman> getAllPPB() {
        // TODO Auto-generated method stub
        return permintaaPengirimanDb.findAll();
    }

    @Override
    public PermintaanPengiriman getPermintaanPengirimanById(Long id) {
        // TODO Auto-generated method stub
        return permintaaPengirimanDb.findById(id).get();
    }



    public int countReq(List<PermintaanPengirimanBarang> x){

        int totalReq = 0;
        for (PermintaanPengirimanBarang permintaanPengirimanBarang : x) {
            totalReq += permintaanPengirimanBarang.getKuantitasPengiriman();
        }

        return totalReq;
        
    }





     public String generateNomorPengiriman(int jumlahBarang, int jenisLayanan) {
        // Ambil 2 digit terakhir dari jumlah barang jika jumlahnya lebih dari 99
        String jumlahBarangStr = (jumlahBarang > 99) ? String.valueOf(jumlahBarang % 100) : String.format("%02d", jumlahBarang);

        // Membuat format waktu untuk 8 karakter terakhir
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        String waktuPembuatan = timeFormat.format(new Date());

        // Menyusun nomor pengiriman sesuai format
        String nomorPengiriman = "REQ" + jumlahBarangStr + getJenisLayananCode(jenisLayanan) + waktuPembuatan;

        return nomorPengiriman;
    }

    private String getJenisLayananCode(int jenisLayanan) {
        switch (jenisLayanan) {
            case 1:
                return "SAM";
            case 2:
                return "KIL";
            case 3:
                return "REG";
            case 4:
                return "HEM";
            default:
                return "UNSUPPORTED";
        }
    }



    @Override
    public void savePermintaanPengirimanBarang(List<PermintaanPengirimanBarang> permintaanPengirimanBarang, PermintaanPengiriman permintaanPengiriman) {
        // TODO Auto-generated method stub
        permintaanPengiriman.setDaftarPermintaanBarang(permintaanPengirimanBarang);
    }


    @Override
    public void deletePermintaanPengiriman(PermintaanPengiriman permintaanPengiriman) {
        // TODO Auto-generated method stub
        permintaaPengirimanDb.delete(permintaanPengiriman);
    }



    @Override
    public List<PermintaanPengiriman> findPermintaanPengirimanByTanggalPengirimanAndSku(Date startDate, Date endDate,
            String sku) {
        // TODO Auto-generated method stub
        return permintaaPengirimanDb.findPermintaanPengirimanByTanggalPengirimanAndSku(startDate, endDate, sku);
    }


}
