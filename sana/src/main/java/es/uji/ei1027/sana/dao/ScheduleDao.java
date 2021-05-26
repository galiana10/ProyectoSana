package es.uji.ei1027.sana.dao;

import es.uji.ei1027.sana.model.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ScheduleDao {
    private JdbcTemplate jdbcTemplate;

    // Obté el jdbcTemplate a partir del Data Source
    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /* Afegeix el schedule a la base de dades */
    public void addSchedule(Schedule schedule) {
        jdbcTemplate.update("INSERT INTO SCHEDULE VALUES(?, ?, ?,?)",
                schedule.getName_s(), schedule.getName_a(), schedule.getInitialdate(), schedule.getFinaldate());
    }

    /* Esborra el schedule de la base de dades por Objeto*/
    public void deleteSchedule(Schedule schedule) {
        jdbcTemplate.update("DELETE FROM SCHEDULE WHERE name_S='" + schedule.getName_s() + "' AND name_A='" + schedule.getName_a() + "' AND initialDate='" + schedule.getInitialdate() + "'  ");
    }

    /* Esborra el schedule de la base de dades por name_s, name_a, initialdate*/
    public void deleteSchedule(String name_s, String name_a, LocalDate initialdate) {
        jdbcTemplate.update("DELETE FROM SCHEDULE WHERE name_s='" + name_s + "' AND name_a='" + name_a + "' AND initialdate='" + initialdate + "' ");
    }

    /* Actualitza els atributs del schedule*/
    public void updateTimeSlot(Schedule schedule) {
        jdbcTemplate.update("UPDATE SCHEDULE SET finaldate=? WHERE name_s=? AND name_a=? AND initialdate=?",
                schedule.getFinaldate(),schedule.getName_s(),schedule.getName_a(), schedule.getInitialdate());
    }

    /* Obté el schedule amb el name_s, name_a and initialDate, Torna null si no existeix. */
    public Schedule getSchedule(String name_s, String name_a, LocalDate initialdate) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM SCHEDULE WHERE name_s=? AND name_a=? AND initialdate=?",
                    new ScheduleRowMapper(),
                    name_s, name_a, initialdate);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }


    public Schedule getSchedule(Schedule schedule) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM SCHEDULE WHERE name_s=? AND name_a=? AND initialdate=?",
                    new ScheduleRowMapper(),
                    schedule.getName_s(), schedule.getName_a(),schedule.getInitialdate());
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }


    /* Obté tots els timeSlot torna una llista buida si no n'hi ha cap. */
    public List<Schedule> getSchedules() {
        try {
            return jdbcTemplate.query("SELECT * FROM SCHEDULE",
                    new ScheduleRowMapper());
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<Schedule>();
        }
    }
}
