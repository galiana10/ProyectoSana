package es.uji.ei1027.sana.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei1027.sana.dao.CitizenDao;
import es.uji.ei1027.sana.model.Citizen;

@Controller
@RequestMapping("/citizen")
public class CitizenController {

    private CitizenDao citizenDao;

    @Autowired
    public void setCitizenDao(CitizenDao citizenDao) {
        this.citizenDao=citizenDao;
    }

    // Operacions: Crear, llistar, actualitzar, esborrar


    @RequestMapping("/list")
    public String listCitizens(Model model) {
        model.addAttribute("citizens", citizenDao.getCitizens());
        return "citizen/list";
    }



    @RequestMapping(value="/add")
    public String addCitizen(Model model) {
        model.addAttribute("citizen", new Citizen());
        return "citizen/add";
    }

    @RequestMapping(value="/add", method=RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("citizen") Citizen citizen,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "citizen/add";
        citizenDao.addCitizen(citizen);
        return "redirect:list";
    }

    @RequestMapping(value="/update/{NIE}", method = RequestMethod.GET)
    public String editCitizen(Model model, @PathVariable String NIE) {
        model.addAttribute("citizen", citizenDao.getCitizen(NIE));
        return "citizen/update";
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    public String processUpdateSubmit(
            @ModelAttribute("citizen") Citizen citizen,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "citizen/update";
        citizenDao.updateCitizen(citizen);
        return "redirect:list";
    }

    @RequestMapping(value="/delete/{NIE}")
    public String processDelete(@PathVariable String NIE) {
        citizenDao.deleteCitizen(NIE);
        return "redirect:../list";
    }


}
