package es.uji.ei1027.sana.dao;

import es.uji.ei1027.sana.model.Service;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceRowMapper implements RowMapper<Service> {


    @Override
    public Service mapRow(ResultSet rs, int rowNum) throws SQLException {

        Service service= new Service();
        service.setName(rs.getString("name"));
        service.setDescription(rs.getString("description"));
        service.setServicetype(rs.getString("servicetype"));
        return service;
    }
}
