package es.uji.ei1027.sana.controller;

import es.uji.ei1027.sana.Service.ReservationSvc;
import es.uji.ei1027.sana.dao.ReservationDao;
import es.uji.ei1027.sana.dao.ReservationZoneDao;
import es.uji.ei1027.sana.dao.TimeSlotDao;
import es.uji.ei1027.sana.model.Reservation;
import es.uji.ei1027.sana.model.ReservationZone;
import es.uji.ei1027.sana.model.TimeSlot;
import es.uji.ei1027.sana.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/reservation")
public class ReservationController {

    private ReservationDao reservationDao;
    private ReservationSvc reservationService;
    private TimeSlotDao timeSlotDao;
    private ReservationZoneDao reservationZoneDao;

    @Autowired
    public void setReservationDao(ReservationDao reservationDao) {
        this.reservationDao=reservationDao;
    }

    @Autowired
    public void setClassificacioService(ReservationSvc reservationService) {
        this.reservationService = reservationService;
    }

    @Autowired
    public void setTimeSlotDao(TimeSlotDao timeSlotDao) {
        this.timeSlotDao=timeSlotDao;
    }

    @Autowired
    public void setReservationZone(ReservationZoneDao reservationZoneDao) {
        this.reservationZoneDao=reservationZoneDao;
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
    public String addReservationInArea(Model model,HttpSession session,@PathVariable String area) {

        session.setAttribute("nextUrl",null);

        if(session.getAttribute("user")==null){
            String next = "reservation/add/".concat(area);
            session.setAttribute("nextUrl",next);
            model.addAttribute("user", new UserInfo());
            return "login";
        }

        model.addAttribute("areaName", area);
        model.addAttribute("reservation", new Reservation());
        model.addAttribute("zones", reservationService.zonesFromArea(area));
        model.addAttribute("timeslots", reservationService.timeslotsFromArea(area));
        return "reservation/add";
    }


    @RequestMapping(value="/add/{area}", method=RequestMethod.POST)
    public String processReserveSubmit(@ModelAttribute("reservation") Reservation reservation,
                                       @RequestParam(name="zonesList", required = false) List<String > zones,
                                       @RequestParam(name="timeslotSelected", required = false) String  timeslotSelect,
                                       HttpSession session,
                                       Model model,
                                       @PathVariable String area,
                                   BindingResult bindingResult) {
        //Las areas seleccionadas en el formulario
        //System.out.println(timeslotSelect);
        //TODO hacer validador para compribar la capcidad de las zonas
        // y si la franja horaria esta ocupada
        // y si estamos en un plazo correcto para hacer la reserva(entre dos dias antes y una hora antes )



        TimeSlot timeSlot;
        if(timeslotSelect==null)
            timeSlot =null;
        else
            timeSlot =  timeSlotDao.getTimeSlot(Integer.parseInt(timeslotSelect));
        List<Object> list = new ArrayList<>() ;
        list.add(reservation);
        list.add(zones);
        list.add(timeSlot);
        list.add(reservationService);
        ReservationValidator rv = new ReservationValidator();

        System.out.println(area);

        rv.validate(list,bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("areaName", area);
            model.addAttribute("zones", reservationService.zonesFromArea(area));
            model.addAttribute("timeslots", reservationService.timeslotsFromArea(area));
            return "reservation/add";
        }
        String qr = reservationService.generateQr();
        UserInfo user = (UserInfo) session.getAttribute("user");
        String nie =  user.getNie();

        reservation.setQR(qr);
        reservation.setId_timeslot(Integer.parseInt(timeslotSelect));
        reservation.setStatus("ACTIVA");
        reservation.setNIE_citizen(nie);

        ReservationZone rzone;
        System.out.println("antes de a;adir reserva");
        reservationDao.addReservation(reservation);
        System.out.println("despues de a;adir reserva");

        for(String zone : zones){
            rzone = new ReservationZone();
            rzone.setQR(qr);
            rzone.setName_Area(area);
            rzone.setNumberLetter(zone);

            reservationZoneDao.addReservationZone(rzone);
            System.out.println("despues de a;adir reserva zona");

        }
        return "reservation/completed";
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
