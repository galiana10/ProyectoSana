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


public class ReservationValidator implements Validator{

    @Override
    public boolean supports(Class<?> cls) {
        return Reservation.class.equals(cls);
    }

    @Override
    public void validate(Object obj, Errors errors) {

        List<Object> params = (List<Object>) obj;

        Reservation reservation =  (Reservation) params.get(0);

        List<String> zones =  (List<String>) params.get(1);

        TimeSlot timeslot = (TimeSlot) params.get(2);

        ReservationSvc svc = (ReservationSvc) params.get(3);




        LocalDate hoy = LocalDate.now();
        if(reservation.getDate().isBefore(hoy)){


            System.out.println("fecha anterior");
            errors.rejectValue("dataanterior", "diaAnterior",
                    "La reserva debe hacerse para una fecha posterior a hoy");


        }


        Period period = Period.between ( hoy , reservation.getDate());
        Integer daysElapsed = period.getDays ();
        if(daysElapsed>2){
            System.out.println("demasiados dias");

            errors.rejectValue("dataposterior", "diaPosterior",
                    "Maximo plazo para la reserva de dos dias de antelación");
        }


        LocalTime now =  LocalTime.now();

        long hoursBetween = ChronoUnit.HOURS.between(now,timeslot.getInitialhour());

        if(daysElapsed==0 && hoursBetween<1) {
            System.out.println("hora anterior hoy");
            errors.rejectValue("id_timeslot", "Hour anteior",
                    "Hora invalida debe de ser de almenos una hora de antelación");

        }


        //comprobar si el timeslot de alguna zona esta en uso
        if( ! svc.zonasLibresEnHorario(zones,timeslot,reservation.getDate()))
            errors.rejectValue("ocupado", "ocupado",
                    "La zona esta ocupada en esa fecha y horario");


        if(! svc.capacityValidForZones(zones, timeslot.getName_a(),reservation.getPeopleNumber()))
            errors.rejectValue("capacidad", "capacidadSuperada",
                    "Se supera la capacidad permitida");
    }
}
