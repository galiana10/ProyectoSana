package es.uji.ei1027.sana.controller;


import es.uji.ei1027.sana.dao.ServiceAreaDao;
import es.uji.ei1027.sana.model.ServiceArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
@RequestMapping("/serviceArea")
public class ServiceAreaController {

    private ServiceAreaDao serviceAreaDao;

    @Autowired
    public void setServiceAreaDao(ServiceAreaDao serviceAreaDao){
        this.serviceAreaDao  = serviceAreaDao;
    }




    @RequestMapping(value = "/add")
    public String addServiceArea(Model model) {
        model.addAttribute("serviceArea", new ServiceArea());
        return "";
    }


    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("serviceAREA") ServiceArea serviceArea,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "";
        serviceAreaDao.addServiceArea(serviceArea);
        return "redirect:list";
    }



}
