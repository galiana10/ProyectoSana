package es.uji.ei1027.sana;
import java.util.logging.Logger;

import es.uji.ei1027.sana.dao.CitizenRowMapper;
import es.uji.ei1027.sana.model.Citizen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@SpringBootApplication
public class SanaApplication implements CommandLineRunner {

	private static final Logger log = Logger.getLogger(SanaApplication.class.getName());

	public static void main(String[] args) {
		// Auto-configura l'aplicació
		new SpringApplicationBuilder(SanaApplication.class).run(args);
	}

	// Funció principal
	public void run(String... strings) throws Exception {
		log.info("Ací va el meu codi");

		log.info("Selecciona el citizen pepe");
		Citizen c1 = jdbcTemplate.queryForObject(
				"SELECT * FROM Citizen WHERE NIE ='12321234L'",
				new CitizenRowMapper());
		log.info(c1.toString());


	}

	// Configura l'accés a la base de dades (DataSource)
	// a partir de les propietats a src/main/resources/applications.properties
	// que comencen pel prefix spring.datasource

	@Bean
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	// Plantilla per a executar operacions sobre la connexió
	private JdbcTemplate jdbcTemplate;

	// Crea el jdbcTemplate a partir del DataSource que hem configurat
	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}




}

