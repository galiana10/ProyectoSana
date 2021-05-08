package es.uji.ei1027.sana;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.logging.Logger;

@SpringBootApplication
public class SanaApplication {

	private static final Logger log = Logger.getLogger(SanaApplication.class.getName());

	public static void main(String[] args) {
		// Auto-configura l'aplicació
		new SpringApplicationBuilder(SanaApplication.class).run(args);
	}
}

