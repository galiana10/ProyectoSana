package es.uji.ei1027.sana.controller;

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
                errors.rejectValue("nom","obligatory","Cal introduir un valor");
            if(ud.getPassword().equals(""))
                errors.rejectValue("password","obligatory","Cal introduir un valor");

        }
    }

@Controller
public class LoginController {

    @Autowired
    private UserInfoDao userDao;

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
            bindingResult.rejectValue("password", "badpw", "Contrasenya incorrecta");
            return "login";
        }
        // Autenticats correctament.
        // Guardem les dades de l'usuari autenticat a la sessió
        session.setAttribute("user", user);

        //TODO tener en cuenta a que direccion se dirije y si es el tipo de usuario adecuiado
        String nextUrl = (String) session.getAttribute("nextUrl");

        if(user.getType()==0){
            nextUrl = (nextUrl != null) ? nextUrl : "publico";
        }else if(user.getType()==1){
            nextUrl = (nextUrl != null) ? nextUrl : "/";
        }else if(user.getType()==2){
            nextUrl = (nextUrl != null) ? nextUrl : "/";
        }


        // Torna a la pàgina principal o si existeix a la que es volia anar
        return "redirect:"+nextUrl;
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }


}


