package es.uji.ei1027.sana.dao;



import es.uji.ei1027.sana.model.ServiceArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ServiceAreaDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    /* Afegeix el serviceArea a la base de dades */
    public void addServiceArea(ServiceArea serviceArea) {
        jdbcTemplate.update("INSERT INTO SERVICEAREA VALUES(?, ?, ?, ?, ?, ?)",
                serviceArea.getName_S(), serviceArea.getName_A(),serviceArea.getInitialdate(),
                serviceArea.getFinaldate(),serviceArea.getServicetype(),serviceArea.getHorarios());
    }

    /* Esborra el serviceArea de la base de dades por Objeto*/
    public void deleteServiceArea(ServiceArea serviceArea) {
        jdbcTemplate.update("DELETE FROM SERVICEAREA WHERE name_S='" + serviceArea.getName_S() + "' AND name_A='" + serviceArea.getName_A());
    }

    /* Esborra el serviceArea de la base de dades por name_s, name_a*/
    public void deleteServiceArea(String name_s, String name_a) {
        jdbcTemplate.update("DELETE FROM SERVICEAREA WHERE name_S=?  AND name_A=?",
                                    name_s, name_a);
    }

    /* Esborra el serviceArea de la base de dades por name_s, name_a*/
    public void deleteServiceArea(String name_s, String name_a,LocalDate inicial_date) {
        jdbcTemplate.update("DELETE FROM SERVICEAREA WHERE name_S=?  AND name_A=? AND initialdate=?",
                name_s, name_a,inicial_date);
    }


    /* Obté el serviceArea amb el name_s, name_a. Torna null si no existeix. */
    public ServiceArea getServiceArea(String name_s, String name_a) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM SERVICEAREA WHERE name_s=? AND name_a=?",
                    new ServiceAreaRowMapper(),
                    name_s, name_a);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }


    public ServiceArea getServiceArea(ServiceArea serviceArea) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM SERVICEAREA WHERE name_s=? AND name_a=?",
                    new ServiceAreaRowMapper(),
                    serviceArea.getName_S(), serviceArea.getName_A());
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }


    /* Obté tots els timeSlot torna una llista buida si no n'hi ha cap. */
    public List<ServiceArea> getServicesAreas() {
        try {
            return jdbcTemplate.query("SELECT * FROM SERVICEAREA",
                    new ServiceAreaRowMapper());
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<ServiceArea>();
        }
    }


}
