package es.uji.ei1027.sana.Service;


import es.uji.ei1027.sana.dao.AreaDao;
import es.uji.ei1027.sana.dao.ZoneDao;
import es.uji.ei1027.sana.model.Zone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ZoneSvc {


    @Autowired
    ZoneDao zoneDao;

    @Autowired
    AreaDao areaDao;

    public String nombreZona(String name_A){

        String nombre=name_A.substring(0,2);

        List<Zone> zonas=zoneDao.getZones();

        List<Zone> zonasArea=new LinkedList<>();

        for (Zone z: zonas){
            if (z.getName_Area().equals(name_A)){
                zonasArea.add(z);
            }
        }

        String numeroZonas=String.valueOf(zonasArea.size()+1);

        String nombreFinal=numeroZonas+nombre;



        return nombreFinal;
    }

}
