package apap.ti.silogistik2106751543.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BerandaController {

    @GetMapping("/")
    public String beranda(Model model) {
        // Anda dapat menambahkan logika tambahan di sini jika diperlukan
        return "home/beranda"; // Ini adalah nama template (thymeleaf, jsp, atau sejenisnya)
    }
}