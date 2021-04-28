package es.uji.ei1027.sana.model;

public class Zone {

    private String numberLetter;
    private String name_Area;
    private Integer maxCapacity;

    public Integer getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(Integer maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public String getNumberLetter() {
        return numberLetter;
    }

    public void setNumberLetter(String numberLetter) {
        this.numberLetter = numberLetter;
    }

    public String getName_Area() {
        return name_Area;
    }

    public void setName_Area(String name_Area) {
        this.name_Area = name_Area;
    }

    @Override
    public String toString() {
        return "Zone{" +
                "numberLetter='" + numberLetter + '\'' +
                ", name_Area='" + name_Area + '\'' +
                ", maxCapacity=" + maxCapacity +
                '}';
    }
}
