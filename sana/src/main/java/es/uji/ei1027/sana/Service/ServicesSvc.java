package es.uji.ei1027.sana.Service;


import es.uji.ei1027.sana.dao.ServiceAreaDao;
import es.uji.ei1027.sana.dao.ServiceDao;
import es.uji.ei1027.sana.model.ServiceArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.LinkedList;
import java.util.List;

@Service
public class ServicesSvc {



    @Autowired
    ServiceAreaDao serviceAreaDao;

    @Autowired
    ServiceDao serviceDao;



    public List<es.uji.ei1027.sana.model.Service> listaServicios(){

        List<es.uji.ei1027.sana.model.Service> servicios=serviceDao.getServices();




        return servicios;

    }
    

    public List<ServiceArea> listaFijos(String name_A){

        List<ServiceArea> serviceAreas=serviceAreaDao.getServicesAreas();

        List<ServiceArea> fijos= new LinkedList<>();

        for (ServiceArea sa: serviceAreas){

            if(sa.getName_A().equals(name_A) && sa.getServicetype().equals("fixed")){
                fijos.add(sa);
            }

        }

        return fijos;

    }

    public List<ServiceArea> listaEstacionales(String name_A){

        List<ServiceArea> serviceAreas=serviceAreaDao.getServicesAreas();

        List<ServiceArea> fijos= new LinkedList<>();

        for (ServiceArea sa: serviceAreas){

            if(sa.getName_A().equals(name_A) && sa.getServicetype().equals("seasonal")){
                fijos.add(sa);
            }

        }

        return fijos;

    }

    public List<ServiceArea> listaServicios(String name_A){

        List<ServiceArea> serviceAreas=serviceAreaDao.getServicesAreas();

        List<ServiceArea> servicios= new LinkedList<>();

        for (ServiceArea sa: serviceAreas){

            if(sa.getName_A().equals(name_A)){
                servicios.add(sa);
            }

        }

        return servicios;

    }

    public List<String> listaNombresServicios(String name_A){

        List<ServiceArea> serviceAreas=serviceAreaDao.getServicesAreas();

        List<String> servicios= new LinkedList<>();

        for (ServiceArea sa: serviceAreas){

            if(sa.getName_A().equals(name_A)){
                servicios.add(sa.getName_S());
            }

        }

        return servicios;

    }


}
