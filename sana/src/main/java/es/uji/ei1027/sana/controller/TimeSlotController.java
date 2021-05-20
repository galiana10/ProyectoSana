package es.uji.ei1027.sana.controller;

import es.uji.ei1027.sana.dao.TimeSlotDao;
import es.uji.ei1027.sana.model.TimeSlot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalTime;

@Controller
@RequestMapping("/timeslot")
public class TimeSlotController {
    private TimeSlotDao timeSlotDao;

    @Autowired
    public void setTimeSlotDao(TimeSlotDao timeSlotDao) {
        this.timeSlotDao = timeSlotDao;
    }

    @RequestMapping("/list")
    public String listTimeSlots(Model model) {
        model.addAttribute("timeslots", timeSlotDao.getTimeSlots());
        return "timeslot/list";
    }

    @RequestMapping(value = "/add")
    public String addTimeslot(Model model) {
        model.addAttribute("timeslot", new TimeSlot());
        return "timeslot/add";
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("timeslot") TimeSlot timeslot,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "timeslot/add";
        timeSlotDao.addTimeSlot(timeslot);
        return "redirect:list";
    }

    @RequestMapping(value="/update/{name_a}/{inicialhour}", method = RequestMethod.GET)
    public String editTimeslot(Model model, @PathVariable String name_A, @PathVariable LocalTime initialHour) {
        //TODO
       // model.addAttribute("timeslot", timeSlotDao.getTimeSlot(name_A,initialHour));
        return "timeslot/update";
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    public String processUpdateSubmit(
            @ModelAttribute("timeslot") TimeSlot timeSlot,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "timeslot/update";
        timeSlotDao.updateTimeSlot(timeSlot);
        return "redirect:list";
    }

    @RequestMapping(value="/delete/{name_A}/{inicialHour}")
    public String processDelete(@PathVariable String name_A, @PathVariable LocalTime initialHour) {
        timeSlotDao.deleteTimeSlot(name_A, initialHour);
        return "redirect:../list";
    }

}
