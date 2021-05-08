package es.uji.ei1027.sana.controller;


import es.uji.ei1027.sana.dao.ControlStaffDao;
import es.uji.ei1027.sana.model.ControlStaff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/control_staff")
public class ControlSatffController {

    private ControlStaffDao controlStaffDao ;

    @Autowired
    public void setControlStaffDaoDao(ControlStaffDao controlStaffDao) {
        this.controlStaffDao=controlStaffDao;
    }




    @RequestMapping("/list")
    public String listCitizens(Model model) {
        model.addAttribute("staff", controlStaffDao.getStaff());
        return "control_staff/list";
    }


    @RequestMapping(value="/add")
    public String addCitizen(Model model) {
        model.addAttribute("staff", new ControlStaff());
        return "control_staff/add";
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("staff") ControlStaff staff,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "control_staff/add";
        controlStaffDao.addStaff(staff);
        return "redirect:list";
    }


    @RequestMapping(value="/update/{NIE}", method = RequestMethod.GET)
    public String editStaff(Model model, @PathVariable String NIE) {
        model.addAttribute("staff", controlStaffDao.getstaff(NIE));
        return "control_staff/update";
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    public String processUpdateSubmit(
            @ModelAttribute("staff") ControlStaff staff,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "control_list/update";
        controlStaffDao.updateStaff(staff);
        return "redirect:list";
    }

    @RequestMapping(value="/delete/{NIE}")
    public String processDelete(@PathVariable String NIE) {
        controlStaffDao.deleteStaff(NIE);
        return "redirect:../list";
    }




}
