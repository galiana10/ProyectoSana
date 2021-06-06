package es.uji.ei1027.sana.controller;

import es.uji.ei1027.sana.Service.GeneralSVC;
import es.uji.ei1027.sana.Service.ServicesSvc;
import es.uji.ei1027.sana.dao.AreaDao;
import es.uji.ei1027.sana.dao.MunicipalityDao;
import es.uji.ei1027.sana.model.Area;
import es.uji.ei1027.sana.model.Citizen;
import es.uji.ei1027.sana.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GeneralController {

    private MunicipalityDao municipalityDao;


    @Autowired
    public void setMunicipalityDao(MunicipalityDao municipalityDao) {
        this.municipalityDao = municipalityDao;
    }


    private AreaDao areaDao;

    @Autowired
    public void setAreaDao(AreaDao areaDao) {
        this.areaDao=areaDao;
    }

    GeneralSVC generalSvc;

    @Autowired
    public void setGeneralSvc(GeneralSVC generalSvc){
        this.generalSvc=generalSvc;
    }


    @RequestMapping("/")
    public String index(Model model){

        model.addAttribute("mapa",generalSvc.mapaMunipios());

        return "index";
    }



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


