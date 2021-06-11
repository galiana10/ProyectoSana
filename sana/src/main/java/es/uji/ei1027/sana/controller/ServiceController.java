package es.uji.ei1027.sana.controller;


import es.uji.ei1027.sana.Service.ServicesSvc;
import es.uji.ei1027.sana.dao.ServiceAreaDao;
import es.uji.ei1027.sana.dao.ServiceDao;
import es.uji.ei1027.sana.model.Service;
import es.uji.ei1027.sana.model.ServiceArea;
import es.uji.ei1027.sana.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


class ServiceValidator implements Validator {

    @Override
    public boolean supports(Class<?> cls) {
        return ServiceArea.class.equals(cls);
    }

    @Override
    public void validate(Object obj, Errors errors) {

        List<Object> params = (List<Object>) obj;

        ServiceArea serviceArea = (ServiceArea) params.get(0);

        List<String> serviciosArea = (List<String>) params.get(1);

        if (serviciosArea.contains(serviceArea.getName_S())) {
            errors.rejectValue("name_S", "serviceDuplicated", "El servicio ya existe");
        }

        if (serviceArea.getServicetype() == null) {
            errors.rejectValue("servicetype", "serviceTypeObligatorio", "El tipo es obligatorio");
        } else {
            if (serviceArea.getServicetype().equals("fixed") && serviceArea.getInitialdate() != null) {
                errors.rejectValue("initialdate", "incialdateNoObligatori", "Los servicios fijos no tiene fecha inicial");
            }

            if (serviceArea.getServicetype().equals("fixed") && serviceArea.getFinaldate() != null) {
                errors.rejectValue("finaldate", "finaldateNoObligatori", "Los servicios fijos no tiene fecha final");
            }

            if (serviceArea.getServicetype().equals("seasonal") && serviceArea.getInitialdate() == null) {
                errors.rejectValue("initialdate", "incialdateObligatori", "Los servicios estacionales necsitan fecha inicial");
            }

            if (serviceArea.getServicetype().equals("seasonal") && serviceArea.getFinaldate() == null) {
                errors.rejectValue("finaldate", "finaldateObligatori", "Los servicios estacionales necesitan fecha final");
            }

            if (serviceArea.getInitialdate() != null && serviceArea.getFinaldate() != null) {
                if (serviceArea.getServicetype().equals("seasonal") && (serviceArea.getInitialdate().isAfter(serviceArea.getFinaldate()))) {
                    errors.rejectValue("finaldate", "initialMayorFinal", "La fecha inicial tiene que ser anterior a la final");
                }
            }
        }


    }
}


@Controller
@RequestMapping("/service")
public class ServiceController {

    ServicesSvc servicesSvc;

    @Autowired
    public void setServicesSvc(ServicesSvc servicesSvc) {
        this.servicesSvc = servicesSvc;
    }

    private ServiceDao serviceDAO;

    @Autowired
    public void setServiceDAO(ServiceDao serviceDAO) {
        this.serviceDAO = serviceDAO;
    }


    private ServiceAreaDao serviceAreaDao;

    @Autowired
    public void setServiceAreaDao(ServiceAreaDao serviceAreaDao) {
        this.serviceAreaDao = serviceAreaDao;
    }


    //Lista servicios

    @RequestMapping("/list/{name_M}/{name_A}")
    public String listServicesArea(HttpSession session,Model model, @PathVariable String name_M, @PathVariable String name_A) {


        model.addAttribute("estacionales", servicesSvc.listaEstacionales(name_A));
        model.addAttribute("fijos", servicesSvc.listaFijos(name_A));
        model.addAttribute("servicios", servicesSvc.listaServicios(name_A));
        model.addAttribute("Area",name_A);
        model.addAttribute("Municipio",name_M);

        UserInfo user=(UserInfo) session.getAttribute("user");
        String Municipio=(String) session.getAttribute("municipio");

        if (user == null || user.getType()!=2) {
            return "redirect:/";
        }else{
            if(!Municipio.equals(name_M)){
                return "redirect:/";
            }
        }

        return "service/area_list";
    }

    @RequestMapping("/list")
    public String listServices(HttpSession session, Model model) {

        UserInfo user = (UserInfo) session.getAttribute("user");
        String Municipio = (String) session.getAttribute("municipio");

        if (user == null || user.getType() != 3) {
            return "redirect:/";
        }

        model.addAttribute("servicios", serviceDAO.getServices());
        return "service/list";
    }

    //Borrar servicios

    @RequestMapping(value = "/delete/{nameS}/{nameA}/{InicialDate}/{municipio}")
    public String processDelete(@PathVariable String nameS, @PathVariable String nameA, @PathVariable String InicialDate, @PathVariable String municipio) {

        LocalDate ini = LocalDate.parse(InicialDate);
        serviceAreaDao.deleteServiceArea(nameS, nameA, ini);

        //TODO mirar el redirect para que actualice una vez borrado

        return "redirect:../../../../list/" + municipio + "/" + nameA;
    }

    @RequestMapping(value = "/delete/{nameS}/{nameA}/{municipio}")
    public String processDelete(@PathVariable String nameS, @PathVariable String nameA, @PathVariable String municipio) {


        serviceAreaDao.deleteServiceArea(nameS, nameA);


        return "redirect:../../../list/" + municipio + "/" + nameA;
    }


    //Añadir

    @RequestMapping(value="/add/{municipio}/{nameA}")
    public String añadir(HttpSession session,Model model, @PathVariable String municipio, @PathVariable String nameA) {


        model.addAttribute("servicio", new ServiceArea());
        model.addAttribute("Area", nameA);
        model.addAttribute("Municipio", municipio);
        model.addAttribute("servicios", servicesSvc.listaServicios());
        model.addAttribute("serviciosArea", servicesSvc.listaServicios(nameA));


        UserInfo user=(UserInfo) session.getAttribute("user");
        String Municipio=(String) session.getAttribute("municipio");

        if (user == null || user.getType()!=2) {
            return "redirect:/";
        }else{
            if(!Municipio.equals(municipio)){
                return "redirect:/";
            }
        }


        return "service/selectType";
    }

    @RequestMapping(value = "/add")
    public String añadirServicio(HttpSession session, Model model) {

        UserInfo user = (UserInfo) session.getAttribute("user");
        String Municipio = (String) session.getAttribute("municipio");

        if (user == null || user.getType() != 3) {
            return "redirect:/";
        }

        model.addAttribute("servicio", new Service());


        return "service/add";
    }

    @RequestMapping(value = "/delete/{name}")
    public String añadirServicio(Model model,@PathVariable("name") String name) {

        serviceDAO.deleteService(name);

        return "redirect:/service/list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String añadirServicioSubmit(Model model, @ModelAttribute("servicio") Service servicio
            , BindingResult bindingResult) {



        if(servicio.getName()==null || servicio.getName().equals(""))
            bindingResult.rejectValue("name","nameEmpty",
                    "El nombre es obligatorio");


        if(servicio.getDescription()==null || servicio.getDescription().equals(""))
            bindingResult.rejectValue("description","descriptionEmpty",
                    "La descripción es obligatoria");

        if (bindingResult.hasErrors()) {

            return "service/add";
        }


        serviceDAO.addService(servicio);
        return "redirect:/service/list";


    }


    @RequestMapping(value = "/add/{municipio}/{nameA}", method = RequestMethod.POST)
    public String processAddFijoSubmit(Model model, @RequestParam(name = "tipoServicio", required = false) String tipoServicio, @ModelAttribute("servicio") ServiceArea serviceArea,
                                       @PathVariable String nameA,
                                       @PathVariable String municipio, BindingResult bindingResult) {

        ServiceValidator sv = new ServiceValidator();

        if (tipoServicio == null) {
            tipoServicio = null;
        }

        List<Object> list = new ArrayList<>();

        serviceArea.setName_A(nameA);
        serviceArea.setServicetype(tipoServicio);


        list.add(serviceArea);
        list.add(servicesSvc.listaNombresServicios(nameA));

        sv.validate(list, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("Area", nameA);
            model.addAttribute("Municipio", municipio);
            model.addAttribute("servicios", servicesSvc.listaServicios());
            model.addAttribute("serviciosArea", servicesSvc.listaServicios(nameA));
            return "service/selectType";
        }


        serviceAreaDao.addServiceArea(serviceArea);
        return "redirect:../../list/" + municipio + "/" + nameA;

    }


}
