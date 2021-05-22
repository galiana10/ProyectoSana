package es.uji.ei1027.sana.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GeneralController {



    @RequestMapping("/publico")
    public String publico(Model model) {

        return "publico";
    }


    @RequestMapping("/municipalmanager/{name_M}")
    public String municipalmanager(Model model,@PathVariable String name_M) {
        model.addAttribute("municipalityMM",name_M);

        return "municipalmanager";
    }
}


