package es.uji.ei1027.sana.Service;



import es.uji.ei1027.sana.dao.CitizenDao;
import es.uji.ei1027.sana.model.Citizen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class RegisterSvc {

    @Autowired
    CitizenDao citizenDao;

    public List<String> listaEmails(){

        List<Citizen> citizens=citizenDao.getCitizens();
        List<String> emails=new LinkedList<>();

        for(Citizen c: citizens){
            emails.add(c.getEmail());
        }

        return emails;

    }
}
