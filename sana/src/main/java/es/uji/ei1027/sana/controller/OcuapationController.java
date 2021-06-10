package es.uji.ei1027.sana.controller;

import es.uji.ei1027.sana.Service.MMSvc;
import es.uji.ei1027.sana.Service.ReservationSvc;
import es.uji.ei1027.sana.dao.AreaDao;
import es.uji.ei1027.sana.dao.ReservationDao;
import es.uji.ei1027.sana.dao.UserInfoDao;
import es.uji.ei1027.sana.model.Area;
import es.uji.ei1027.sana.model.Reservation;
import es.uji.ei1027.sana.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@Controller
@RequestMapping("/ocupacion")
public class OcuapationController {

    private ReservationDao reservationDao;
    private ReservationSvc reservationSvc;

    @Autowired
    public void setReservationDao(ReservationDao reservationDao) {
        this.reservationDao = reservationDao;
    }

    @Autowired
    public void setReservationSvc(ReservationSvc reservationSvc) { this.reservationSvc = reservationSvc;}



    @RequestMapping("/{municipio}/{area}")
    public String ocupacionPerArea(Model model, @PathVariable String municipio, @PathVariable String area) {
        model.addAttribute("area", area);
        model.addAttribute("municipio", municipio);
        return "ocupation/consulta";
    }

    @RequestMapping(value = "/{municipio}/{area}", method = RequestMethod.POST)
    public String ocupacionPerAreaSubmit(Model model, @PathVariable String municipio, @PathVariable String area,
                                         @RequestParam(name = "date") String date,
                                         @RequestParam(name = "hour") String hour) {

        LocalDate localDate = LocalDate.parse(date);
        LocalTime localTime = LocalTime.parse(hour);

        System.out.println("LocalDate" + localDate + "LocalTime" + localTime);

        model.addAttribute("area", area);
        model.addAttribute("municipio", municipio);
        model.addAttribute("reservationSvc", reservationSvc);
        model.addAttribute("reservas", reservationDao.getReservationsOnDate(area, localDate, localTime));
        return "ocupation/consulta";
    }
}
