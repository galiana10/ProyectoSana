package es.uji.ei1027.sana.dao;

import es.uji.ei1027.sana.model.Reservation_Zone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReservationZoneDAO {
    private JdbcTemplate jdbcTemplate;

    // Obté el jdbcTemplate a partir del Data Source
    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /* Afegeix la reserva a la base de dades */
    public void addReservationZone(Reservation_Zone reservationZ) {
        jdbcTemplate.update("INSERT INTO RESERVATION_ZONE VALUES(?, ?)",
                reservationZ.getQR(),reservationZ.getNumberLetter());
    }

    /* Esborra la reserva de la base de dades por Objeto*/
    public void deleteReservationZone(Reservation_Zone reservationZ) {
        jdbcTemplate.update("DELETE FROM RESERVATION_ZONE WHERE QR='"+reservationZ.getQR()+"' ");
    }

    /* Esborra al reserva de la base de dades por QR*/
    public void deleteReservation(String QRreservation) {
        jdbcTemplate.update("DELETE FROM RESERVATION WHERE QR='"+QRreservation+"' ");
    }



    /* Obté la reserva amb el QR Torna null si no existeix. */
    public Reservation getReservation(String QRreservation) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM RESERVATION WHERE QR=?",
                    new ReservationRowMapper(),
                    QRreservation);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    /* Obté totes les reserves Torna una llista buida si no n'hi ha cap. */
    public List<Reservation> getReservations() {
        try {
            return jdbcTemplate.query("SELECT * FROM RESERVATION",
                    new ReservationRowMapper());
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<Reservation>();
        }
    }
}
