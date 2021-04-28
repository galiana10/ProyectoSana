package es.uji.ei1027.sana.dao;

import es.uji.ei1027.sana.model.Schedule;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ScheduleRowMapper implements RowMapper<Schedule> {
    @Override
    public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
        Schedule schedule = new Schedule();
        schedule.setName_s(rs.getString("name_s"));
        schedule.setName_a(rs.getString("name_a"));
        schedule.setInitialdate(rs.getObject("initialDate", LocalDate.class));
        schedule.setFinaldate(rs.getObject("finalDate", LocalDate.class));
        return schedule;
    }
}
