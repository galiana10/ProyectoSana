package es.uji.ei1027.sana.Service;

import es.uji.ei1027.sana.model.Reservation;
import es.uji.ei1027.sana.model.TimeSlot;
import es.uji.ei1027.sana.dao.ReservationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;

@Service
public class TimeSlotOfReservation {
    @Autowired
    ReservationDao reservationDao;

    public LocalTime getInicialhour(Reservation reservation) {
        TimeSlot timeSlot = reservationDao.getTimeSlot(reservation);
        return timeSlot.getInitialhour();
    }

    public LocalTime getFinalhour(Reservation reservation) {
        TimeSlot timeSlot = reservationDao.getTimeSlot(reservation);
        return timeSlot.getFinalhour();
    }

    public boolean isExpired(Reservation reservation) {
        TimeSlot timeSlot = reservationDao.getTimeSlot(reservation);
        LocalDate hoy = LocalDate.now();

        Period period = Period.between(hoy, reservation.getDate());
        Integer daysElapsed = period.getDays();
        System.out.println("Dias entre reserva y dia actual: " + daysElapsed);
        if (daysElapsed < 0) {
            System.out.println("Reserva Expirada");
            return true;
        }
        return false;
    }


}
