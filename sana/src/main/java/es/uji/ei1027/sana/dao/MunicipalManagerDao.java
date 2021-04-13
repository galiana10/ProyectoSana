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

    /* Afegeix el citizen a la base de dades */
    public void addMunicipalManager(MunicipalManager mm) {
        jdbcTemplate.update("INSERT INTO MUNICIPALMANAGER VALUES(?, ?, ?, ?, ?)",
                mm.getNIE(), mm.getName(),mm.getInicialDate(),mm.getFinalDate(),mm.getName_M());
    }

    /* Esborra el citizen de la base de dades por Objeto*/
    public void deleteMunicipalManager(MunicipalManager mm) {
        jdbcTemplate.update("DELETE FROM MUNICIPALMANAGER WHERE NIE='"+mm.getNIE()+"' ");
    }

    /* Esborra el citizen de la base de dades por NIE*/
    public void deleteMunicipalManager(String NIEmm) {
        jdbcTemplate.update("DELETE FROM MUNICIPALMANAGER WHERE NIE='"+NIEmm+"' ");
    }

    /* Actualitza els atributs del citizen*/
    public void updateMunicipalManager(MunicipalManager mm) {
        jdbcTemplate.update("UPDATE MUNICIPALMANAGER SET name=?,inicialDate=?,final_Date=?,name_M=? WHERE NIE=?",
                mm.getName(),mm.getInicialDate(),mm.getFinalDate(),mm.getName_M(),mm.getNIE());
    }

    /* Obté el citizen amb el NIE Torna null si no existeix. */
    public MunicipalManager getMunicipalManager(String NIEmm) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM MUNICIPALMANAGER WHERE NIE=?",
                    new MunicipalManagerRowMapper(),
                    NIEmm);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    /* Obté tots els citizen Torna una llista buida si no n'hi ha cap. */
    public List<MunicipalManager> getMunicipalManager() {
        try {
            return jdbcTemplate.query("SELECT * FROM MUNICPALMANAGER",
                    new MunicipalManagerRowMapper());
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<MunicipalManager>();
        }
    }

}
