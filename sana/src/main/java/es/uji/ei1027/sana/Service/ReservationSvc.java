package es.uji.ei1027.sana.Service;


import es.uji.ei1027.sana.dao.ReservationDao;
import es.uji.ei1027.sana.dao.TimeSlotDao;
import es.uji.ei1027.sana.dao.ZoneDao;
import es.uji.ei1027.sana.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
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


    public List<String> zonasLibresEnHorario(String area, String timeSlot, LocalDate fecha) {

        List<String> zonasLibres = new ArrayList<>();

        List<String> zones = zonesFromArea(area);

        for (String zoneName : zones) {

            List<Reservation> reservationsOnZone = reservationDao.getReservationsOnZone(area, zoneName);

            List<Reservation> finalList = reservationsOnZone.stream().filter(reservation -> reservation.getDate().isEqual(fecha))
                    .filter(reservation -> reservation.getId_timeslot() == Integer.parseInt(timeSlot))
                    .collect(Collectors.toList());


            //There is no reservation on this date and timeslot
            if (finalList.size() == 0)
                zonasLibres.add(zoneName);
        }

        return zonasLibres;
    }


    public int getCapacityOfZones(List<String> zones, String nameArea) {

        int capacityTotal = 0;
        for (String zoneName : zones) {
            Zone zone = zoneDao.getZone(zoneName, nameArea);

            capacityTotal += zone.getMaxCapacity();
        }
        return capacityTotal;
    }

    public int getCapacityOfArea(String nameArea){
        List<String> zones = zonesFromArea(nameArea);
        return getCapacityOfZones(zones, nameArea);
    }

    public boolean capacityValidForZones(List<String> zones, String nameArea, int numOfPersons) {
        int capacityTotal = getCapacityOfZones(zones, nameArea);
        System.out.println("capacidad de esto = " + capacityTotal);

        if (numOfPersons > capacityTotal) {
            return false;
        }
        return true;
    }

    public String generateQr() {
        int numReserve = reservationDao.getReservations().size() + 1;
        String qr = String.valueOf(numReserve);
        return qr;
    }

    public List<ReservationZone> getZonesOfReservation(String qr_r){
        return reservationDao.getZonesOnReservation(qr_r);
    }



}
