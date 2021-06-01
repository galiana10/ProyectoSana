package es.uji.ei1027.sana.Service;

import es.uji.ei1027.sana.model.Reservation;
import es.uji.ei1027.sana.model.TimeSlot;
import es.uji.ei1027.sana.dao.ReservationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class TimeSlotOfReservation {
    @Autowired
    ReservationDao reservationDao;

    public LocalTime getInicialhour(Reservation reservation){
        TimeSlot timeSlot = reservationDao.getTimeSlot(reservation);
        return timeSlot.getInitialhour();
    }

    public LocalTime getFinalhour(Reservation reservation){
        TimeSlot timeSlot = reservationDao.getTimeSlot(reservation);
        return timeSlot.getFinalhour();
    }

}
