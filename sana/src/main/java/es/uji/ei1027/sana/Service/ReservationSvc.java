package es.uji.ei1027.sana.Service;


import es.uji.ei1027.sana.dao.ReservationDao;
import es.uji.ei1027.sana.dao.TimeSlotDao;
import es.uji.ei1027.sana.dao.ZoneDao;
import es.uji.ei1027.sana.model.Reservation;
import es.uji.ei1027.sana.model.TimeSlot;
import es.uji.ei1027.sana.model.Zone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationSvc {

    @Autowired
    ZoneDao zoneDao;


    @Autowired
    ReservationDao reservationDao;

    @Autowired
    TimeSlotDao timeSlotDao;

    public List<String> zonesFromArea(String area) {

        List<Zone> zoneList = zoneDao.getZones();

        List<String> areaZones = (List<String>) zoneList.stream().filter(zone -> zone.getName_Area().equals(area)).map(Zone::getNumberLetter).collect(Collectors.toList());

        return areaZones;

    }


    public List<TimeSlot> timeslotsFromArea(String area) {

        List<TimeSlot> timeslotList = timeSlotDao.getTimeSlots();

        List<TimeSlot> areaTimeslots = (List<TimeSlot>) timeslotList.stream().filter(timeSlot -> timeSlot.getName_a().equals(area)).collect(Collectors.toList());

        return areaTimeslots;
    }


    public boolean zonasLibresEnHorario(List<String> zones, TimeSlot timeSlot, LocalDate fecha) {

        for (String zoneName : zones) {

            List<Reservation> reservationsOnZone = reservationDao.getReservationsOnZone(timeSlot.getName_a(), zoneName);

            List<Reservation> finalList =  reservationsOnZone.stream().filter(reservation -> reservation.getDate().isEqual(fecha))
                    .filter(reservation -> reservation.getId_timeslot() == timeSlot.getId_timeslot())
                    .collect(Collectors.toList());

            if(finalList.size()>0)
                return false;
        }

        return true;
    }


    public boolean capacityValidForZones(List<String> zones, String nameArea, int numOfPersons){
        int capacityTotal=0;
        for (String zoneName : zones) {
            Zone zone = zoneDao.getZone(zoneName,nameArea);

            capacityTotal+=zone.getMaxCapacity();
        }
        if(numOfPersons>capacityTotal){
            return false;
        }
        return true;

    }

}
