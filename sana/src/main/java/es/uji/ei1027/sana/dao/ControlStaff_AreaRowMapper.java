package es.uji.ei1027.sana.dao;

import es.uji.ei1027.sana.model.ControlStaff_Area;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ControlStaff_AreaRowMapper  implements RowMapper<ControlStaff_Area> {


    public ControlStaff_Area mapRow(ResultSet rs, int rowNum) throws SQLException {
        ControlStaff_Area controlStaff_area = new ControlStaff_Area();
        controlStaff_area.setName_a(rs.getString("name_a"));
        controlStaff_area.setNie_cs(rs.getString("nie_cs"));

        return controlStaff_area;
    }
}
