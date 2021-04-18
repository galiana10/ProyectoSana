package es.uji.ei1027.sana.model;

public class ControlStaffArea {
    private String nie_cs;
    private String name_a;

    public String getNie_cs() {
        return nie_cs;
    }

    public void setNie_cs(String nie_cs) {
        this.nie_cs = nie_cs;
    }

    public String getName_a() {
        return name_a;
    }

    public void setName_a(String name_a) {
        this.name_a = name_a;
    }

    @Override
    public String toString() {
        return "ControlStaffArea{" +
                "nie_cs='" + nie_cs + '\'' +
                ", name_a='" + name_a + '\'' +
                '}';
    }
}
