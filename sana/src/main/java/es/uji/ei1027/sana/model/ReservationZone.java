package es.uji.ei1027.sana.model;

public class ReservationZone {
    String QR;
    String numberLetter;

    public String getQR() {
        return QR;
    }

    public void setQR(String QR) {
        this.QR = QR;
    }

    public String getNumberLetter() {
        return numberLetter;
    }

    public void setNumberLetter(String numberLetter) {
        this.numberLetter = numberLetter;
    }

    @Override
    public String toString() {
        return "Reservation_Zone{" +
                "QR='" + QR + '\'' +
                ", numberLetter='" + numberLetter + '\'' +
                '}';
    }
}
