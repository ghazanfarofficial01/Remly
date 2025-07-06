package com.remly.remly.controller;

import com.remly.remly.DAO.ReminderDAO;
import com.remly.remly.DAO.UserDAO;
import com.remly.remly.model.Reminder;
import com.remly.remly.model.User;
//import com.remly.remly.model.UserPrincipal;
import com.remly.remly.utils.ReminderValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/remly")
public class ReminderController {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private ReminderDAO reminderDAO;

    @Autowired
    private ReminderValidatorService validatorService;
    @GetMapping("setReminder")
    public String getReminderForm() {
        return "reminderForm";
    }

    @PostMapping("setReminder")
    public String setReminder(@RequestBody MultiValueMap reminder, @AuthenticationPrincipal OAuth2User principal, RedirectAttributes redirectAttributes, Model model) {

        Reminder reminderObj = new Reminder();
        //System.out.println(reminder);
        if(reminder.containsKey("reminderDate"))
            reminderObj.setReminderDate(LocalDate.parse(((List<String>) reminder.get("reminderDate")).get(0)));
        if(reminder.containsKey("reminderTime"))
            reminderObj.setReminderTime(LocalTime.parse(((List<String>) reminder.get("reminderTime")).get(0)));
        if(reminder.containsKey("title"))
            reminderObj.setTitle(((List<String>) reminder.get("title")).get(0));
        if(reminder.containsKey("message"))
            reminderObj.setMessage(((List<String>) reminder.get("message")).get(0));
        if(reminder.containsKey("timezone"))
            reminderObj.setTimezone(((List<String>) reminder.get("timezone")).get(0));
        if(reminder.containsKey("isDaily")) {
            String flag = ((List<String>) reminder.get("isDaily")).get(0);
            if(flag.equalsIgnoreCase("ON"))
                    reminderObj.setIsDaily(true);
        }
        //System.out.println(reminderObj.toString());


        Map<String, String> errors = validatorService.validateReminder(reminderObj);

        if (!errors.isEmpty()) {
            System.out.println(errors);
            model.addAttribute("errors", errors);
            return "errors/error";
            //return errors.get("message");
        }

        try{
            String username = principal.getAttribute("email");
            //System.out.println("User: " + username);
            Optional<User> user = userDAO.findByEmail(username);
            //System.out.println("User " + user);
            reminderObj.setUser(user.get());
            reminderDAO.save(reminderObj);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(reminderObj.toString());
        redirectAttributes.addFlashAttribute("successMessage", "Reminder set successfully!");
        return "redirect:/remly/reminders";
    }

    @GetMapping("reminders")
    public String getReminders() {
        return "reminderForm";
    }
}
