package es.uji.ei1027.sana.controller;

import es.uji.ei1027.sana.Service.MMSvc;
import es.uji.ei1027.sana.dao.AreaDao;
import es.uji.ei1027.sana.dao.MunicipalManagerDao;
import es.uji.ei1027.sana.dao.MunicipalityDao;
import es.uji.ei1027.sana.dao.UserInfoDao;
import es.uji.ei1027.sana.model.Area;
import es.uji.ei1027.sana.model.MunicipalManager;
import es.uji.ei1027.sana.model.UserInfo;
import org.apache.tomcat.jni.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import javax.servlet.http.HttpSession;

import java.util.List;
import java.util.stream.Collectors;



@Controller
@RequestMapping("/area")
public class AreaController {

    private MunicipalityDao municipalityDao ;
    @Autowired
    public void setMunicipalityDao(MunicipalityDao municipalityDao) {
        this.municipalityDao=municipalityDao;
    }

    private MunicipalManagerDao municipalManagerDao ;
    @Autowired
    public void setMunicipalManagerDao(MunicipalManagerDao municipalManagerDao) {
        this.municipalityDao=municipalityDao;
    }

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
    public String listAreasPublico(Model model, @PathVariable String name_M, HttpSession session) {
        model.addAttribute("areasPublico", areaDao.getAreasMunipality(name_M));
        model.addAttribute("municipalityPublico",name_M);

        UserInfo usuario=(UserInfo) session.getAttribute("user");
        if (usuario == null ) {
            return "redirect:/";
        }

        if(usuario.getType()!=0){

            return "redirect:/";
        }

        return "area/list_publico";
    }

    @RequestMapping(value = "/informacion/{name_A}",method = RequestMethod.GET)
    public String informacionAreas(Model model,@PathVariable String name_A) {
        model.addAttribute("areasInfo", areaDao.getArea(name_A));
        return "area/informacion";
    }

    //MM
    @RequestMapping("/listMM/{name_M}")
    public String listAreasMM(HttpSession session,Model model,@PathVariable String name_M) {
        model.addAttribute("areasMM", areaDao.getAreasMunipality(name_M));
        model.addAttribute("municipalityMM",name_M);

        UserInfo user=(UserInfo) session.getAttribute("user");

        if (user == null || user.getType()!=2) {
            return "redirect:/";
        }

        return "area/list_mm";




    }

    @RequestMapping("/listMM/{name_M}/{name_A}")
    public String listServicioMM(Model model,@PathVariable String name_M,@PathVariable String name_A) {
        model.addAttribute("areasMM", areaDao.getAreasMunipality(name_M));
        model.addAttribute("municipalityMM",name_M);
        return "area/list_mm";
    }


    @RequestMapping(value="/add/{nameMunicipality}")
    public String addArea(Model model,
    @PathVariable String nameMunicipality) {
        model.addAttribute("nameMunicipality",nameMunicipality);
        model.addAttribute("area", new Area());

        return "area/add";
    }

    @RequestMapping(value="/add/{nameMunicipality}", method=RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("area") Area area,
                                   @PathVariable String nameMunicipality,
                                   BindingResult bindingResult) {


        area.setName_M(nameMunicipality);
        AreaValidator av= new AreaValidator();
        av.validate(area,bindingResult);
        List<String> areas = areaDao.getAreas().stream().map(area1 -> area1.getName()).collect(Collectors.toList());
        if(areas.contains(area.getName()))
            bindingResult.rejectValue("name","nameduplicated",
                    "Este nombre ya esta en uso");
        if (bindingResult.hasErrors())
            return "area/add";
        areaDao.addArea(area);
        return "redirect:../listMM/"+nameMunicipality;
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
