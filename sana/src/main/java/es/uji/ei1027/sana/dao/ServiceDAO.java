package es.uji.ei1027.sana.dao;



import es.uji.ei1027.sana.model.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ServiceDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    /* Afegeix el service a la base de dades */
    public void addService(Service service) {
        jdbcTemplate.update("INSERT INTO service VALUES(?, ?, ?)",
              service.getName(),service.getDescription(),service.getServicetype()) ;
    }

    /*Esborra un servici*/
    public void deleteService(Service service) {
        jdbcTemplate.update("DELETE FROM service where name = ?",
                service.getName()) ;
    }

    /*Esborra un servici*/
    public void deleteService(String serviceName) {
        jdbcTemplate.update("DELETE FROM service where name = ?",
                serviceName) ;
    }

    /*Actualiza un service*/
    public void updateService(Service service){
        jdbcTemplate.update("UPDATE service set description=?,servicetype=? where name=?",
                service.getDescription(),service.getServicetype(),service.getName());
    }


    /* Obté la classificacio amb el nom donat. Torna null si no existeix. */
    public Service getService(String serviceName) {
        try {
            return jdbcTemplate.queryForObject("SELECT * from service WHERE name=?",
                    new ServiceRowMapper(),serviceName);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    /* Obté la classificacio amb el nom donat. Torna null si no existeix. */
    public List<Service> getServices() {
        try {
            return jdbcTemplate.query("SELECT * from service ",
                    new ServiceRowMapper());
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<Service>();
        }
    }




}
