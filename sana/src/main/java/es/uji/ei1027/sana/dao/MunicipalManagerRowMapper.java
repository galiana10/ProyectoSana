package es.uji.ei1027.sana.dao;

import es.uji.ei1027.sana.model.MunicipalManager;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MunicipalManagerRowMapper implements RowMapper<MunicipalManager> {

    @Override
    public MunicipalManager mapRow(ResultSet rs, int rowNum) throws SQLException {
        MunicipalManager mm = new MunicipalManager();
        mm.setNIE(rs.getString("NIE"));
        mm.setName(rs.getString("name"));
        mm.setInitialDate(rs.getDate("initialDate"));
        mm.setFinalDate(rs.getDate("finalDate"));
        mm.setName_M(rs.getString("name_M"));
        return mm;
    }
}
