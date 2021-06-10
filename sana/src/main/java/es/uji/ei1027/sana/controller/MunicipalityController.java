package es.uji.ei1027.sana.controller;


import es.uji.ei1027.sana.Service.QRCodeService;
import es.uji.ei1027.sana.dao.MunicipalityDao;
import es.uji.ei1027.sana.model.Municipality;
import es.uji.ei1027.sana.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.io.IOException;


@Controller
@RequestMapping("/municipality")
public class MunicipalityController {

    private MunicipalityDao municipalityDao;


    @Autowired
    public void setMunicipalityDao(MunicipalityDao municipalityDao) {
        this.municipalityDao = municipalityDao;
    }

    @RequestMapping("/list")
    public String listMunicipalities(Model model) {
        model.addAttribute("municipalities", municipalityDao.getMunicipalities());
        return "municipality/list";
    }

    @RequestMapping("/listPublico")
    public String listMunicipalitiesPublico(HttpSession session,Model model) {

        model.addAttribute("municipalitiesPublico", municipalityDao.getMunicipalities());

        UserInfo user=(UserInfo) session.getAttribute("user");

        if (user == null || user.getType()!=0) {
            return "redirect:/";
        }
        return "municipality/list_publico";
    }
    @RequestMapping("/listEM")
    public String listMunicipalitiesEM(HttpSession session,Model model) {

        model.addAttribute("municipalitiesPublico", municipalityDao.getMunicipalities());

        UserInfo user=(UserInfo) session.getAttribute("user");

        if (user == null || user.getType()!=3) {
            return "redirect:/";
        }
        return "municipality/list_em";
    }

    @RequestMapping("/add")
    public String addMunicipality(HttpSession session,Model model) {
        model.addAttribute("municipality", new Municipality());

        UserInfo user=(UserInfo) session.getAttribute("user");

        if (user == null || user.getType()!=3) {
            return "redirect:/";
        }
        return "municipality/add";
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("municipality") Municipality municipality,
                                   BindingResult bindingResult) {
        MunicipalityValidator mv = new MunicipalityValidator();
        mv.validate(municipality,bindingResult);
        if (bindingResult.hasErrors())
            return "municipality/add";
        municipalityDao.addMunicipality(municipality);
        return "redirect:listEM";
    }

    @RequestMapping(value="/update/{name}", method = RequestMethod.GET)
    public String editMunicipality(Model model, @PathVariable String name) {
        model.addAttribute("municipality", municipalityDao.getMunicipality(name));
        return "municipality/update";
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    public String processUpdateSubmit(
            @ModelAttribute("municipality") Municipality municipality,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "municipality/update";
        municipalityDao.updateMunicipality(municipality);
        return "redirect:list";
    }

    @RequestMapping(value="/delete/{name}")
    public String processDelete(@PathVariable String name) {
        municipalityDao.deleteMunicipality(name);
        return "redirect:../listEM";
    }


}
