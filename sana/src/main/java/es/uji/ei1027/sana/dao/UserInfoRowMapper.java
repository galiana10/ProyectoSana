package es.uji.ei1027.sana.dao;

import es.uji.ei1027.sana.model.UserInfo;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserInfoRowMapper implements RowMapper<UserInfo> {

    @Override
    public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {

        UserInfo ui= new UserInfo();
        ui.setNie(rs.getString("nie"));
        ui.setUsername(rs.getString("username"));
        ui.setPasswordEncripted(rs.getString("password"));
        ui.setType(rs.getInt("type"));


        return ui;
    }
}
