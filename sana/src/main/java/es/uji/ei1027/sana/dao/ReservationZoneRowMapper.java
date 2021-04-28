package es.uji.ei1027.sana.dao;

import es.uji.ei1027.sana.model.ReservationZone;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ReservationZoneRowMapper implements RowMapper<ReservationZone> {

    public ReservationZone mapRow(ResultSet rs, int rowNum) throws SQLException {
        ReservationZone reservationZone=new ReservationZone();

        reservationZone.setQR(rs.getString("QR_R"));
        reservationZone.setNumberLetter(rs.getString("numberLetter_Z"));
        reservationZone.setName_Area(rs.getString("name_Area"));
        return reservationZone;
    }
}
