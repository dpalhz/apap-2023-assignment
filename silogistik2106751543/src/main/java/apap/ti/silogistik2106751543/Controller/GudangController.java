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

import apap.ti.silogistik2106751543.dto.GudangMapper;
import apap.ti.silogistik2106751543.dto.request.CreateGudangRequestDTO;
import apap.ti.silogistik2106751543.dto.request.RestokGudangRequestDTO;
import apap.ti.silogistik2106751543.model.Barang;
import apap.ti.silogistik2106751543.model.Gudang;
import apap.ti.silogistik2106751543.model.GudangBarang;
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
        var listGudangBarang = gudang.getListGudangBarang();


        model.addAttribute("gudang", gudang);
        model.addAttribute("listGudangBarang", gudangBarangService.getGudangBarangByGudang(gudang));
        return "gudang/detail-gudang.html";
    }

    @GetMapping("/gudang/{id}/restok-barang")
    public String reStokBarang(@PathVariable("id") Long id, Model model){
        Gudang gudang = gudangService.getGudangById(id);
        var reStokGudang = gudangMapper.gudangToRestokGudangRequestDTO(gudang);


        var listGudangBarangExisting = gudangBarangService.getAllGudangBarang();
        model.addAttribute("gudangDTO", reStokGudang);
        model.addAttribute("listGudangBarangExisting", listGudangBarangExisting);
        

       

        return "gudang/form-restok.html";

    }


    @PostMapping(value = "gudang/{id}/restok-barang")
    public String reStokBarangSubmit(@PathVariable("id") Long id, Model model,  @ModelAttribute RestokGudangRequestDTO restokGudangRequestDTO){ 
        if(restokGudangRequestDTO.getListGudangBarang()==null){
            model.addAttribute("idGudang", id);
            return "gudang/no-restok.html";
        }
        System.out.println("TESTING");
        
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
            // Memasukan penulis baru (kosong) ke list, untuk dirender sebagai row baru. 
             restokGudangRequestDTO.getListGudangBarang().add(new GudangBarang());

            

             model.addAttribute("listGudangBarangExisting", gudangBarangService.getAllGudangBarang());

            

             model.addAttribute("gudangDTO", restokGudangRequestDTO);
            //  System.out.println("DEBUGG");
            //  System.out.println(restokGudangRequestDTO.getListGudangBarang());
            //  System.out.println(restokGudangRequestDTO.getIdGudang());




              return "gudang/form-restok.html";

        }

    
}
