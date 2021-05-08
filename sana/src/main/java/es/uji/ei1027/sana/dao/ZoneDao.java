package es.uji.ei1027.sana.dao;

import es.uji.ei1027.sana.model.Zone;
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
        jdbcTemplate.update("INSERT INTO zone VALUES(?, ?, ?)",
                zone.getNumberLetter(),zone.getName_Area(),zone.getMaxCapacity()) ;
    }

    /*Esborra una zona*/
    public void deleteZone(Zone zone) {
        jdbcTemplate.update("DELETE FROM zone where numberLetter = ? AND name_Area= ?",
                zone.getNumberLetter(),zone.getName_Area()) ;
    }

    /*Esborra un zona por atributos*/
    public void deleteZone(String zoneNumberLetter,String nameArea) {
        jdbcTemplate.update("DELETE FROM zone where numberLetter = ? AND name_Area= ?",
                zoneNumberLetter,nameArea) ;
    }


    /* Obté la zona. Torna null si no existeix. */
    public Zone getZone(String zoneNumberLetter, String nameArea) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM zone where numberLetter = ? AND name_Area= ?",
                    new ZoneRowMapper(),zoneNumberLetter,nameArea);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }


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
