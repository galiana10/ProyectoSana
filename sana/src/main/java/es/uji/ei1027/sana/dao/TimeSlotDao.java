package es.uji.ei1027.sana.dao;

import es.uji.ei1027.sana.model.TimeSlot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TimeSlotDao {
    private JdbcTemplate jdbcTemplate;

    // Obté el jdbcTemplate a partir del Data Source
    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /* Afegeix el timeSlot a la base de dades */
    public void addTimeSlot(TimeSlot timeSlot) {
        jdbcTemplate.update("INSERT INTO TIMESLOT VALUES(?, ?, ?,?)",
                timeSlot.getName_a(), timeSlot.getInicialhour(),timeSlot.getFinalhour(), timeSlot.getSeason());
    }

    /* Esborra el timeSlot de la base de dades por Objeto*/
    public void deleteTimeSlot(TimeSlot timeSlot) {
        jdbcTemplate.update("DELETE FROM TIMESLOT WHERE name_a='" + timeSlot.getName_a() + "' AND inicialhour'" + timeSlot.getInicialhour() + "'  ");
    }

    /* Esborra el timeSlot de la base de dades por name_a AND inicialhour*/
    public void deleteTimeSlot(String name_a, LocalTime inicialhour) {
        jdbcTemplate.update("DELETE FROM TIMESLOT WHERE name_a='"+name_a+"' AND name_a='"+inicialhour.toString()+"' ");
    }

    /* Actualitza els atributs del timeSlot*/
    public void updateTimeSlot(TimeSlot timeSlot) {
        jdbcTemplate.update("UPDATE TIMESLOT SET finalHour=?,season=? WHERE name_a=? AND inicialhour=?",
                timeSlot.getFinalhour(),timeSlot.getSeason(),timeSlot.getName_a(), timeSlot.getInicialhour());
    }


    /* Obté el timeSlot amb el name_a and inicialHour, Torna null si no existeix. */
    public TimeSlot getTimeSlot(String name_a, LocalTime inicialhour) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM MUNICIPALITY WHERE name_a=? AND inicialhour=?",
                    new TimeSlotRowMapper(),
                    name_a, inicialhour);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    /* Obté tots els timeSlot torna una llista buida si no n'hi ha cap. */
    public List<TimeSlot> getTimeSlots() {
        try {
            return jdbcTemplate.query("SELECT * FROM TIMESLOT",
                    new TimeSlotRowMapper());
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<TimeSlot>();
        }
    }


}
