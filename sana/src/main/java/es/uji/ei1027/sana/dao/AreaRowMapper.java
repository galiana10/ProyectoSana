package es.uji.ei1027.sana.dao;

import es.uji.ei1027.sana.model.Area;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class AreaRowMapper implements RowMapper<Area>{

    public Area mapRow(ResultSet rs, int rowNum) throws SQLException {
        Area area=new Area();

        area.setName(rs.getString("name"));
        area.setDescription(rs.getString("description"));
        area.setNumberOfZone(rs.getInt("numberOfZone"));
        area.setAccessType(rs.getString("accessType"));
        area.setGeographicalLocation(rs.getString("geographicalLocation"));
        area.setAreaType(rs.getString("areaType"));
        area.setPhysiscalCharacteristic(rs.getString("physiscalCharacteristic"));
        area.setOrientation(rs.getString("orientation"));
        area.setFacility(rs.getString("facility"));
        area.setComment(rs.getString("comment"));
        area.setImage(rs.getString("image"));
        area.setName_M(rs.getString("name_M"));



        return area;
    }
}
