package es.uji.ei1027.sana.model;

public class Area {
    private String name;
    private String description;
    private Integer numberOfZone;
    private String accessType;
    private String geographicalLocation;
    private String areaType;
    private String physiscalCharacteristic;
    private String orientation;
    private String facility;
    private String comment;
    private String image;
    private String name_M;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNumberOfZone() {
        return numberOfZone;
    }

    public void setNumberOfZone(Integer numberOfZone) {
        this.numberOfZone = numberOfZone;
    }

    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    public String getGeographicalLocation() {
        return geographicalLocation;
    }

    public void setGeographicalLocation(String geographicalLocation) {
        this.geographicalLocation = geographicalLocation;
    }

    public String getAreaType() {
        return areaType;
    }

    public void setAreaType(String areaType) {
        this.areaType = areaType;
    }

    public String getPhysiscalCharacteristic() {
        return physiscalCharacteristic;
    }

    public void setPhysiscalCharacteristic(String physiscalCharacteristic) {
        this.physiscalCharacteristic = physiscalCharacteristic;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName_M() {
        return name_M;
    }

    public void setName_M(String name_M) {
        this.name_M = name_M;
    }

    @Override
    public String toString() {
        return "Area{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", numberOfZone=" + numberOfZone +
                ", accessType='" + accessType + '\'' +
                ", geographicalLocation='" + geographicalLocation + '\'' +
                ", areaType='" + areaType + '\'' +
                ", physiscalCharacteristic='" + physiscalCharacteristic + '\'' +
                ", orientation='" + orientation + '\'' +
                ", facility='" + facility + '\'' +
                ", comment='" + comment + '\'' +
                ", image='" + image + '\'' +
                ", name_M='" + name_M + '\'' +
                '}';
    }
}
