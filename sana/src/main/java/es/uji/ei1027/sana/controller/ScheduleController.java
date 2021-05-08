package es.uji.ei1027.sana.controller;

import es.uji.ei1027.sana.dao.ScheduleDao;
import es.uji.ei1027.sana.model.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDate;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {
    private ScheduleDao scheduleDao;

    @Autowired
    public void setScheduleDao(ScheduleDao scheduleDao){
        this.scheduleDao  = scheduleDao;
    }

    @RequestMapping("/list")
    public String listSchedules(Model model){
        model.addAttribute("schedules", scheduleDao.getSchedules());
        return "schedule/list";
    }

    @RequestMapping(value = "/add")
    public String addSchedule(Model model) {
        model.addAttribute("schedule", new Schedule());
        return "schedule/add";
    }


    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("schedule") Schedule schedule,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "schedule/add";
        scheduleDao.addSchedule(schedule);
        return "redirect:list";
    }

    @RequestMapping(value="/update/{name_s}/{name_a}/{initialDate}", method = RequestMethod.GET)
    public String editSchedule(Model model, @PathVariable String name_s, @PathVariable String name_a, @PathVariable LocalDate initialDate) {
        model.addAttribute("schedule", scheduleDao.getSchedule(name_s, name_s, initialDate));
        return "schedule/update";
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    public String processUpdateSubmit(
            @ModelAttribute("schedule") Schedule schedule,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "schedule/update";
        scheduleDao.updateTimeSlot(schedule);
        return "redirect:list";
    }

    @RequestMapping(value="/delete/{name_s}/{name_a}/{initialDate}")
    public String processDelete(@PathVariable String name_s,@PathVariable String name_a, @PathVariable LocalDate initialdate) {
        scheduleDao.deleteSchedule(name_s, name_a, initialdate);
        return "redirect:../list";
    }

}
