package apap.ti.silogistik2106751543.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import apap.ti.silogistik2106751543.service.BarangService;
import apap.ti.silogistik2106751543.service.GudangService;
import apap.ti.silogistik2106751543.service.KaryawanService;
import apap.ti.silogistik2106751543.service.PermintaanPengirimanService;

@Controller
public class BerandaController {



    @Autowired
    GudangService gudangService;

    @Autowired
    BarangService barangService;

    @Autowired
    KaryawanService karyawanService;

    @Autowired
    PermintaanPengirimanService permintaanPengirimanService;

    

    @GetMapping("/")
    public String beranda(Model model) {
        model.addAttribute("totalGudang",gudangService.getBanyakGudang());
        model.addAttribute("totalBarang", barangService.getAllBarang().size());
        model.addAttribute("totalPermintaanPengiriman", permintaanPengirimanService.getAllPPB().size());
        model.addAttribute("totalKaryawan", karyawanService.getAllKaryawan().size());

        return "home/beranda"; 
    }


    @GetMapping("/testing")
    public String beranda2(Model model) {
        return "home/testing.html"; 
    }

}