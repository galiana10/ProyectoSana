package es.uji.ei1027.sana.dao;

import es.uji.ei1027.sana.model.ControlStaff;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ControlStaffRowMapper implements RowMapper<ControlStaff> {

    public ControlStaff mapRow(ResultSet rs, int rowNum) throws SQLException {
        ControlStaff controlStaff = new ControlStaff();
        controlStaff.setNie(rs.getString("NIE"));
        controlStaff.setEmail(rs.getString("email"));
        controlStaff.setName(rs.getString("name"));

        return controlStaff;
    }
}
