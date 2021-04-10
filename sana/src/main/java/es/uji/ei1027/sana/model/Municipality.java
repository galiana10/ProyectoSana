package es.uji.ei1027.sana.model;

public class Municipality {
    String name;
    int tlf;
    String address;

    public String getName() {
        return name;
    }

    public int getTlf() {
        return tlf;
    }

    public String getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTlf(int tlf) {
        this.tlf = tlf;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Municipality{" +
                "name='" + name + '\'' +
                ", tlf=" + tlf +
                ", address='" + address + '\'' +
                '}';
    }
}
