package es.uji.ei1027.sana.model;

public class ControlStaff extends UserInfo {
    private String NIE;
    private String email;



    public String getNie() {
        return NIE;
    }

    public void setNie(String nie) {
        this.NIE = nie;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ControlStaff{" +
                "NIE='" + NIE + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                '}';
    }


}
