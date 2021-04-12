package es.uji.ei1027.sana.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ZoneDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    /* Afegeix el zone a la base de dades */
    public void addZone(Zone zone) {
        jdbcTemplate.update("INSERT INTO zone VALUES(?, ?)",
                zone.getNumberLetter(),zone.getName_Area()) ;
    }

    /*Esborra un servici*/
    public void deleteZone(Zone zone) {
        jdbcTemplate.update("DELETE FROM zone where numberLetter = ?",
                zone.getNumberLetter()) ;
    }

    /*Esborra un servici*/
    public void deleteZone(String zoneNumberLetter) {
        jdbcTemplate.update("DELETE FROM zone where name = ?",
                zoneNumberLetter) ;
    }


    /* Obté la classificacio amb el nom donat. Torna null si no existeix. */
    public Zone getZone(String zoneName) {
        try {
            return jdbcTemplate.queryForObject("SELECT * from zone WHERE numberLetter=?",
                    new ZoneRowMapper(),zoneName);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    /* Obté la classificacio amb el nom donat. Torna null si no existeix. */
    public List<Zone> getZones() {
        try {
            return jdbcTemplate.query("SELECT * from zone ",
                    new ZoneRowMapper());
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<Zone>();
        }
    }

}
