package apap.ti.silogistik2106751543.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import apap.ti.silogistik2106751543.dto.GudangMapper;
import apap.ti.silogistik2106751543.model.Gudang;
import apap.ti.silogistik2106751543.model.GudangBarang;
import apap.ti.silogistik2106751543.service.GudangService;

@Controller
public class GudangController {

    @Autowired
    GudangMapper gudangMapper;

    @Autowired
    GudangService gudangService;



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
        var listGudangBarang = gudang.getGudangBarang();

        System.out.println("\n");
        System.out.println("DEBUGGGG");

        System.out.println(listGudangBarang);

       

        model.addAttribute("gudang", gudang);
        model.addAttribute("listGudangBarang", listGudangBarang);
        return "gudang/detail-gudang.html";
    }
    
}
