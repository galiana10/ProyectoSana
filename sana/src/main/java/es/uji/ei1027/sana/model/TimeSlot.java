package es.uji.ei1027.sana.model;

import java.time.LocalTime;

public class TimeSlot {
    String name_a;
    LocalTime inicialhour;
    LocalTime finalhour;
    String season;

    public String getName_a() {
        return name_a;
    }

    public void setName_a(String name_a) {
        this.name_a = name_a;
    }

    public LocalTime getInicialhour() {
        return inicialhour;
    }

    public void setInicialhour(LocalTime inicialhour) {
        this.inicialhour = inicialhour;
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
                ", inicialHour=" + inicialhour +
                ", finalHour=" + finalhour +
                ", season='" + season + '\'' +
                '}';
    }
}
