package es.uji.ei1027.sana.controller;

import es.uji.ei1027.sana.Service.MMSvc;
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


@Controller
@RequestMapping("/ocupacion")
public class OcuapationController {

    private ReservationDao reservationDao;
    @Autowired
    public void setReservationDao (ReservationDao reservationDao){
        this.reservationDao = reservationDao;
    }

    @RequestMapping("/{area}")
    public String ocupacionPerArea(Model model, @PathVariable String area) {
        model.addAttribute("area", area);
        return "ocupation/consulta";
    }

    @RequestMapping(value = "/{area}", method = RequestMethod.POST)
    public String ocupacionPerAreaSubmit(Model model, @PathVariable String area, @RequestParam(name="date")LocalDate date, @RequestParam(name="hour")LocalDate hour) {
        model.addAttribute("area", area);
        model.addAttribute("reservas", reservationDao.getReservationsOnDate(area, fecha, hora));
        return "ocupation/consulta";
    }
}
