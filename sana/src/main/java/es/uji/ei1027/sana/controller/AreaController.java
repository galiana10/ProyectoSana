package es.uji.ei1027.sana.controller;

import es.uji.ei1027.sana.Service.MMSvc;
import es.uji.ei1027.sana.dao.AreaDao;
import es.uji.ei1027.sana.dao.UserInfoDao;
import es.uji.ei1027.sana.model.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/area")
public class AreaController {

    private AreaDao areaDao;
    @Autowired
    public void setAreaDao(AreaDao areaDao) {
        this.areaDao=areaDao;
    }

    @Autowired
    private UserInfoDao userDao;

    MMSvc MMSvc;
    @Autowired
    public void setMMsvc(MMSvc MMSvc){
        this.MMSvc=MMSvc;
    }


    @RequestMapping("/list")
    public String listAreas(Model model) {
        model.addAttribute("areas", areaDao.getAreas());
        return "area/list";
    }

    //Publico
    @RequestMapping("/listPublico/{name_M}")
    public String listAreasPublico(Model model,@PathVariable String name_M) {
        model.addAttribute("areasPublico", areaDao.getAreasMunipality(name_M));
        model.addAttribute("municipalityPublico",name_M);
        return "area/list_publico";
    }

    @RequestMapping(value = "/informacion/{name_A}",method = RequestMethod.GET)
    public String informacionAreas(Model model,@PathVariable String name_A) {
        model.addAttribute("areasInfo", areaDao.getArea(name_A));
        return "area/informacion";
    }

    //MM
    @RequestMapping("/listMM/{name_M}")
    public String listAreasMM(Model model,@PathVariable String name_M) {
        model.addAttribute("areasMM", areaDao.getAreasMunipality(name_M));
        model.addAttribute("municipalityMM",name_M);
        return "area/list_mm";
    }

    @RequestMapping("/listMM/{name_M}/{name_A}")
    public String listServicioMM(Model model,@PathVariable String name_M,@PathVariable String name_A) {
        model.addAttribute("areasMM", areaDao.getAreasMunipality(name_M));
        model.addAttribute("municipalityMM",name_M);
        return "area/list_mm";
    }


    @RequestMapping(value="/add")
    public String addArea(Model model) {
        model.addAttribute("area", new Area());
        return "area/add";
    }

    @RequestMapping(value="/add", method=RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("area") Area area,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "area/add";
        areaDao.addArea(area);
        return "redirect:list";
    }

    @RequestMapping(value="/update/{municipality}/{nameArea}", method = RequestMethod.GET)
    public String editArea(Model model, @PathVariable String municipality, @PathVariable String nameArea ) {
        model.addAttribute("municipality", municipality);
        model.addAttribute("area", areaDao.getArea(nameArea));
        return "area/update";
    }

    @RequestMapping(value="/update/{municipality}", method = RequestMethod.POST)
    public String processUpdateSubmit(
            @PathVariable String municipality,
            @ModelAttribute("area") Area area,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "area/update";
        areaDao.updateArea(area);

        return "redirect:../../area/listMM/"+municipality;
    }

    @RequestMapping(value="/delete/{name}")
    public String processDelete(@PathVariable String name) {
        areaDao.deleteArea(name);
        return "redirect:../list";
    }

}
