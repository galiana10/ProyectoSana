package es.uji.ei1027.sana.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;

public class TimeSlot {
    int id_timeslot;
    String name_a;
    @DateTimeFormat(pattern = "HH:mm:ss")
    LocalTime initialhour;
    @DateTimeFormat(pattern = "HH:mm:ss")
    LocalTime finalhour;
    String season;

    public String getName_a() {
        return name_a;
    }

    public int getId_timeslot() {
        return id_timeslot;
    }

    public void setId_timeslot(int id_timeslot) {
        this.id_timeslot = id_timeslot;
    }

    public void setName_a(String name_a) {
        this.name_a = name_a;
    }

    public LocalTime getInitialhour() {
        return initialhour;
    }

    public void setInitialhour(LocalTime initialhour) {
        this.initialhour = initialhour;
    }

    public LocalTime getFinalhour() {
        return finalhour;
    }

    public void setFinalhour(LocalTime finalhour) {
        this.finalhour = finalhour;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    @Override
    public String toString() {
        return "TimeSlot{" +
                "id_timeslot=" + id_timeslot +
                ", name_a='" + name_a + '\'' +
                ", initialhour=" + initialhour +
                ", finalhour=" + finalhour +
                ", season='" + season + '\'' +
                '}';
    }
}
