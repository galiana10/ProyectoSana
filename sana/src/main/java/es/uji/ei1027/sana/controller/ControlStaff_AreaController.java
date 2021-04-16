package es.uji.ei1027.sana.controller;


import es.uji.ei1027.sana.dao.ControlStaff_AreaDao;
import es.uji.ei1027.sana.model.ControlStaff_Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/control_staff_area")
public class ControlStaff_AreaController {
    private ControlStaff_AreaDao controlStaff_areaDao;

    @Autowired
    public void setControlStaff_areaDao (ControlStaff_AreaDao controlStaff_areaDao)
    {
        this.controlStaff_areaDao=controlStaff_areaDao;
    }


    @RequestMapping("/list")
    public String liststaffArea(Model model){
        model.addAttribute("staff_area",controlStaff_areaDao.getStaff());
        return "control_staff_area/list";
    }



    @RequestMapping(value="/add")
    public String addCitizen(Model model) {
        model.addAttribute("staff_area", new ControlStaff_Area());
        return "control_staff_area/add";
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("staff_area") ControlStaff_Area staffArea,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "control_staff_area/add";
        controlStaff_areaDao.anadeStaffArea(staffArea);
        return "redirect:list";
    }

    @RequestMapping(value="/delete/{nie}/{name}")
    public String processDelete(@PathVariable String nie,@PathVariable String name) {
        controlStaff_areaDao.deteleStaffArea(nie,name);
        return "redirect:../../list";
    }


}
