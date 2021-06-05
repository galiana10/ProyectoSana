package es.uji.ei1027.sana.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class Reservation {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private Integer peopleNumber;
    private String QR;
    private String NIE_citizen;
    private String status;
    private Integer id_timeslot;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getId_timeslot() {
        return id_timeslot;
    }

    public void setId_timeslot(Integer id_timeslot) {
        this.id_timeslot = id_timeslot;
    }



    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getPeopleNumber() {
        return peopleNumber;
    }

    public void setPeopleNumber(Integer peopleNumber) {
        this.peopleNumber = peopleNumber;
    }

    public String getQR() {
        return QR;
    }

    public void setQR(String QR) {
        this.QR = QR;
    }


    public String getNIE_citizen() {
        return NIE_citizen;
    }

    public void setNIE_citizen(String NIE_citizen) {
        this.NIE_citizen = NIE_citizen;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "date=" + date +
                ", peopleNumber=" + peopleNumber +
                ", QR='" + QR + '\'' +
                ", NIE_citizen='" + NIE_citizen + '\'' +
                ", status='" + status + '\'' +
                ", id_timeslot=" + id_timeslot +
                '}';
    }
}
