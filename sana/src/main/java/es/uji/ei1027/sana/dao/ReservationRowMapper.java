package es.uji.ei1027.sana.dao;

import es.uji.ei1027.sana.model.Reservation;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;


public final class ReservationRowMapper implements RowMapper<Reservation> {

    public Reservation mapRow(ResultSet rs, int rowNum) throws SQLException {
        Reservation reservation=new Reservation();

        reservation.setDate(rs.getObject("date", LocalDate.class));
        reservation.setPeopleNumber(rs.getInt("peopleNumber"));
        reservation.setQR(rs.getString("QR"));
        reservation.setNIE_citizen(rs.getString("NIE_citizen"));
        reservation.setStatus(rs.getString("status"));
        reservation.setId_timeslot(rs.getInt("id_timeslot"));



        return reservation;
    }
}