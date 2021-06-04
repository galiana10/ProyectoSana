package es.uji.ei1027.sana.controller;

import es.uji.ei1027.sana.Service.QRCodeService;
import es.uji.ei1027.sana.Service.ReservationSvc;
import es.uji.ei1027.sana.Service.TimeSlotOfReservation;
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
import java.io.IOException;
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
    private QRCodeService qrService;
    private TimeSlotOfReservation timeSlotOfReservation;

    @Autowired
    public void setTimeSlotOfReservation(TimeSlotOfReservation timeSlotOfReservation) {
        this.timeSlotOfReservation = timeSlotOfReservation;
    }

    @Autowired
    public void setReservationDao(ReservationDao reservationDao) {
        this.reservationDao = reservationDao;
    }

    @Autowired
    public void setClassificacioService(ReservationSvc reservationService) {
        this.reservationService = reservationService;
    }

    @Autowired
    public void setTimeSlotDao(TimeSlotDao timeSlotDao) {
        this.timeSlotDao = timeSlotDao;
    }

    @Autowired
    public void setReservationZone(ReservationZoneDao reservationZoneDao) {
        this.reservationZoneDao = reservationZoneDao;
    }

    @Autowired
    public void setQRService(QRCodeService qrService) {
        this.qrService=qrService;
    }

    // Operacions: Crear, llistar, actualitzar, esborrar


    @RequestMapping("/list")
    public String listReservations(Model model, HttpSession session ) {
        UserInfo user = (UserInfo) session.getAttribute("user");
        model.addAttribute("reservations", reservationDao.getCityzenReservations(user.getNie()));
        model.addAttribute("timeSlotOfReservation", timeSlotOfReservation);
        return "reservation/list";
    }

    @RequestMapping(value = "/add")
    public String addReservation(Model model) {
        model.addAttribute("reservation", new Reservation());
        return "reservation/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("reservation") Reservation reservation,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "reservation/add";
        reservationDao.addReservation(reservation);
        return "redirect:list";
    }


    @RequestMapping(value = "/add/{area}")
    public String addReservationInArea(Model model, HttpSession session, @PathVariable String area) {

        session.setAttribute("nextUrl", null);

        if (session.getAttribute("user") == null) {
            String next = "reservation/add/".concat(area);
            session.setAttribute("nextUrl", next);
            model.addAttribute("user", new UserInfo());
            return "login";
        }

        model.addAttribute("areaName", area);
        model.addAttribute("reservation", new Reservation());

        model.addAttribute("timeslots", reservationService.timeslotsFromArea(area));
        return "reservation/add";
    }


    @RequestMapping(value = "/add/zones/{area}")
    public String processAddZones(@ModelAttribute("reservation")Reservation reservation,
                                  @RequestParam(name="timeslotSelected", required = false) String  timeslotSelect,
                                  HttpSession session,
                                  Model model,
                                  @PathVariable String area,
                                  BindingResult bindingResult){


        ReservationValidator rv = new ReservationValidator();

        TimeSlot timeSlot;
        if(timeslotSelect==null) {
            timeSlot =null;
        }
        else {
            timeSlot =  timeSlotDao.getTimeSlot(Integer.parseInt(timeslotSelect));
        }
        List<Object> list = new ArrayList<>() ;
        list.add(reservation);
        list.add(timeSlot);
        list.add(reservationService);
        rv.validate(list,bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("areaName", area);
            model.addAttribute("timeslots", reservationService.timeslotsFromArea(area));
            return "reservation/add";
        }

        //no viene desde add/{area}
        if(reservation==null) {
            return "redirect:reservation/add/"+area;
        }

        String qr = reservationService.generateQr();
        UserInfo user = (UserInfo) session.getAttribute("user");
        String nie =  user.getNie();

        reservation.setQR(qr);
        reservation.setId_timeslot(Integer.parseInt(timeslotSelect));
        reservation.setStatus("ACTIVA");
        reservation.setNIE_citizen(nie);

        model.addAttribute("reservation", reservation);
        model.addAttribute("areaName", area);
        model.addAttribute("zones", reservationService.zonasLibresEnHorario(area,String.valueOf(reservation.getId_timeslot()),reservation.getDate()));
        return "reservation/select_zones";

    }


    @RequestMapping(value="/add/{area}", method=RequestMethod.POST)
    public String processReserveSubmit(@ModelAttribute("reservation") Reservation reservation,
                                       @RequestParam(name="zonesList", required = false) List<String > zones,
                                       HttpSession session,
                                       Model model,
                                       @PathVariable String area,
                                   BindingResult bindingResult) {
        //Las areas seleccionadas en el formulario
        //System.out.println(timeslotSelect);
        //TODO hacer validador para compribar la capcidad de las zonas
        // y si la franja horaria esta ocupada
        // y si estamos en un plazo correcto para hacer la reserva(entre dos dias antes y una hora antes )

        if(! reservationService.capacityValidForZones(zones, area,reservation.getPeopleNumber())) {

            bindingResult.rejectValue("QR", "capacidadSuperada",
                    "Se supera la capacidad permitida, seleccione mas zonas \n" + "Capacidad actual de zonas seleccionadas = " + reservationService.getCapacityOfZones(zones, area));
            model.addAttribute("reservation", reservation);
            model.addAttribute("areaName", area);
            model.addAttribute("zones", reservationService.zonasLibresEnHorario(area,String.valueOf(reservation.getId_timeslot()),reservation.getDate()));

            return "reservation/select_zones";
        }

        ReservationZone rzone;
        reservationDao.addReservation(reservation);

        for (String zone : zones) {
            rzone = new ReservationZone();
            rzone.setQR(reservation.getQR());
            rzone.setName_Area(area);
            rzone.setNumberLetter(zone);

            reservationZoneDao.addReservationZone(rzone);

        }

        try {
            model.addAttribute("qr",qrService.crateQRCode("http://localhost:8080/reservation/list",200,200));
        } catch (IOException e) {
            e.printStackTrace();
        }

        UserInfo user = (UserInfo) session.getAttribute("user");
        model.addAttribute("user",user);
        model.addAttribute("reservation",reservation);
        model.addAttribute("zones",zones);
        model.addAttribute("timeSlotOfReservation", timeSlotOfReservation);
        return "reservation/completed";
    }


    @RequestMapping(value = "/update/{QR}", method = RequestMethod.GET)
    public String editReservation(Model model, @PathVariable String QR) {
        model.addAttribute("reservation", reservationDao.getReservation(QR));
        return "reservation/update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String processUpdateSubmit(
            @ModelAttribute("reservation") Reservation reservation,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "reservation/update";
        reservationDao.updateReservation(reservation);
        return "redirect:list";
    }

    @RequestMapping(value = "/delete/{QR}")
    public String processDelete(@PathVariable String QR) {
        reservationDao.deleteReservation(QR);
        return "redirect:../list";
    }

    @RequestMapping(value = "/anular/{QR}")
    public String processCancel(@PathVariable String QR) {
        reservationDao.cancelReservation(QR);
        return "redirect:../list";
    }
}
