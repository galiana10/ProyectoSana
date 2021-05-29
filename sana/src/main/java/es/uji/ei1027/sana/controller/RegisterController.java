package es.uji.ei1027.sana.controller;


import es.uji.ei1027.sana.model.Citizen;
import es.uji.ei1027.sana.model.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


class RegisterValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object o, Errors errors) {

    }
}

@Controller
public class RegisterController {


    @RequestMapping("/register")
    public String register(Model model) {
        model.addAttribute("citizen",new Citizen());
        model.addAttribute("user",new UserInfo());
        return "register";
    }

    @RequestMapping(value="/register", method= RequestMethod.POST)
    public String checkRegister(@ModelAttribute("citizen") Citizen citizen,
                                @ModelAttribute("user") UserInfo userInfo
            , BindingResult bindingResult) {



        // Torna al login
        return "redirect: /login";
    }
}
