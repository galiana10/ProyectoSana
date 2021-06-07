package es.uji.ei1027.sana.controller;

import es.uji.ei1027.sana.model.Reservation;
import es.uji.ei1027.sana.model.TimeSlot;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.sql.Time;

public class TimeSlotValidator implements Validator {
    @Override
    public boolean supports(Class<?> cls) {
        return TimeSlot.class.equals(cls);
    }

    @Override
    public void validate(Object target, Errors errors) {

        TimeSlot  ts = (TimeSlot) target;


        if(ts.getInitialhour()==null)
            errors.rejectValue("initialhour","ininull",
                    "La hora inicial es obligatoria");


        if(ts.getFinalhour()==null)
            errors.rejectValue("finalhour","finnull",
                    "La hora final es obligatoria");

        if(ts.getFinalhour()!=null && ts.getFinalhour()!=null){
            if(ts.getFinalhour().isBefore(ts.getInitialhour()))
                errors.rejectValue("finalhour","badOrder",
                        "La hora final debe ser anterior a la de inicio");

        }


    }
}
