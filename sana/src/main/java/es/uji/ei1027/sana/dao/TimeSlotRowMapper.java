package es.uji.ei1027.sana.dao;

import es.uji.ei1027.sana.model.TimeSlot;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;

public class TimeSlotRowMapper implements RowMapper<TimeSlot>{
    @Override
    public TimeSlot mapRow(ResultSet rs, int rowNum) throws SQLException {
        System.out.println("Entramos en el rowmapper");
        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setId_timeslot(rs.getInt("id_timeslot"));
        timeSlot.setName_a(rs.getString("name_A"));
        timeSlot.setInitialhour(rs.getObject("initialHour", LocalTime.class));
        timeSlot.setFinalhour(rs.getObject("finalHour", LocalTime.class));
        timeSlot.setSeason(rs.getString("season"));
        return timeSlot;
    }
}
