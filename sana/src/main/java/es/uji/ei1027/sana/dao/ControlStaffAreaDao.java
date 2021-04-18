package es.uji.ei1027.sana.dao;

import es.uji.ei1027.sana.model.ControlStaffArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;


@Repository
public class ControlStaffAreaDao {

    private JdbcTemplate jdbcTemplate;


    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    /*Añade control staff area*/

    public void anadeStaffArea(ControlStaffArea controlStaff_area) {
        jdbcTemplate.update("INSERT INTO controlstaff_area VALUES(?,?)",
                controlStaff_area.getNie_cs(), controlStaff_area.getName_a());
    }

    /*borra con objeto*/
    public void deteleStaffArea(ControlStaffArea controlStaff_area) {
        jdbcTemplate.update("DELETE FROM controlstaff_area WHERE nie_cs='" + controlStaff_area.getNie_cs() + "' AND name_a='" + controlStaff_area.getName_a() + "\'");

    }

    /*borra con nie*/
    public void deteleStaffArea(String controlStaff_areaNie, String controlStaff_areaName) {
        jdbcTemplate.update("DELETE FROM controlstaff_area WHERE nie_cs='" + controlStaff_areaNie + "' AND name_a='" + controlStaff_areaName + "'");
    }

    /*obten relacion con nie y name*/
    public ControlStaff_Area getstaff(String NIE, String name) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM controlstaff_area WHERE nie_cs=? AND name_a='" + name + "'",
                    new ControlStaffAreaRowMapper(),
                    NIE);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    /*obten totes les relacions*/
    public List<ControlStaff_Area> getStaff() {
        try {
            return jdbcTemplate.query("SELECT * FROM controlstaff_area", new ControlStaff_AreaRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<ControlStaff_Area>();
        }

    }
}
