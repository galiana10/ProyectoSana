package es.uji.ei1027.sana.controller;

import es.uji.ei1027.sana.model.MunicipalManager;
import es.uji.ei1027.sana.model.Municipality;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class MunicipalityValidator implements Validator {
    @Override
    public boolean supports(Class<?> cls) {
        return Municipality.class.equals(cls);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Municipality m= (Municipality) target;

        if(m.getName() == null || m.getName().equals(""))
            errors.rejectValue("name", "nameEmpty",
                    "El nombre es obligatorio");

        if( m.getTlf()==0)
            errors.rejectValue("tlf", "nameEmpty",
                    "El teléfono es obligatorio");

        if(m.getAddress() == null || m.getAddress().equals(""))
            errors.rejectValue("address", "nameEmpty",
                    "La dirección es obligatoria");



    }
}
