package es.uji.ei1027.sana.model;

import java.time.LocalDate;

public class ControlStaffArea {
    private String nie_cs;
    private String name_a;
    private LocalDate initialDate;
    private LocalDate finalDate;

    public LocalDate getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(LocalDate initialDate) {
        this.initialDate = initialDate;
    }

    public LocalDate getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(LocalDate finalDate) {
        this.finalDate = finalDate;
    }



    public String getNie_cs() {
        return nie_cs;
    }

    public void setNie_cs(String nie_cs) {
        this.nie_cs = nie_cs;
    }

    public String getName_a() {
        return name_a;
    }

    public void setName_a(String name_a) {
        this.name_a = name_a;
    }

    @Override
    public String toString() {
        return "ControlStaffArea{" +
                "nie_cs='" + nie_cs + '\'' +
                ", name_a='" + name_a + '\'' +
                ", initialDate=" + initialDate +
                ", finalDate=" + finalDate +
                '}';
    }
}
