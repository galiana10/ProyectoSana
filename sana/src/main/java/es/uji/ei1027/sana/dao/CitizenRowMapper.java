package es.uji.ei1027.sana.dao;

import es.uji.ei1027.sana.model.Citizen;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public final class CitizenRowMapper implements RowMapper<Citizen> {

    public Citizen mapRow(ResultSet rs, int rowNum) throws SQLException {
        Citizen citizen = new Citizen();
        citizen.setName(rs.getString("name"));
        citizen.setNIE(rs.getString("NIE"));
        citizen.setEmail(rs.getString("email"));
        citizen.setAddress(rs.getString("address"));
        citizen.setAddress(rs.getString("town"));
        citizen.setCountry(rs.getString("country"));

        return citizen;
    }
}


