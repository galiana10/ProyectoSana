package es.uji.ei1027.sana.model;

import java.time.LocalDate;

public class Reservation {

    private LocalDate date;
    private Integer peopleNumber;
    private String QR;
    private Integer reservationLimit;
    private String NIE_citizen;

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

    public Integer getReservationLimit() {
        return reservationLimit;
    }

    public void setReservationLimit(Integer reservationLimit) {
        this.reservationLimit = reservationLimit;
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
                ", reservationLimit=" + reservationLimit +
                ", NIE_citizen='" + NIE_citizen + '\'' +
                '}';
    }
}
