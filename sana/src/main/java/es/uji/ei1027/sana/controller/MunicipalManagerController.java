package es.uji.ei1027.sana.controller;


import es.uji.ei1027.sana.Service.GeneratorService;
import es.uji.ei1027.sana.dao.MunicipalManagerDao;
import es.uji.ei1027.sana.dao.MunicipalityDao;
import es.uji.ei1027.sana.model.MunicipalManager;
import es.uji.ei1027.sana.model.UserInfo;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/municipalManager")
public class MunicipalManagerController {

    private MunicipalManagerDao mmDao;
    private MunicipalityDao municipalityDao;
    private GeneratorService generatorService;

    @Autowired
    public void setMunicipalManagerDao(MunicipalManagerDao mmDao) {
        this.mmDao = mmDao;
    }

    @Autowired
    public void setMunicipalityDao(MunicipalityDao municipalityDao) {
        this.municipalityDao = municipalityDao;
    }

    @Autowired
    public void setGeneratorService(GeneratorService generatorService) {
        this.generatorService = generatorService;
    }


    @RequestMapping("/list")
    public String listMunicipalManager(HttpSession session,Model model) {
        model.addAttribute("managers", mmDao.getMunicipalManager());
        System.out.println(generatorService.generateRandomString());

        UserInfo user=(UserInfo) session.getAttribute("user");


        if (user == null || user.getType()!=3) {
            return "redirect:/";
        }
        return "municipal_manager/list";
    }

    @RequestMapping(value = "/add")
    public String addMunicipalManager(HttpSession session,Model model) {


        model.addAttribute("municipalManager", new MunicipalManager());
        model.addAttribute("municipios", municipalityDao.getMunicipalities());

        UserInfo user=(UserInfo) session.getAttribute("user");


        if (user == null || user.getType()!=3) {
            return "redirect:/";
        }

        return "municipal_manager/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("municipalManager") MunicipalManager mm,
                                   @RequestParam(name = "name", required = false) String name,
                                   @RequestParam(name = "nie", required = false) String nie,
                                   Model model,
                                   BindingResult bindingResult) {
        mm.setName(name);
        mm.setNIE(nie);
        mm.setUsername("mm" + generatorService.generateRandomString());

        BasicPasswordEncryptor basicPasswordEncryptor=new BasicPasswordEncryptor();
        String pw=generatorService.generateRandomString();

        mm.setPassword(basicPasswordEncryptor.encryptPassword(pw));

        MunicipalManagerValidator mmv = new MunicipalManagerValidator();

        mmv.validate(mm, bindingResult);

        if (bindingResult.hasErrors()) {
            System.out.println("entro en validador");
            //model.addAttribute("municipalManager", new MunicipalManager());
            model.addAttribute("municipios", municipalityDao.getMunicipalities());
            return "municipal_manager/add";
        }
        mmDao.addMunicipalManager(mm);
        return "redirect:list";
    }

    @RequestMapping(value = "/update/{nieMM}", method = RequestMethod.GET)
    public String editMunicipalManager(Model model, @PathVariable String nieMM) {
        model.addAttribute("municipalManager", mmDao.getMunicipalManager(nieMM));
        return "municipalManager/update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String processUpdateSubmit(
            @ModelAttribute("municipalManager") MunicipalManager mm,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "municipalManager/update";
        mmDao.updateMunicipalManager(mm);
        return "redirect:list";
    }

    @RequestMapping(value = "/delete/{nieMM}")
    public String processDelete(@PathVariable String nieMM) {
        mmDao.deleteMunicipalManager(nieMM);
        return "redirect:../list";
    }


}
