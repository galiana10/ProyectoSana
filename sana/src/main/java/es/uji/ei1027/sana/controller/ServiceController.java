package es.uji.ei1027.sana.controller;


import es.uji.ei1027.sana.dao.ServiceDao;
import es.uji.ei1027.sana.model.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/service")
public class ServiceController {

    private ServiceDao serviceDAO;

    @Autowired
    public void setServiceDAO(ServiceDao serviceDAO){this.serviceDAO=serviceDAO;}


    @RequestMapping("/list")
    public String listServices(Model model) {
        model.addAttribute("services", serviceDAO.getServices());
        return "service/list";
    }



    @RequestMapping(value="/add")
    public String addService(Model model) {
        model.addAttribute("service", new Service());
        return "service/add";
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("+") Service service,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "service/add";
        serviceDAO.addService(service);
        return "redirect:list";
    }

    @RequestMapping(value="/update/{name}", method = RequestMethod.GET)
    public String editService(Model model, @PathVariable String name) {
        model.addAttribute("service", serviceDAO.getService(name));
        return "service/update";
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    public String processUpdateSubmit(
            @ModelAttribute("service") Service service,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "service/update";
        serviceDAO.updateService(service);
        return "redirect:list";
    }


    @RequestMapping(value="/delete/{name}")
    public String processDelete(@PathVariable String name) {
        serviceDAO.deleteService(name);
        return "redirect:../list";
    }



}
