package es.uji.ei1027.sana.dao;

import es.uji.ei1027.sana.model.Municipality;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MunicipalityDao {
    private JdbcTemplate jdbcTemplate;

    // Obté el jdbcTemplate a partir del Data Source
    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /* Afegeix el municipality a la base de dades */
    public void addMunicipality(Municipality municipality) {
        jdbcTemplate.update("INSERT INTO MUNICIPALITY VALUES(?, ?, ?)",
                municipality.getName(),municipality.getAddress() ,municipality.getTlf());
    }

    /* Esborra el municipality de la base de dades por Objeto*/
    public void deleteMunicipality(Municipality municipality) {
        jdbcTemplate.update("DELETE FROM MUNICIPALITY WHERE name='"+municipality.getName()+"' ");
    }

    /* Esborra el municipality de la base de dades por Nombre*/
    public void deleteMunicipality(String municipalityName) {
        jdbcTemplate.update("DELETE FROM MUNICIPALITY WHERE name='"+municipalityName+"' ");
    }

    /* Actualitza els atributs del municipality*/
    public void updateMunicipality(Municipality municipality) {
        jdbcTemplate.update("UPDATE MUNICIPALITY SET tlf=?,address=? WHERE name=?",
                municipality.getTlf(),municipality.getAddress(),municipality.getName());
    }

    /* Obté el municipality amb el nombre Torna null si no existeix. */
    public Municipality getMunicipality(String nombreMunicipality) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM MUNICIPALITY WHERE name=?",
                    new MunicipalityRowMapper(),
                    nombreMunicipality);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    /* Obté tots els municipalities torna una llista buida si no n'hi ha cap. */
    public List<Municipality> getMunicipalities() {
        try {
            return jdbcTemplate.query("SELECT * FROM MUNICIPALITY",
                    new MunicipalityRowMapper());
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<Municipality>();
        }
    }


}
