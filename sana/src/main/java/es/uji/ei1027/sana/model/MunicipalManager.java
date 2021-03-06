package es.uji.ei1027.sana.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class MunicipalManager extends UserInfo{
    String NIE;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date initialDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date finalDate;
    String name_M;

    public String getNIE() {
        return NIE;
    }

    public void setNIE(String NIE) {
        this.NIE = NIE;
    }



    public Date getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(Date initialDate) {
        this.initialDate = initialDate;
    }

    public Date getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(Date finalDate) {
        this.finalDate = finalDate;
    }

    public String getName_M() {
        return name_M;
    }

    public void setName_M(String name_M) {
        this.name_M = name_M;
    }

    @Override
    public String toString() {
        return "MunicipalManager{" +
                "NIE='" + NIE + '\'' +
                ", name='" + name + '\'' +
                ", initialDate=" + initialDate +
                ", finalDate=" + finalDate +
                ", name_M='" + name_M + '\'' +
                '}';
    }

}
