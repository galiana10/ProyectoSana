package es.uji.ei1027.sana.dao;

import es.uji.ei1027.sana.model.ServiceArea;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ServiceAreaRowMapper implements RowMapper<ServiceArea> {


    @Override
    public ServiceArea mapRow(ResultSet rs, int i) throws SQLException {
        ServiceArea serviceArea=new ServiceArea();
        serviceArea.setName_S(rs.getString("name_S"));
        serviceArea.setName_A(rs.getString("name_A"));
        serviceArea.setInitialdate(rs.getObject("initialDate", LocalDate.class));
        serviceArea.setFinaldate(rs.getObject("finalDate", LocalDate.class));
        serviceArea.setServicetype(rs.getString("serviceType"));
        serviceArea.setHorarios(rs.getString("horarios"));
        return serviceArea;
    }
}
