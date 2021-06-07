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

    @RequestMapping("/list/{name_a}")
    public String listTimeSlots(Model model , @PathVariable String name_a) {
        model.addAttribute("timeslots", timeSlotDao.getTimeSlotsFromArea(name_a));
        model.addAttribute("area",name_a);
        return "timeslot/list";
    }

    @RequestMapping(value = "/add/{name_a}")
    public String addTimeslot(Model model, @PathVariable String name_a) {
        model.addAttribute("timeslot", new TimeSlot());
        model.addAttribute("area", name_a);
        return "timeslot/add";
    }

    @RequestMapping(value="/add/{name_a}", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("timeslot") TimeSlot timeslot
                                     , @PathVariable String name_a,
                                   Model model,
                                   BindingResult bindingResult) {

        TimeSlotValidator tv = new TimeSlotValidator();
        timeslot.setName_a(name_a);
        tv.validate(timeslot,bindingResult);

        if (bindingResult.hasErrors()){
            model.addAttribute("area", name_a);

            return "timeslot/add";
        }
        timeSlotDao.addTimeSlot(timeslot);
        return "redirect:/timeslot/list/"+name_a;
    }

    @RequestMapping(value="/update/{id_timeslot}", method = RequestMethod.GET)
    public String editTimeslot(Model model,  @PathVariable int id_timeslot) {

        model.addAttribute("timeslot", timeSlotDao.getTimeSlot(id_timeslot));
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

    @RequestMapping(value="/delete/{id_timeslot}")
    public String processDelete(@PathVariable String id_timeslot ){
        TimeSlot timeSlot = timeSlotDao.getTimeSlot(Integer.parseInt(id_timeslot));
        timeSlotDao.deleteTimeSlot(id_timeslot);
        return "redirect:../list/"+timeSlot.getName_a();
    }

}
