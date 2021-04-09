package es.uji.ei1027.sana.model;

public class Citizen {

    private String name;
    private String NIE;
    private String email;
    private String address;
    private String town;
    private String country;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNIE() {
        return NIE;
    }

    public void setNIE(String NIE) {
        this.NIE = NIE;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

   public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Citizen{" +
                "name='" + name + '\'' +
                ", NIE='" + NIE + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", town='" + town + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
