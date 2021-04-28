package es.uji.ei1027.sana.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;

public class TimeSlot {
    String name_a;
    @DateTimeFormat(pattern = "HH:mm:ss")
    LocalTime initialhour;
    @DateTimeFormat(pattern = "HH:mm:ss")
    LocalTime finalhour;
    String season;

    public String getName_a() {
        return name_a;
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
                "name_A='" + name_a + '\'' +
                ", inicialHour=" + initialhour +
                ", finalHour=" + finalhour +
                ", season='" + season + '\'' +
                '}';
    }
}
