package es.uji.ei1027.sana.Service;


import es.uji.ei1027.sana.dao.AreaDao;
import es.uji.ei1027.sana.dao.MunicipalManagerDao;
import es.uji.ei1027.sana.dao.MunicipalityDao;
import es.uji.ei1027.sana.model.Area;
import es.uji.ei1027.sana.model.Municipality;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class GeneralSVC {


    @Autowired
    private MunicipalityDao municipalityDao;


    @Autowired
    private AreaDao areaDao;


    public HashMap<String, List<String>> mapaMunipios(){

        HashMap<String, List<String>> mapa=new HashMap<>();

        List<Municipality> municipios=municipalityDao.getMunicipalities();

        for (Municipality m:municipios){
            List<Area> areas=areaDao.getAreasMunipality(m.getName());
            List<String> nombresAreas=new LinkedList<>();

            for (Area a:areas){
                nombresAreas.add(a.getName());
            }
            mapa.put(m.getName(),nombresAreas);
        }

        return mapa;

    }
}
