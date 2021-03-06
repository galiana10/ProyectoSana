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
        jdbcTemplate.update("INSERT INTO TIMESLOT VALUES(DEFAULT,?, ?, ?,?)",
                timeSlot.getName_a(), timeSlot.getInitialhour(),timeSlot.getFinalhour(), timeSlot.getSeason());
    }

    /* Esborra el timeSlot de la base de dades por Objeto*/
    public void deleteTimeSlot(TimeSlot timeSlot) {
        jdbcTemplate.update("DELETE FROM TIMESLOT WHERE id_timeslot= ?",timeSlot.getId_timeslot());
    }

    /* Esborra el timeSlot de la base de dades por name_a AND inicialhour*/
    public void deleteTimeSlot(String id_timeslot) {
        jdbcTemplate.update("DELETE FROM TIMESLOT WHERE id_timeslot = ?",Integer.parseInt(id_timeslot));
    }

    public void deleteTimeSlot(int id_timeslot) {
        jdbcTemplate.update("DELETE FROM TIMESLOT WHERE id_timeslot = ?",id_timeslot);
    }



    /* Actualitza els atributs del timeSlot*/
    public void updateTimeSlot(TimeSlot timeSlot) {
        System.out.println(timeSlot);
        jdbcTemplate.update("UPDATE TIMESLOT SET finalHour=?,season=? WHERE name_a=? AND initialhour=?",
                timeSlot.getFinalhour(),timeSlot.getSeason(),timeSlot.getName_a(), timeSlot.getInitialhour());
    }

    /* Obté el timeSlot amb el name_a and inicialHour, Torna null si no existeix. */
    public TimeSlot getTimeSlot(int id) {
        try {
            System.out.println("get time slot funcion");
            return jdbcTemplate.queryForObject("SELECT * FROM TIMESLOT WHERE id_timeslot=?",
                    new TimeSlotRowMapper(),
                    id);
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

    /* Obté tots els timeSlot torna una llista buida si no n'hi ha cap. */
    public List<TimeSlot> getTimeSlotsFromArea(String area) {
        try {
            return jdbcTemplate.query("SELECT * FROM TIMESLOT where name_a=?",
                    new TimeSlotRowMapper(),area);
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<TimeSlot>();
        }
    }


}
