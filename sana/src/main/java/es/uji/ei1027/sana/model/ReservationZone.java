package es.uji.ei1027.sana.model;

public class ReservationZone {
    private String QR;
    private String numberLetter;
    private String name_Area;

    public String getName_Area() {
        return name_Area;
    }

    public void setName_Area(String name_Area) {
        this.name_Area = name_Area;
    }

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
        return "ReservationZone{" +
                "QR='" + QR + '\'' +
                ", numberLetter='" + numberLetter + '\'' +
                ", name_Area='" + name_Area + '\'' +
                '}';
    }
}
