package es.uji.ei1027.sana.controller;

import es.uji.ei1027.sana.Service.MMSvc;
import es.uji.ei1027.sana.dao.UserInfoDao;
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

import javax.servlet.http.HttpSession;


class UserValidator implements Validator {
        @Override
        public boolean supports(Class<?> cls) {
            return UserInfo.class.isAssignableFrom(cls);
        }
        @Override
        public void validate(Object obj, Errors errors) {
            UserInfo ud=(UserInfo) obj;
            if(ud.getUsername().equals(""))
                errors.rejectValue("username","obligatory","Cal introduir un valor");
            if(ud.getPassword().equals(""))
                errors.rejectValue("password","obligatory","Cal introduir un valor");

        }
}

@Controller
public class LoginController {

    MMSvc MMSvc;

    @Autowired
    private UserInfoDao userDao;

    @Autowired
    public void setMMsvc(MMSvc MMSvc){
        this.MMSvc=MMSvc;
    }


    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new UserInfo());
        return "login";
    }

    @RequestMapping(value="/login", method= RequestMethod.POST)
    public String checkLogin(@ModelAttribute("user") UserInfo user,
                             BindingResult bindingResult, HttpSession session) {
        UserValidator userValidator = new UserValidator();
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "login";
        }
        // Comprova que el login siga correcte
        // intentant carregar les dades de l'usuari
        user = userDao.loadUserByUsername(user.getUsername(), user.getPassword());
        if (user == null) {
            bindingResult.rejectValue("password", "badpw", "Usuario o contrase??a incorrecta");
            return "login";
        }
        // Autenticats correctament.
        // Guardem les dades de l'usuari autenticat a la sessio??
        session.setAttribute("user", user);
        session.setAttribute("tipo", user.getType());


        //TODO tener en cuenta a que direccion se dirije y si es el tipo de usuario adecuiado
        String nextUrl = (String) session.getAttribute("nextUrl");
        System.out.println(nextUrl);

        if(user.getType()==0){
            nextUrl = (nextUrl != null) ? nextUrl : "publico";
        }else if(user.getType()==1){
            nextUrl = (nextUrl != null) ? nextUrl : "/";
        }else if(user.getType()==2){
            String municipality=MMSvc.municipalityFromMM(user.getNie());
            session.setAttribute("municipio", municipality);
            nextUrl = (nextUrl != null) ? nextUrl : "area/listMM/"+municipality;

        }else{
            nextUrl = (nextUrl != null) ? nextUrl : "/admin";

        }

        // Torna a la pa??gina principal o si existeix a la que es volia anar
        return "redirect:"+nextUrl;
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }


}


