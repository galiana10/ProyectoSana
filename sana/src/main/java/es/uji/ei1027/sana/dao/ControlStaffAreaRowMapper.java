package es.uji.ei1027.sana.dao;

import es.uji.ei1027.sana.model.ControlStaffArea;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ControlStaffAreaRowMapper implements RowMapper<ControlStaffArea> {


    public ControlStaffArea mapRow(ResultSet rs, int rowNum) throws SQLException {
        ControlStaffArea controlStaff_area = new ControlStaffArea();
        controlStaff_area.setName_a(rs.getString("name_a"));
        controlStaff_area.setNie_cs(rs.getString("nie_cs"));

        return controlStaff_area;
    }
}
