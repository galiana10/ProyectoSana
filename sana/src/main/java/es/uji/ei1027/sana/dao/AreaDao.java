package es.uji.ei1027.sana.dao;

import es.uji.ei1027.sana.model.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AreaDao {


    private JdbcTemplate jdbcTemplate;

    // Obté el jdbcTemplate a partir del Data Source
    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /* Afegeix el area a la base de dades */
    public void addArea(Area area) {
        jdbcTemplate.update("INSERT INTO AREA VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                area.getName(),area.getDescription(),area.getNumberOfZone(),area.getAccessType(),area.getGeographicalLocation(),area.getAreaType(),area.getPhysiscalCharacteristic(),area.getOrientation(),area.getFacility(),area.getComment(),area.getImage(),area.getName_M());
    }

    /* Esborra el area de la base de dades por Objeto*/
    public void deleteArea(Area area) {
        jdbcTemplate.update("DELETE FROM AREA WHERE name='"+area.getName()+"' ");
    }

    /* Esborra el area de la base de dades por Name*/
    public void deleteArea(String NameArea) {
        jdbcTemplate.update("DELETE FROM AREA WHERE name='"+NameArea+"' ");
    }

    /* Actualitza els atributs del area*/
    public void updateArea(Area area) {
        jdbcTemplate.update("UPDATE AREA SET description=?,numberOfZone=?,accessType=?,geographicalLocation=?,areaType=?,physiscalCharacteristic=?,orientation=?,facility=?,comment=?,image=?,name_M=? WHERE name=?",
                area.getDescription(),area.getNumberOfZone(),area.getAccessType(),area.getGeographicalLocation(),area.getAreaType(),area.getPhysiscalCharacteristic(),area.getOrientation(),area.getFacility(),area.getComment(),area.getImage(),area.getName_M(),area.getName());
    }

    /* Obté el area amb el name Torna null si no existeix. */
    public Area getArea(String NameArea) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM  AREA WHERE name=?",
                    new AreaRowMapper(),
                    NameArea);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    /* Obté tots els areas.Torna una llista buida si no n'hi ha cap. */
    public List<Area> getAreas() {
        try {
            return jdbcTemplate.query("SELECT * FROM AREA",
                    new AreaRowMapper());
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<Area>();
        }
    }

}
