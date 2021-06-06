package es.uji.ei1027.sana.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class ServiceArea {

    String name_S;
    String name_A;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate initialdate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate finaldate;
    private String servicetype;
    private String horarios;

    public String getName_S() {
        return name_S;
    }

    public void setName_S(String name_S) {
        this.name_S = name_S;
    }

    public String getName_A() {
        return name_A;
    }

    public void setName_A(String name_A) {
        this.name_A = name_A;
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

    public String getServicetype() {
        return servicetype;
    }

    public void setServicetype(String servicetype) {
        this.servicetype = servicetype;
    }

    public String getHorarios() {
        return horarios;
    }

    public void setHorarios(String horarios) {
        this.horarios = horarios;
    }

    @Override
    public String toString() {
        return "ServiceArea{" +
                "name_S='" + name_S + '\'' +
                ", name_A='" + name_A + '\'' +
                ", initialdate=" + initialdate +
                ", finaldate=" + finaldate +
                ", servicetype='" + servicetype + '\'' +
                ", horarios='" + horarios + '\'' +
                '}';
    }
}
