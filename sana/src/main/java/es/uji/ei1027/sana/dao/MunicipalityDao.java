package es.uji.ei1027.sana.dao;

import es.uji.ei1027.sana.model.Citizen;
import es.uji.ei1027.sana.model.Municipality;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

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
                municipality.getName(), municipality.getTlf(),municipality.getAddress());
    }


}
