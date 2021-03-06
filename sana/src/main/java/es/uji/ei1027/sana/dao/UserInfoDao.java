package es.uji.ei1027.sana.dao;

import es.uji.ei1027.sana.model.UserInfo;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class UserInfoDao {


    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public UserInfo getByUsername(String username) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM userinfo WHERE username=?",
                    new UserInfoRowMapper(),
                    username);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    public UserInfo loadUserByUsername(String username, String password) {
        UserInfo user= getByUsername(username);
        if(user==null){
            //user doesn't
            return null;
        }
        
        BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
        if (passwordEncryptor.checkPassword(password,user.getPassword())) {
            //se borra el comapo de password para mas seguridad
            user.setPassword(null);
            return user;
        }
        else {
            return null; // bad login!
        }
    }

}
