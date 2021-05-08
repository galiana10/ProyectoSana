package es.uji.ei1027.sana.controller;

import es.uji.ei1027.sana.dao.ReservationZoneDao;
import es.uji.ei1027.sana.model.ReservationZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/reservationzone")
public class ReservationZoneController {

    private ReservationZoneDao reservationZoneDao;


    @Autowired
    public void setReservationZoneDao(ReservationZoneDao reservationZoneDao) {
        this.reservationZoneDao=reservationZoneDao;
    }

    // Operacions: Crear, llistar, actualitzar, esborrar


    @RequestMapping("/list")
    public String listReservationsZones(Model model) {
        model.addAttribute("reservationsZones", reservationZoneDao.getReservationsZones());
        return "reservation/list";
    }



    @RequestMapping(value="/add")
    public String addReservationZone(Model model) {
        model.addAttribute("reservationZone", new ReservationZone());
        return "reservationzone/add";
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("reservationzone") ReservationZone reservationZ,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "reservationzone/add";
        reservationZoneDao.addReservationZone(reservationZ);
        return "redirect:list";
    }

    @RequestMapping(value="/update/{QR}/{numberLetter_Z}/{name_Area}", method = RequestMethod.GET)
    public String editReservation(Model model, @PathVariable String QR,@PathVariable String numberLetter_Z,@PathVariable String name_Area) {
        model.addAttribute("reservationzone", reservationZoneDao.getReservationZone(QR,numberLetter_Z,name_Area));
        return "reservationzone/update";
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    public String processUpdateSubmit(
            @ModelAttribute("reservationzone") ReservationZone reservationZ,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "reservationzone/update";
       // reservationZoneDao.updateReservationZone(reservationZ);
        return "redirect:list";
    }



}
