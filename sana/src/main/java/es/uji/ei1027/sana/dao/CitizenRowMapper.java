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
        citizen.setTown(rs.getString("town"));
        citizen.setCountry(rs.getString("country"));
        citizen.setUsername(rs.getString("username"));
        citizen.setPassword(rs.getString("password"));
        citizen.setType(rs.getInt("type"));

        return citizen;
    }
}
