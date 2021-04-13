package es.uji.ei1027.sana.model;

import java.util.Date;

public class MunicipalManager {
    String NIE;
    String name;
    Date inicialDate;
    Date finalDate;
    String name_M;

    public String getNIE() {
        return NIE;
    }

    public void setNIE(String NIE) {
        this.NIE = NIE;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getInicialDate() {
        return inicialDate;
    }

    public void setInicialDate(Date inicialDate) {
        this.inicialDate = inicialDate;
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
                ", inicialDate=" + inicialDate +
                ", finalDate=" + finalDate +
                ", name_M='" + name_M + '\'' +
                '}';
    }
}
