package es.uji.ei1027.sana.model;

public class ControlStaff {
    private String NIE;
    private String email;
    private String name;


    public String getNie() {
        return NIE;
    }

    public void setNie(String nie) {
        this.NIE = nie;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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


    //TODO incluir atributo username y devolverlo
    public String getUsername() {
        return null;
    }


    //TODO incluir atributo password y devolverlo(revisar la encriptacion)
    public String getPassword() {
        return null;
    }
}
