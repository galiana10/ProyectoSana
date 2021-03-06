package es.uji.ei1027.sana.controller;


import es.uji.ei1027.sana.Service.GeneratorService;
import es.uji.ei1027.sana.Service.RegisterSvc;
import es.uji.ei1027.sana.Service.SendEmailService;
import es.uji.ei1027.sana.dao.CitizenDao;
import es.uji.ei1027.sana.dao.UserInfoDao;
import es.uji.ei1027.sana.model.Citizen;
import es.uji.ei1027.sana.model.UserInfo;
import org.jasypt.util.password.BasicPasswordEncryptor;
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

        List<Object> params = (List<Object>) obj;

        Citizen citizen=(Citizen)params.get(0);

        List<String> emails=(List<String>) params.get(1);

        if(citizen.getName()==null || citizen.getName().equals("") ){
            errors.rejectValue("name","NAMEObligatori","Name Obligatorio");
        }

        if(citizen.getNIE()==null || citizen.getNIE().equals("")  ){
            errors.rejectValue("NIE","NIEObligatori","NIE Obligatorio");
        }

        if(citizen.getEmail()==null || citizen.getEmail().equals("") ){
            errors.rejectValue("email","EmailObligatori","Email Obligatorio");
        }else{
            if(emails.contains(citizen.getEmail())){
                errors.rejectValue("email","EmailObligatori","Email ya existe");
            }
        }

        if(citizen.getAddress()==null || citizen.getAddress().equals("") ){
            errors.rejectValue("address","AddressObligatori","Direcci??n Obligatoria");
        }

        if(citizen.getTown()==null || citizen.getTown().equals("") ){
            errors.rejectValue("town","NIEObligatori","Ciudad Obligatorio");
        }

        if(citizen.getCountry()==null || citizen.getCountry().equals("") ){
            errors.rejectValue("country","CountryObligatori","Pa??s Obligatorio");
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

    private RegisterSvc registerSvc;

    @Autowired
    public void setRegisterSvc(RegisterSvc registerSvc) {
        this.registerSvc=registerSvc;
    }


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
        BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();

        RegisterValidator rv=new RegisterValidator();



        citizen.setNIE(dni);
        citizen.setName(name);

        String username="ci"+dni;
        String password = generatorService.generateRandomString();
        citizen.setPassword(passwordEncryptor.encryptPassword(password));
        citizen.setUsername(username);




        List<Object> list = new ArrayList<>();


        list.add(citizen);
        list.add(registerSvc.listaEmails());

        rv.validate(list,bindingResult);

        if(citizenDao.getCitizen(dni)!=null){
            bindingResult.rejectValue("NIE","NIEduplicado","Usario ya existe");
        }

        if (bindingResult.hasErrors()){

            return "register";
        }

        //Con este dao ya se a??ade el UserInfo tambien
        citizenDao.addCitizen(citizen);

        String mensaje= "Usuario: "+citizen.getUsername()+ " Contrase??a: "+password;
        sendEmailService.sendEmail(citizen.getEmail(),"Bienvenido a SANA",mensaje);


        // Torna al login
        return "redirect:/login";
    }
}
