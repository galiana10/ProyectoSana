package es.uji.ei1027.sana.controller;


import es.uji.ei1027.sana.dao.MunicipalManagerDao;
import es.uji.ei1027.sana.model.MunicipalManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/municipalManager")
public class MunicipalManagerController {

    private MunicipalManagerDao mmDao;

    @Autowired
    public void setMunicipalManagerDao(MunicipalManagerDao mmDao) {
        this.mmDao = mmDao;
    }


    @RequestMapping("/list")
    public String listMunicipalManager(Model model) {
        model.addAttribute("municipalManager", mmDao.getMunicipalManager());
        return "municipalManager/list";
    }

    @RequestMapping(value="/add")
    public String addMunicipalManager(Model model) {
        model.addAttribute("municipalManager", new MunicipalManager());
        return "municipalManager/add";
    }
    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("municipalManager") MunicipalManager mm,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "municipalManager/add";
        mmDao.addMunicipalManager(mm);
        return "redirect:list";
    }

    @RequestMapping(value="/update/{nieMM}", method = RequestMethod.GET)
    public String editMunicipalManager(Model model, @PathVariable String nieMM) {
        model.addAttribute("municipalManager", mmDao.getMunicipalManager(nieMM));
        return "municipalManager/update";
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    public String processUpdateSubmit(
            @ModelAttribute("municipalManager") MunicipalManager mm,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "municipalManager/update";
        mmDao.updateMunicipalManager(mm);
        return "redirect:list";
    }

    @RequestMapping(value="/delete/{nieMM}")
    public String processDelete(@PathVariable String nieMM) {
        mmDao.deleteMunicipalManager(nieMM);
        return "redirect:../list";
    }



}
