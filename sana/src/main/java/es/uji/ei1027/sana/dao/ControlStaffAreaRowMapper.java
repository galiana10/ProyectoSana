package es.uji.ei1027.sana.dao;

import es.uji.ei1027.sana.model.ControlStaffArea;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ControlStaffAreaRowMapper implements RowMapper<ControlStaffArea> {


    public ControlStaffArea mapRow(ResultSet rs, int rowNum) throws SQLException {
        ControlStaffArea controlStaff_area = new ControlStaffArea();
        controlStaff_area.setNie_cs(rs.getString("NIE_CS"));
        controlStaff_area.setName_a(rs.getString("name_A"));
        controlStaff_area.setInitialDate(rs.getObject("initialDate", LocalDate.class));
        controlStaff_area.setFinalDate(rs.getObject("finalDate", LocalDate.class));

        return controlStaff_area;
    }
}
