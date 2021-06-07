package es.uji.ei1027.sana.controller;

import es.uji.ei1027.sana.model.Area;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class AreaValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

        Area a =(Area)target;

        if(a.getName() == null || a.getName().equals(""))
            errors.rejectValue("name", "nameEmpty",
                    "El nombre es obligatorio");

        if(a.getAccessType() == null || a.getAccessType().equals(""))
            errors.rejectValue("accessType", "ACCESEmpty",
                    "La tipo de acceso es obligatorio");


        if(a.getAreaType() == null || a.getAreaType().equals(""))
            errors.rejectValue("areaType", "areatypeEmpty",
                    "La tipo de area es obligatorio");


        if(a.getDescription() == null || a.getDescription().equals(""))
            errors.rejectValue("description", "descriptionEmpty",
                    "La descripción es obligatoria");


        if(a.getFacility() == null || a.getFacility().equals(""))
            errors.rejectValue("facility", "facilityEmpty",
                    "Se debe especificar alguna facilidad");


        if(a.getGeographicalLocation() == null || a.getGeographicalLocation().equals(""))
            errors.rejectValue("geographicalLocation", "geoEmpty",
                    "La localización es obligatoria");

        if(a.getOrientation() == null || a.getOrientation().equals(""))
            errors.rejectValue("orientation", "orientetionEmpty",
                    "La orientación es obligatoria");

        if(a.getPhysiscalCharacteristic() == null || a.getPhysiscalCharacteristic().equals(""))
            errors.rejectValue("physiscalCharacteristic", "physicalEmpty",
                    "Las caracteristicas fisicas son obligatorias");

        if(a.getImage() == null || a.getImage().equals(""))
            errors.rejectValue("image", "imgEmpty",
                    "La imagen es obligatoria");

        if(a.getComment() == null || a.getComment().equals(""))
            errors.rejectValue("comment", "commentEmpty",
                    "Comentario obligatorio");

    }
}
