package es.uji.ei1027.sana.controller;

import es.uji.ei1027.sana.Service.ZoneSvc;
import es.uji.ei1027.sana.dao.ZoneDao;
import es.uji.ei1027.sana.model.UserInfo;
import es.uji.ei1027.sana.model.Zone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/zone")
public class ZoneController {

    ZoneSvc zoneSvc;

    @Autowired
    public void setZoneSvc(ZoneSvc zoneSvc){
        this.zoneSvc=zoneSvc;
    }


    private ZoneDao zoneDAO;

    @Autowired
    public void setZoneDAO(ZoneDao zoneDAO){this.zoneDAO=zoneDAO;}

    //Añadir zonas

    @RequestMapping(value="/add/{Municipio}/{name_Area}")
    public String addZone(HttpSession session,Model model, @PathVariable String Municipio, @PathVariable String name_Area) {

        model.addAttribute("zona",new Zone());
        model.addAttribute("Municipio",Municipio);
        model.addAttribute("Area",name_Area);

        UserInfo user=(UserInfo) session.getAttribute("user");
        String municipio=(String) session.getAttribute("municipio");

        if (user == null || user.getType()!=2) {
            return "redirect:/";
        }else{
            if(!municipio.equals(Municipio)){
                return "redirect:/";
            }
        }

        return "zona/add";
    }


    @RequestMapping(value="/add/{Municipio}/{name_Area}", method = RequestMethod.POST)
    public String checkAddZone(@ModelAttribute("zona") Zone zone, BindingResult bindingResultModel,
                               Model model, @PathVariable String Municipio,@PathVariable String name_Area) {


        model.addAttribute("zona",new Zone());
        model.addAttribute("Municipio",Municipio);
        model.addAttribute("Area",name_Area);

        zone.setName_Area(name_Area);
        zone.setNumberLetter(zoneSvc.nombreZona(name_Area));

        zoneDAO.addZone(zone);


        return "redirect:../../../area/listMM/"+Municipio;
    }





}
