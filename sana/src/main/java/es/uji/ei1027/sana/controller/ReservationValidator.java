package es.uji.ei1027.sana.controller;


import es.uji.ei1027.sana.Service.ReservationSvc;
import es.uji.ei1027.sana.dao.TimeSlotDao;
import es.uji.ei1027.sana.model.Reservation;
import es.uji.ei1027.sana.model.TimeSlot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;


public class ReservationValidator implements Validator {

    @Override
    public boolean supports(Class<?> cls) {
        return Reservation.class.equals(cls);
    }

    @Override
    public void validate(Object obj, Errors errors) {

        List<Object> params = (List<Object>) obj;

        Reservation reservation = (Reservation) params.get(0);

        TimeSlot timeslot = (TimeSlot) params.get(1);

        ReservationSvc svc = (ReservationSvc) params.get(2);

        System.out.println("entramos en el validador");

        LocalDate hoy = LocalDate.now();

        boolean anterior = false;

        if (reservation.getDate() == null) {
            errors.rejectValue("date", "dateObligatori",
                    "La fecha es obligatoria");
        }

        if (reservation.getPeopleNumber() == null) {
            System.out.println("no hay egnte");
            errors.rejectValue("peopleNumber", "peopleObligatorio",
                    "El numero de personas es obligatorio");
        }

        if (timeslot == null) {
            errors.rejectValue("id_timeslot", "horaObligatoria",
                    "La franja horaria es obligatoria");
        }

        if (reservation.getDate() != null && reservation.getDate().isBefore(hoy)) {
            anterior = true;
            errors.rejectValue("date", "diaAnterior",
                    "La reserva debe hacerse para una fecha posterior a hoy");

        }

        if (reservation.getDate() != null) {
            Period period = Period.between(hoy, reservation.getDate());
            Integer daysElapsed = period.getDays();
            if (daysElapsed > 2) {
                System.out.println("demasiados dias");

                errors.rejectValue("date", "diaPosterior",
                        "Maximo plazo para la reserva de dos dias de antelación");
            }

            if (timeslot != null) {


                LocalTime now = LocalTime.now();

                long hoursBetween = ChronoUnit.HOURS.between(now, timeslot.getInitialhour());

                if (daysElapsed == 0 && hoursBetween < 1) {
                    anterior = true;
                    System.out.println("hora anterior hoy");
                    errors.rejectValue("id_timeslot", "Hour anteior",
                            "Hora invalida debe de ser de almenos una hora de antelación");

                }
            }
        }
    }
}
