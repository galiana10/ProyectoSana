package es.uji.ei1027.sana.dao;

import es.uji.ei1027.sana.model.ReservationZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReservationZoneDao {
    private JdbcTemplate jdbcTemplate;

    // Obté el jdbcTemplate a partir del Data Source
    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /* Afegeix la reserva a la base de dades */
    public void addReservationZone(ReservationZone reservationZ) {
        jdbcTemplate.update("INSERT INTO RESERVATION_ZONE VALUES(?, ?, ?)",
                reservationZ.getQR(),reservationZ.getNumberLetter(),reservationZ.getName_Area());
    }



    /* Obté la reserva amb atributos. Torna null si no existeix. */
    public ReservationZone getReservationZone(String QRreservation,String numberLetter,String name_Area) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM RESERVATION_ZONE WHERE QR=? AND numberLetter_Z=? AND name_Area=?",
                    new ReservationZoneRowMapper(),
                    QRreservation,numberLetter,name_Area);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    /* Obté totes les reserves Torna una llista buida si no n'hi ha cap. */
    public List<ReservationZone> getReservationsZones() {
        try {
            return jdbcTemplate.query("SELECT * FROM RESERVATION_ZONE",
                    new ReservationZoneRowMapper());
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<ReservationZone>();
        }
    }
}
