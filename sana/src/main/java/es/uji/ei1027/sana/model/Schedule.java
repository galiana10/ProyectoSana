package es.uji.ei1027.sana.model;

import java.time.LocalDate;

public class Schedule {
    String name_s;
    String name_a;
    LocalDate initialdate;
    LocalDate finaldate;

    public String getName_s() {
        return name_s;
    }

    public void setName_s(String name_s) {
        this.name_s = name_s;
    }

    public String getName_a() {
        return name_a;
    }

    public void setName_a(String name_a) {
        this.name_a = name_a;
    }

    public LocalDate getInitialdate() {
        return initialdate;
    }

    public void setInitialdate(LocalDate initialdate) {
        this.initialdate = initialdate;
    }

    public LocalDate getFinaldate() {
        return finaldate;
    }

    public void setFinaldate(LocalDate finaldate) {
        this.finaldate = finaldate;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "name_s='" + name_s + '\'' +
                ", name_a='" + name_a + '\'' +
                ", initialdate=" + initialdate +
                ", finaldate=" + finaldate +
                '}';
    }
}
