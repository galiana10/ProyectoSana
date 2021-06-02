package es.uji.ei1027.sana.Service;


import es.uji.ei1027.sana.dao.MunicipalManagerDao;
import es.uji.ei1027.sana.model.MunicipalManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MMSvc {

    @Autowired
    MunicipalManagerDao municipalManagerDao;

    public String municipalityFromMM(String NIE_MM){
        MunicipalManager municipalManager=municipalManagerDao.getMunicipalManager(NIE_MM);
        return municipalManager.getName_M();
    }

}
