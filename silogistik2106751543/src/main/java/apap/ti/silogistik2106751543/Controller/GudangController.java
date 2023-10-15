package apap.ti.silogistik2106751543.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import apap.ti.silogistik2106751543.dto.GudangMapper;
import apap.ti.silogistik2106751543.dto.request.RestokGudangRequestDTO;
import apap.ti.silogistik2106751543.model.Barang;
import apap.ti.silogistik2106751543.model.Gudang;
import apap.ti.silogistik2106751543.model.GudangBarang;
import apap.ti.silogistik2106751543.service.BarangService;
import apap.ti.silogistik2106751543.service.GudangBarangService;
import apap.ti.silogistik2106751543.service.GudangService;

@Controller
public class GudangController {

    @Autowired
    GudangMapper gudangMapper;

    @Autowired
    GudangService gudangService;

    @Autowired
    GudangBarangService gudangBarangService;

    @Autowired 
    BarangService barangService;



    @GetMapping("/gudang")
    public String viewDaftarGudang(Model model){
        List<Gudang> listGudang = gudangService.getAllGudang();


        // System.out.println(listGudang);

        //Add variabel semua bukuModel ke "ListBuku" untuk dirender pada thymeleaf
        model.addAttribute("listGudang", listGudang);
      


        return "gudang/daftar-gudang.html";
    }


    @GetMapping("gudang/{id}")
    public String detailBuku(@PathVariable("id") Long id, Model model) {
        //Mendapatkan buku melalui kodeBuku
        var gudang = gudangService.getGudangById(id);

        model.addAttribute("gudang", gudang);
        model.addAttribute("listGudangBarang", gudangBarangService.getGudangBarangByGudang(gudang));
        return "gudang/detail-gudang.html";
    }


    

    @GetMapping("/gudang/{id}/restok-barang")
    public String reStokBarang(@PathVariable("id") Long id, Model model){
        Gudang gudang = gudangService.getGudangById(id);
        var reStokGudang = gudangMapper.gudangToRestokGudangRequestDTO(gudang);

        model.addAttribute("gudangDTO", reStokGudang);
   
        

       

        return "gudang/form-restok.html";

    }


    @PostMapping(value = "gudang/{id}/restok-barang")
    public String reStokBarangSubmit(@PathVariable("id") Long id, Model model,  @ModelAttribute RestokGudangRequestDTO restokGudangRequestDTO){ 
        if(restokGudangRequestDTO.getListGudangBarang()==null){
            model.addAttribute("idGudang", id);
            return "gudang/no-restok.html";
        }
        for (GudangBarang gb : restokGudangRequestDTO.getListGudangBarang()) {
            System.out.println(gb.getStok());
            System.out.println(gb.getBarang());
            
        }
        
        // var gudangDTO = gudangMapper.restock(restokGudangRequestDTO.getIdGudang());
        var gudangFromDTO  = gudangMapper.restokGudangRequestDTOToGudang(restokGudangRequestDTO);
        gudangService.restokBarang(gudangFromDTO);


        model.addAttribute("gudang", gudangFromDTO);


        return "gudang/sukses-restok.html";

    }

    @PostMapping(value = "gudang/{id}/restok-barang", params = {"addRow"})
    public String addRowBarang(@PathVariable("id") Long id,
        @ModelAttribute RestokGudangRequestDTO restokGudangRequestDTO, Model model){
            if(restokGudangRequestDTO.getListGudangBarang() == null || restokGudangRequestDTO.getListGudangBarang().size() == 0){
                restokGudangRequestDTO.setListGudangBarang(new ArrayList<>());
            }
             restokGudangRequestDTO.getListGudangBarang().add(new GudangBarang());
             model.addAttribute("listBarang", barangService.getAllBarang());
             model.addAttribute("gudangDTO", restokGudangRequestDTO);
              return "gudang/form-restok.html";

        }



        @GetMapping("/gudang/cari-barang")
        public String cariBarangForm(@RequestParam(value = "sku", required = false) String skuBarang, Model model) {
            if (skuBarang == null) {
                // Tampilkan form pencarian
                List<Barang> listBarang = barangService.getAllBarang();
                model.addAttribute("listBarang", listBarang);
                return "gudang/cari-barang.html";
            } else {
                // Lakukan pencarian dan tampilkan hasilnya
                Barang barang = barangService.getBarangById(skuBarang);
                List<GudangBarang> gudangBarang = gudangBarangService.getGudangBarangByBarang(barang);
                List<Barang> listBarang = barangService.getAllBarang();
                model.addAttribute("listBarang", listBarang);
                model.addAttribute("gudangBarang", gudangBarang);
                return "gudang/cari-barang.html";
            }
        }
        



    
}
