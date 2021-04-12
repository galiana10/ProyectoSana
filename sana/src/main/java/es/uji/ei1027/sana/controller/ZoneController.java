package es.uji.ei1027.sana.controller;
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
    private ZoneDAO zoneDAO;

    @Autowired
    public void setZoneDAO(ZoneDAO zoneDAO){this.zoneDAO=zoneDAO;}


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

    @RequestMapping(value="/update/{name}", method = RequestMethod.GET)
    public String editZone(Model model, @PathVariable String numberLetter) {
        model.addAttribute("zone", zoneDAO.getZone(numberLetter));
        return "zone/update";
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    public String processUpdateSubmit(
            @ModelAttribute("zone") Zone zone,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "zone/update";
        zoneDAO.updateZone(zone);
        return "redirect:list";
    }


    @RequestMapping(value="/delete/{name}")
    public String processDelete(@PathVariable String numberLetter) {
        zoneDAO.deleteZone(numberLetter);
        return "redirect:../list";
    }



}
