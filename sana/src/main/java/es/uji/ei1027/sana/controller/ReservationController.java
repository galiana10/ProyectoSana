package es.uji.ei1027.sana.controller;

import es.uji.ei1027.sana.Service.ReservationSvc;
import es.uji.ei1027.sana.dao.ReservationDao;
import es.uji.ei1027.sana.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/reservation")
public class ReservationController {

    private ReservationDao reservationDao;
    private ReservationSvc reservationService;


    @Autowired
    public void setReservationDao(ReservationDao reservationDao) {
        this.reservationDao=reservationDao;
    }

    @Autowired
    public void setClassificacioService(ReservationSvc reservationService) {
        this.reservationService = reservationService;
    }


    // Operacions: Crear, llistar, actualitzar, esborrar


    @RequestMapping("/list")
    public String listReservations(Model model) {
        model.addAttribute("reservations", reservationDao.getReservations());
        return "reservation/list";
    }



    @RequestMapping(value="/add")
    public String addReservation(Model model) {
        model.addAttribute("reservation", new Reservation());
        return "reservation/add";
    }

    @RequestMapping(value="/add", method=RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("reservation") Reservation reservation,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "reservation/add";
        reservationDao.addReservation(reservation);
        return "redirect:list";
    }


    @RequestMapping(value="/add/{area}")
    public String addReservationInArea(Model model,@PathVariable String area) {

        model.addAttribute("reservation", new Reservation());
        model.addAttribute("zones", reservationService.zonesFromArea(area));
        return "reservation/add";
    }





    @RequestMapping(value="/update/{QR}", method = RequestMethod.GET)
    public String editReservation(Model model, @PathVariable String QR) {
        model.addAttribute("reservation", reservationDao.getReservation(QR));
        return "reservation/update";
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    public String processUpdateSubmit(
            @ModelAttribute("reservation") Reservation reservation,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "reservation/update";
        reservationDao.updateReservation(reservation);
        return "redirect:list";
    }

    @RequestMapping(value="/delete/{QR}")
    public String processDelete(@PathVariable String QR) {
        reservationDao.deleteReservation(QR);
        return "redirect:../list";
    }

}
