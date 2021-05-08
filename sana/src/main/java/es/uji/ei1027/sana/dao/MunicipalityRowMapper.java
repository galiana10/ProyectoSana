package es.uji.ei1027.sana.dao;

import es.uji.ei1027.sana.model.Municipality;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MunicipalityRowMapper implements RowMapper<Municipality> {
    @Override
    public Municipality mapRow(ResultSet rs, int rowNum) throws SQLException {
       Municipality municipality = new Municipality();
       municipality.setName(rs.getString("name"));
       municipality.setAddress(rs.getString("address"));
       municipality.setTlf(rs.getInt("tlf"));
       return municipality;
    }
}
