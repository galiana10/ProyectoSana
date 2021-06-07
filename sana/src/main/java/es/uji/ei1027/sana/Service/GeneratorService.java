package es.uji.ei1027.sana.Service;


import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.logging.SimpleFormatter;

@Service
public class GeneratorService {

    public String generateRandomString(){
        Timestamp timestamp = Timestamp.from(Instant.now());
        String randomString = new SimpleDateFormat("yyyyMMddHHmmss").format(timestamp);

        return randomString;
    }
}
