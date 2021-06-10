package es.uji.ei1027.sana.controller;

import es.uji.ei1027.sana.model.MunicipalManager;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class MunicipalManagerValidator implements Validator {


    @Override
    public boolean supports(Class<?> cls) {
        return MunicipalManager.class.equals(cls);
    }

    @Override
    public void validate(Object target, Errors errors) {

        MunicipalManager mm = (MunicipalManager) target;
        if(mm.getName()==null || mm.getName().equals("")){
            errors.rejectValue("name", "nameEmpty",
                    "El nombre es obligatorio");
        }

        if(mm.getNIE()==null || mm.getNIE().equals(""))
            errors.rejectValue("nie", "nieEmpty",
                    "El DNI es obligatorio");


        if(mm.getInitialDate()==null){
            errors.rejectValue("initialDate", "inidateEmpty",
                    "El la fecha de inicio es obligatoria");
        }
        if(mm.getInitialDate() != null && mm.getFinalDate()!=null){

            if(mm.getFinalDate().before(mm.getInitialDate())){
                errors.rejectValue("finalDate", "findateEmpty",
                        "El la fecha final ha de ser posterior a la de inicio");
            }

        }


    }
}
