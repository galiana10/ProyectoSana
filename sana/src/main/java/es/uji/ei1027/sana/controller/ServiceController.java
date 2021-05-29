package es.uji.ei1027.sana.controller;


import es.uji.ei1027.sana.Service.ServicesSvc;
import es.uji.ei1027.sana.dao.ScheduleDao;
import es.uji.ei1027.sana.dao.ServiceDao;
import es.uji.ei1027.sana.model.Schedule;
import es.uji.ei1027.sana.model.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDate;

@Controller
@RequestMapping("/service")
public class ServiceController {

    ServicesSvc servicesSvc;

    @Autowired
    public void setServicesSvc(ServicesSvc servicesSvc){
        this.servicesSvc=servicesSvc;
    }

    private ServiceDao serviceDAO;

    @Autowired
    public void setServiceDAO(ServiceDao serviceDAO){this.serviceDAO=serviceDAO;}

    private ScheduleDao scheduleDao;

    @Autowired
    public  void setScheduleDao(ScheduleDao scheduleDao){ this.scheduleDao=scheduleDao;}


    @RequestMapping("/list")
    public String listServices(Model model) {
        model.addAttribute("services", serviceDAO.getServices());
        return "service/list";
    }

    @RequestMapping("/list/{name_M}/{name_A}")
    public String listServicesArea(Model model,@PathVariable String name_M,@PathVariable String name_A) {

        model.addAttribute("servicesSchedules", servicesSvc.listaSchedule(name_A));
        model.addAttribute("Area",name_A);
        model.addAttribute("Municipio",name_M);
        return "service/area_list";
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


    @RequestMapping(value="/delete/{nameS}/{nameA}/{InicialDate}/{municipio}")
    public String processDelete(@PathVariable String nameS,@PathVariable String nameA,@PathVariable String InicialDate,@PathVariable String municipio) {

        LocalDate ini = LocalDate.parse(InicialDate);
        scheduleDao.deleteSchedule(nameS,nameA,ini);

        //TODO mirar el redirect para que actualice una vez borrado

        return "redirect:service/list/"+municipio+"/"+nameA;
    }



}
