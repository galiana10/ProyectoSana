package es.uji.ei1027.sana.dao;

import es.uji.ei1027.sana.model.TimeSlot;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;

public class TimeSlotRowMapper implements RowMapper<TimeSlot>{
    @Override
    public TimeSlot mapRow(ResultSet rs, int rowNum) throws SQLException {
        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setName_a(rs.getString("name_A"));
        timeSlot.setInitialhour(rs.getObject("initialHour", LocalTime.class));
        timeSlot.setFinalhour(rs.getObject("finalHour", LocalTime.class));
        timeSlot.setSeason(rs.getString("season"));
        return timeSlot;
    }
}
