package es.uji.ei1027.sana.Service;


import es.uji.ei1027.sana.dao.ScheduleDao;
import es.uji.ei1027.sana.dao.ServiceDao;
import es.uji.ei1027.sana.model.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.LinkedList;
import java.util.List;

@Service
public class ServicesSvc {

    @Autowired
    ScheduleDao scheduleDao;

    @Autowired
    ServiceDao serviceDao;

    public List<es.uji.ei1027.sana.model.Service> listaServicios(String name_A){

        List<Schedule> schedules=scheduleDao.getSchedules();

        List<es.uji.ei1027.sana.model.Service> servicesArea= new LinkedList<es.uji.ei1027.sana.model.Service>();

        for (Schedule schedule: schedules){

            if(schedule.getName_a().equals(name_A)){
                servicesArea.add(serviceDao.getService(schedule.getName_s()));
            }

        }

        return servicesArea;

    }


    public List<Schedule> listaSchedule(String name_A){

        List<Schedule> schedules=scheduleDao.getSchedules();

        List<Schedule> servicesSchedule= new LinkedList<Schedule>();

        for (Schedule schedule: schedules){

            if(schedule.getName_a().equals(name_A)){
                servicesSchedule.add(schedule);
            }

        }

        return servicesSchedule;

    }


}
