package es.uji.ei1027.sana.dao;

import es.uji.ei1027.sana.model.MunicipalManager;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Date;

public class MunicipalManagerRowMapper implements RowMapper<MunicipalManager> {

    @Override
    public MunicipalManager mapRow(ResultSet rs, int rowNum) throws SQLException {
        MunicipalManager mm = new MunicipalManager();
        mm.setName(rs.getString("name"));
        mm.setNIE(rs.getString("NIE"));
        mm.setInicialDate(rs.getObject("inicialDate", Date.class));
        mm.setFinalDate(rs.getObject("finalDate", Date.class));
        mm.setName_M(rs.getString("name_M"));
        return mm;
    }
}
