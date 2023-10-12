package apap.ti.silogistik2106751543.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FragmentsController {
    @GetMapping("/fragments/navbar")
    public String getNavbar() {
        return "fragments/navbar.html";
    }
    
}
