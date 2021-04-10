package es.uji.ei1027.sana.controller;


import es.uji.ei1027.sana.dao.MunicipalityDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/municipalty")
public class MunicipalityController {

    private MunicipalityDao municipalityDao;


}
