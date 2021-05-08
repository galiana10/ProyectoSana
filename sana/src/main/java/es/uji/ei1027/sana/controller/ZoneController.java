package es.uji.ei1027.sana.controller;

import es.uji.ei1027.sana.dao.ZoneDao;
import es.uji.ei1027.sana.model.Zone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/zone")
public class ZoneController {
    private ZoneDao zoneDAO;

    @Autowired
    public void setZoneDAO(ZoneDao zoneDAO){this.zoneDAO=zoneDAO;}


    @RequestMapping("/list")
    public String listZones(Model model) {
        model.addAttribute("zones", zoneDAO.getZones());
        return "zones/list";
    }



    @RequestMapping(value="/add")
    public String addZone(Model model) {
        model.addAttribute("zone", new Zone());
        return "zone/add";
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("+") Zone zone,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "zone/add";
        zoneDAO.addZone(zone);
        return "redirect:list";
    }

    @RequestMapping(value="/update/{numberLetter}/{name_Area}", method = RequestMethod.GET)
    public String editZone(Model model, @PathVariable String numberLetter,@PathVariable String name_Area) {
        model.addAttribute("zone", zoneDAO.getZone(numberLetter,name_Area));
        return "zone/update";
    }





}
