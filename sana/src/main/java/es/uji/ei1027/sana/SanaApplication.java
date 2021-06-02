package es.uji.ei1027.sana;

import es.uji.ei1027.sana.Service.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.logging.Logger;

@SpringBootApplication
public class SanaApplication {

	@Autowired
	private SendEmailService sendEmailService;

	private static final Logger log = Logger.getLogger(SanaApplication.class.getName());

	public static void main(String[] args) {
		// Auto-configura l'aplicació
		new SpringApplicationBuilder(SanaApplication.class).run(args);
	}

//	@EventListener(ApplicationReadyEvent.class)
//	private void triggerWhenStarts(){
//		sendEmailService.sendEmail("marcosggarcia99@gmail.com", "TEST SANA", "Hi there!");
//	}

}

