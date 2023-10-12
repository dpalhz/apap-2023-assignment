package apap.ti.silogistik2106751543.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import apap.ti.silogistik2106751543.service.BarangService;
import apap.ti.silogistik2106751543.service.KaryawanService;
import apap.ti.silogistik2106751543.service.PermintaanPengirimanBarangService;
import apap.ti.silogistik2106751543.service.PermintaanPengirimanService;
import jakarta.validation.Valid;
import apap.ti.silogistik2106751543.dto.request.CreatePermintaanPengirimanBarangDTO;
import apap.ti.silogistik2106751543.model.Barang;
import apap.ti.silogistik2106751543.model.PermintaanPengiriman;
import apap.ti.silogistik2106751543.model.PermintaanPengirimanBarang;
import apap.ti.silogistik2106751543.dto.PermintaanPengirimanMapper;

@Controller
public class PermintaanPengirimanController {

    @Autowired
    BarangService barangService;

    @Autowired 
    KaryawanService karyawanService;

    @Autowired
    PermintaanPengirimanMapper permintaanPengirimanMapper;


    @Autowired
    PermintaanPengirimanService permintaanPengirimanService;


    @Autowired
    PermintaanPengirimanBarangService permintaanPengirimanBarangService;


    @GetMapping("/permintaan-pengiriman")
    public String daftarPermintaanPengiriman(Model model) {
        
        model.addAttribute("listPermintaanPengiriman", permintaanPengirimanService.getAllPPB());
        
        return "permintaan-pengiriman/daftar-permintaan.html"; 

    }

    @GetMapping("permintaan-pengiriman/{id}")
    public String detailPermintaan(@PathVariable("id") Long id, Model model) {

        //Mendapatkan buku melalui kodeBuku
        var req = permintaanPengirimanService.getPermintaanPengirimanById(id);
        var reqDTO = permintaanPengirimanMapper.permintanPengirimanToDetailDTO(req);
        System.out.println(req.getDaftarPermintaanBarang());
        model.addAttribute("req", reqDTO);

       
        return "permintaan-pengiriman/detail.html";
    }


    @GetMapping("permintaan-pengiriman/{id}/cancel")
    public String deleteBuku(@PathVariable("id") Long id, Model model){
        
        var req = permintaanPengirimanService.getPermintaanPengirimanById(id);
        permintaanPengirimanService.deletePermintaanPengiriman(req);

        model.addAttribute("kode", req.getNomorPengiriman());


        return "permintaan-pengiriman/sukses-cancel-permintaan.html";
    }

    


    @GetMapping(value="/permintaan-pengiriman/tambah")
    public String getMethodName(Model model) {

        var permintaanPengirimanDTO = new CreatePermintaanPengirimanBarangDTO();


        model.addAttribute("permintaanPengirimanDTO", permintaanPengirimanDTO);
        model.addAttribute("listBarang", barangService.getAllBarang() );
        model.addAttribute("listKaryawan", karyawanService.getAllKaryawan());


        return "permintaan-pengiriman/buat-permintaan-pengiriman.html";
    }



    @PostMapping(value = "/permintaan-pengiriman/tambah", params = {"addRow"})
    public String addRowBarang(
        @ModelAttribute CreatePermintaanPengirimanBarangDTO createPermintaanPengirimanBarangDTO, Model model){
            if(createPermintaanPengirimanBarangDTO.getDaftarPermintaanBarang() == null || createPermintaanPengirimanBarangDTO.getDaftarPermintaanBarang().size() == 0){
                createPermintaanPengirimanBarangDTO.setDaftarPermintaanBarang(new ArrayList<>());
            }
            createPermintaanPengirimanBarangDTO.getDaftarPermintaanBarang().add(new PermintaanPengirimanBarang());
            List<Barang> listBarang = barangService.getAllBarang();
            model.addAttribute("listKaryawan", karyawanService.getAllKaryawan());
            model.addAttribute("listBarang", listBarang);
            model.addAttribute("permintaanPengirimanDTO", createPermintaanPengirimanBarangDTO);
            return "permintaan-pengiriman/buat-permintaan-pengiriman.html";

        }

    @PostMapping(value = "/permintaan-pengiriman/tambah")
    public String submitPemintaanPengirimanBarang(
        @ModelAttribute @Valid CreatePermintaanPengirimanBarangDTO createPermintaanPengirimanBarangDTO, 
        BindingResult bindingResult,
        Model model
    ) {
        if (bindingResult.hasErrors()) {
            return "error/error-viewall"; // Ganti dengan halaman yang sesuai
        }

        if(createPermintaanPengirimanBarangDTO.getDaftarPermintaanBarang()==null){
            return"home/beranda.html";
        }
        // Logika lain jika data valid
        var permintaanPengirimanFromDTO = permintaanPengirimanMapper.createPermintaanPengirimanBarangRequestDTOtoPermintaanPengiriman(createPermintaanPengirimanBarangDTO);
        List<PermintaanPengirimanBarang> ppb = permintaanPengirimanFromDTO.getDaftarPermintaanBarang();
        permintaanPengirimanService.save(permintaanPengirimanFromDTO);
       
        permintaanPengirimanBarangService.save(ppb, permintaanPengirimanFromDTO);
        permintaanPengirimanService.savePermintaanPengirimanBarang(ppb, permintaanPengirimanFromDTO);

       
        return "permintaan-pengiriman/sukses-tambah-permintaan-pengiriman.html";
    }

    @GetMapping("/filter-permintaan-pengiriman")
    public String filterPermintaanPengiriman(
            @RequestParam(name = "start-date", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            
            @RequestParam(name = "end-date", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            
            @RequestParam(name = "sku", required = false) String sku,
            Model model
    ) {
        List<PermintaanPengiriman> permintaanPengirimanDTO = permintaanPengirimanService.findPermintaanPengirimanByTanggalPengirimanAndSku(startDate, endDate, sku);


        List<Barang> listBarang = barangService.getAllBarang();
        for (PermintaanPengiriman barang : permintaanPengirimanDTO) {
            System.out.println(barang.getDaftarPermintaanBarang());
        }

        model.addAttribute("listBarang", listBarang);
        model.addAttribute("permintaanPengirimanDTO", permintaanPengirimanDTO);

        return "permintaan-pengiriman/filter-permintaan-pengiriman.html"; // Ganti dengan nama template Thymeleaf yang sesuai
    }

    @GetMapping("/filter-permintaan-pengiriman/show")
    public String showFilterForm(Model model) {
        List<Barang> listBarang = barangService.getAllBarang();

        model.addAttribute("listBarang", listBarang);
        var permintaan = new CreatePermintaanPengirimanBarangDTO();
        model.addAttribute("permintaanPengirimanDTO", permintaan);
   
        return "permintaan-pengiriman/filter-permintaan-pengiriman.html"; // Gantilah dengan nama template Thymeleaf yang sesuai
    }

   







    




    
}
