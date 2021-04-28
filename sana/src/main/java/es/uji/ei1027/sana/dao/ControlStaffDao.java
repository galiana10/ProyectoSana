package es.uji.ei1027.sana.dao;


import es.uji.ei1027.sana.model.ControlStaff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;


@Repository
public class ControlStaffDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    /* Afegeix el staff a la base de dades */
    public void addStaff(ControlStaff controlStaff) {
        jdbcTemplate.update("INSERT INTO controlstaff VALUES(?, ?, ?)",
                controlStaff.getNie(),controlStaff.getEmail(), controlStaff.getName());
    }

    /* Esborra el cs de la base de dades por Objeto*/
    public void deleteStaff(ControlStaff controlStaff) {
        jdbcTemplate.update("DELETE FROM controlstaff WHERE NIE='"+controlStaff.getNie()+"' ");
    }


    /* Esborra el cs de la base de dades por NIE*/
    public void deleteStaff(String NIE) {
        jdbcTemplate.update("DELETE FROM controlstaff WHERE NIE='"+NIE+"' ");
    }


    /* Actualitza els atributs del cs*/
    public void updateStaff(ControlStaff controlStaff) {
        jdbcTemplate.update("UPDATE controlstaff SET name=?,email=? WHERE nie=?",
                controlStaff.getName(),controlStaff.getEmail(),controlStaff.getNie());
    }

    /* Obté el cs amb el NIE Torna null si no existeix. */
    public ControlStaff getstaff(String NIE) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM controlstaff WHERE NIE=?",
                    new ControlStaffRowMapper(),
                    NIE);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }


    /* Obté tots els cs Torna una llista buida si no n'hi ha cap. */
    public List<ControlStaff> getStaff() {
        try {
            return jdbcTemplate.query("SELECT * FROM controlstaff",
                    new ControlStaffRowMapper());
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<ControlStaff>();
        }
    }



}
