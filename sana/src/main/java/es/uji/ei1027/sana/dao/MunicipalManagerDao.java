package es.uji.ei1027.sana.dao;

import es.uji.ei1027.sana.model.MunicipalManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MunicipalManagerDao {

    private JdbcTemplate jdbcTemplate;

    // Obté el jdbcTemplate a partir del Data Source
    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /* Afegeix el mm a la base de dades */
    public void addMunicipalManager(MunicipalManager mm) {

        jdbcTemplate.update("INSERT INTO USERINFO VALUES(?, ?, ?, ?, ?)",
                mm.getNIE(), mm.getName(),mm.getUsername(),mm.getPassword(),2);


        jdbcTemplate.update("INSERT INTO MUNICIPALMANAGER VALUES(?, ?, ?, ?)",
                mm.getNIE(),mm.getInitialDate(),mm.getFinalDate(),mm.getName_M());
    }

    /* Esborra el mm de la base de dades por Objeto*/
    public void deleteMunicipalManager(MunicipalManager mm) {
        jdbcTemplate.update("DELETE FROM userinfo WHERE NIE='"+mm.getNIE()+"' ");
    }

    /* Esborra el mm de la base de dades por NIE*/
    public void deleteMunicipalManager(String NIEmm) {
        jdbcTemplate.update("DELETE FROM userinfo WHERE NIE='"+NIEmm+"' ");
    }

    /* Actualitza els atributs del mm */
    public void updateMunicipalManager(MunicipalManager mm) {

        jdbcTemplate.update("UPDATE userinfo SET name=?, WHERE nie=?",
                mm.getName(),mm.getUsername(),mm.getPassword(),mm.getNIE());

        jdbcTemplate.update("UPDATE MUNICIPALMANAGER SET name=?,initialDate=?,final_Date=?,name_M=? WHERE NIE=?",
                mm.getName(),mm.getInitialDate(),mm.getFinalDate(),mm.getName_M(),mm.getNIE());
    }

    /* Obté el m amb el NIE Torna null si no existeix. */
    public MunicipalManager getMunicipalManager(String NIEmm) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM MUNICIPALMANAGER JOIN USERINFO USING(NIE) WHERE NIE=?",
                    new MunicipalManagerRowMapper(),
                    NIEmm);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    /* Obté tots els mm Torna una llista buida si no n'hi ha cap. */
    public List<MunicipalManager> getMunicipalManager() {
        try {
            return jdbcTemplate.query("SELECT * FROM MUNICIPALMANAGER JOIN USERINFO USING(NIE)",
                    new MunicipalManagerRowMapper());
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<MunicipalManager>();
        }
    }

}
