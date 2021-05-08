package es.uji.ei1027.sana.dao;

import es.uji.ei1027.sana.model.Citizen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;


@Repository
public class CitizenDao {

    private JdbcTemplate jdbcTemplate;

    // Obté el jdbcTemplate a partir del Data Source
    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /* Afegeix el citizen a la base de dades */
    public void addCitizen(Citizen citizen) {
        jdbcTemplate.update("INSERT INTO USERINFO VALUES(?, ?, ?, ?, ?)",
               citizen.getNIE(), citizen.getName(),citizen.getUsername(),citizen.getPassword(),0);
        jdbcTemplate.update("INSERT INTO CITIZEN VALUES(?, ?, ?, ?, ?)",
                citizen.getNIE(), citizen.getEmail(),citizen.getAddress(),citizen.getTown(),citizen.getCountry());

    }

    /* Esborra el citizen de la base de dades por Objeto*/
    public void deleteCitizen(Citizen citizen) {
        jdbcTemplate.update("DELETE FROM USERINFO WHERE NIE='"+citizen.getNIE()+"' ");
    }

    /* Esborra el citizen de la base de dades por NIE*/
    public void deleteCitizen(String NIEcitizen) {
        jdbcTemplate.update("DELETE FROM USERINFO WHERE NIE='"+NIEcitizen+"' ");
    }

    /* Actualitza els atributs del citizen*/
    public void updateCitizen(Citizen citizen) {
        jdbcTemplate.update("UPDATE CITIZEN SET email=?,address=?,town=?,country=? WHERE NIE=?",
                citizen.getEmail(),citizen.getAddress(),citizen.getTown(),citizen.getCountry(),citizen.getNIE());

        jdbcTemplate.update("UPDATE USERINFO SET name=? WHERE NIE=?",
                citizen.getName(),citizen.getNIE());
    }

    /* Obté el citizen amb el NIE Torna null si no existeix. */
    public Citizen getCitizen(String NIECitizen) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM CITIZEN JOIN USERINFO USING(NIE) WHERE NIE=?",
                    new CitizenRowMapper(),
                    NIECitizen);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    /* Obté tots els citizen Torna una llista buida si no n'hi ha cap. */
    public List<Citizen> getCitizens() {
        try {
            return jdbcTemplate.query("SELECT * FROM CITIZEN JOIN USERINFO USING(NIE)",
                    new CitizenRowMapper());
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<Citizen>();
        }
    }

}
