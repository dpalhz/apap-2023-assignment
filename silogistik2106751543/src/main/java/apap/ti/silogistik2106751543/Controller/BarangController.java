package apap.ti.silogistik2106751543.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import apap.ti.silogistik2106751543.dto.BarangMapper;
import apap.ti.silogistik2106751543.dto.request.CreateBarangRequestDTO;
import apap.ti.silogistik2106751543.dto.request.UpdateBarangRequestDTO;
import apap.ti.silogistik2106751543.dto.resoonse.BarangWithTotalStokDTO;
import apap.ti.silogistik2106751543.model.Barang;
import apap.ti.silogistik2106751543.model.GudangBarang;
import apap.ti.silogistik2106751543.service.BarangService;
import apap.ti.silogistik2106751543.service.GudangBarangService;
import jakarta.validation.Valid;

@Controller
public class BarangController {

    @Autowired
    BarangService barangService;


    @Autowired
    BarangMapper barangMapper;


    @Autowired
    GudangBarangService gudangBarangService;


    @GetMapping("/barang")
    public String viewDaftarGudang(Model model){
        List<BarangWithTotalStokDTO> listBarang = barangService.getListBarangWithTotalStok();

       
        model.addAttribute("listBarang", listBarang);

        return "barang/daftar-barang.html";
    }


    @GetMapping("barang/tambah")
    public String formAddBuku(Model model) {
        //Membuat DTO baru sebagai isian form pengguna
        var barangDTO = new CreateBarangRequestDTO();
        model.addAttribute("barangDTO", barangDTO);

        return "barang/tambah-barang.html";
    }


    @PostMapping("barang/tambah")
    public String addBuku(@Valid @ModelAttribute CreateBarangRequestDTO barangDTO, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors()
                    .stream()
                    .map(error -> {
                        if (error instanceof FieldError) {
                            FieldError fieldError = (FieldError) error;
                            return fieldError.getField() + ": " + error.getDefaultMessage();
                        }
                        return error.getDefaultMessage();
                    })
                    .collect(Collectors.toList());

            model.addAttribute("errors", errors);
            return "error/error-viewall.html";
        }

        String sku = barangService.generateSKU(barangDTO.getTipeBarang());
        barangDTO.setSku(sku);        
        var barang = barangMapper.createBarangRequestDTOToBarang(barangDTO);

        // inisiasi list gudangBarang
        
        barangService.savaBarang(barang);
        
        //Add variabel id barang ke 'id' untuk dirender di thymeleaf
        model.addAttribute("merk", barang.getMerk());

        return "barang/sukses-tambah-barang.html";
    }


    @GetMapping("barang/{id}")
    public String detailBarang(@PathVariable("id") String sku, Model model) {
        //Mendapatkan buku melalui kodeBuku
        var barangDetailDTO = barangService.getBarangDetailDTO(sku);
        model.addAttribute("barangDetailDTO", barangDetailDTO);
        return "barang/detail-barang.html";
    }


    @GetMapping("/barang/{id}/ubah")
    public String formUpdateBarang(@PathVariable("id") String id, Model model){
        var barang = barangService.getBarangById(id);
        var barangDTO = barangMapper.barangToUpdateBarangRequestDTO(barang);
        System.out.println(barangDTO.getSku());
        barangDTO.setSku(barang.getSku());
        model.addAttribute("barangDTO", barangDTO);
        return "barang/edit-barang.html";
    }

    @PostMapping("/barang/{id}/ubah")
    public String UpdateBarang(@PathVariable("id") String id, UpdateBarangRequestDTO updateBarangRequestDTO, Model model){
        var barang = barangService.getBarangById(id);


        var barangDTO = barangMapper.updateBarangRequestDTOToBarang(updateBarangRequestDTO);
        barangDTO.setSku(barang.getSku());
        Barang barangEdited  =  barangService.UpdateBarang(barangDTO);
        
        model.addAttribute("barangEdited", barangEdited);
        return "barang/sukses-edit-barang.html";
    }

    
}
