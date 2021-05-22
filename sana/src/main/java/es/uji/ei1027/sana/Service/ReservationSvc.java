package es.uji.ei1027.sana.Service;


import es.uji.ei1027.sana.dao.ZoneDao;
import es.uji.ei1027.sana.model.Zone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationSvc {

    @Autowired
    ZoneDao zoneDao;


    public List<String> zonesFromArea(String area){

        List<Zone> zoneList = zoneDao.getZones();

        List<String> areaZones = (List<String>) zoneList.stream().filter(zone -> zone.getName_Area().equals(area)).map(Zone::getNumberLetter).collect(Collectors.toList());

        return  areaZones;

    }
}
