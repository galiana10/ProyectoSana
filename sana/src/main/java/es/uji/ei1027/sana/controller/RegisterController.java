package es.uji.ei1027.sana.controller;


import es.uji.ei1027.sana.Service.GeneratorService;
import es.uji.ei1027.sana.Service.SendEmailService;
import es.uji.ei1027.sana.dao.CitizenDao;
import es.uji.ei1027.sana.dao.UserInfoDao;
import es.uji.ei1027.sana.model.Citizen;
import es.uji.ei1027.sana.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


class RegisterValidator implements Validator {

    @Override
    public boolean supports(Class<?> cls) {
         return Citizen.class.equals(cls);
    }

    @Override
    public void validate(Object obj, Errors errors) {

        Citizen citizen=(Citizen)obj;

        if(citizen.getName()==null || citizen.getName().equals("") ){
            errors.rejectValue("name","NAMEObligatori","Name Obligatorio");
        }

        if(citizen.getNIE()==null || citizen.getNIE().equals("")  ){
            errors.rejectValue("NIE","NIEObligatori","NIE Obligatorio");
        }

        if(citizen.getEmail()==null || citizen.getEmail().equals("") ){
            errors.rejectValue("email","EmailObligatori","Email Obligatorio");
        }

        if(citizen.getAddress()==null || citizen.getAddress().equals("") ){
            errors.rejectValue("address","AddressObligatori","Dirección Obligatoria");
        }

        if(citizen.getTown()==null || citizen.getTown().equals("") ){
            errors.rejectValue("town","NIEObligatori","Ciudad Obligatorio");
        }

        if(citizen.getCountry()==null || citizen.getCountry().equals("") ){
            errors.rejectValue("country","CountryObligatori","País Obligatorio");
        }






    }
}

@Controller
public class RegisterController {

    private GeneratorService generatorService;

    @Autowired
    public void setGeneratorService(GeneratorService generatorService){this.generatorService=generatorService;}

    private SendEmailService sendEmailService;


    @Autowired
    public void setSendEmailService(SendEmailService sendEmailService){
        this.sendEmailService=sendEmailService;
    }


    private UserInfoDao userInfoDao;

    @Autowired
    public void setUserInfoDao(UserInfoDao userInfoDao){ this.userInfoDao=userInfoDao;}

    private CitizenDao citizenDao;

    @Autowired
    public void setCitizenDao(CitizenDao citizenDao){ this.citizenDao=citizenDao;}


    @RequestMapping("/register")
    public String register(Model model) {
        model.addAttribute("citizen",new Citizen());
        return "register";
    }

    @RequestMapping(value="/register", method= RequestMethod.POST)
    public String checkRegister(@RequestParam(name="name", required = false) String  name,
                                @RequestParam(name="dni", required = false) String  dni,
                                @ModelAttribute("citizen") Citizen citizen,
                                BindingResult bindingResult) {


        RegisterValidator rv=new RegisterValidator();



        citizen.setNIE(dni);
        citizen.setName(name);

        String username="ci"+dni;
        citizen.setPassword(generatorService.generateRandomString());
        citizen.setUsername(username);

        rv.validate(citizen,bindingResult);

        if(citizenDao.getCitizen(dni)!=null){
            bindingResult.rejectValue("NIE","NIEduplicado","Usario ya existe");
        }

        if (bindingResult.hasErrors()){

            return "register";
        }

        //Con este dao ya se añade el UserInfo tambien
        citizenDao.addCitizen(citizen);

        String mensaje= "Usuario: "+citizen.getUsername()+ " Contraseña: "+citizen.getPassword();
        sendEmailService.sendEmail(citizen.getEmail(),"Bienvenido a SANA",mensaje);


        // Torna al login
        return "redirect:/login";
    }
}
