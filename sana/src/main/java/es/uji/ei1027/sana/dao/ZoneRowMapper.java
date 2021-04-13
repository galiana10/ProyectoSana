package es.uji.ei1027.sana.dao;

import org.springframework.jdbc.core.RowMapper;
import es.uji.ei1027.sana.model.Zone;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ZoneRowMapper implements RowMapper<Zone> {
    @Override
    public Zone mapRow(ResultSet rs, int rowNum) throws SQLException {

        Zone zone= new Zone();
        zone.setNumberLetter(rs.getString("letter"));
        zone.setName_Area(rs.getString("area"));


        return zone;
    }
}
