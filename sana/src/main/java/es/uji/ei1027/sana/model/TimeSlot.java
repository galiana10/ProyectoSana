package es.uji.ei1027.sana.model;

import java.sql.Time;
import java.time.LocalTime;

public class TimeSlot {
    String name_A;
    LocalTime inicialHour;
    LocalTime finalHour;
    String season;

    public String getName_A() {
        return name_A;
    }

    public void setName_A(String name_A) {
        this.name_A = name_A;
    }

    public LocalTime getInicialHour() {
        return inicialHour;
    }

    public void setInicialHour(LocalTime inicialHour) {
        this.inicialHour = inicialHour;
    }

    public LocalTime getFinalHour() {
        return finalHour;
    }

    public void setFinalHour(LocalTime finalHour) {
        this.finalHour = finalHour;
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
                "name_A='" + name_A + '\'' +
                ", inicialHour=" + inicialHour +
                ", finalHour=" + finalHour +
                ", season='" + season + '\'' +
                '}';
    }
}
