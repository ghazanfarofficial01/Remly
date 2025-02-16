package com.remly.remly.utils;

import com.remly.remly.model.Reminder;
import org.springframework.stereotype.Service;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class ReminderValidatorService {

    public Map<String, String> validateReminder(Reminder request) {
        Map<String, String> errors = new HashMap<>();
          String title = request.getTitle();
          String message = request.getMessage();

        // Validate title
        if (title == null || title.trim().isEmpty()) {
            errors.put("title", "Title is required.");
        }

        // Validate message
        if (message == null || message.trim().isEmpty()) {
            errors.put("message", "Message is required.");
        }

        // Validate date and time
        if (request.getReminderDate() == null && request.getReminderTime() == null) {
            errors.put("dateTime", "Both date and time are required.");
        }
        else if(request.getIsDaily() && request.getReminderTime() != null){

            // Parse the provided time (Assuming format HH:mm)
            LocalTime providedTime = LocalTime.parse(request.getReminderTime().toString(), DateTimeFormatter.ISO_TIME);
            System.out.println(providedTime);
            // Get the current time in the specified timezone
            LocalTime currentTime = ZonedDateTime.now(ZoneId.of(getZoneId(request.getTimezone()))).toLocalTime();
            System.out.println(currentTime);

            if (!providedTime.isAfter(currentTime)) {
                errors.put("dateTime", "Reminder time must be in the future.");
            }
        }
        else {
            System.out.println(request.getIsDaily());
            System.out.println(request.getReminderTime());
            try {
                LocalDate reminderDate = LocalDate.parse(request.getReminderDate().toString(), DateTimeFormatter.ISO_DATE);
                LocalTime reminderTime = LocalTime.parse(request.getReminderTime().toString(), DateTimeFormatter.ISO_TIME);

                ZoneId zoneId = ZoneId.of(getZoneId(request.getTimezone()));
                ZonedDateTime reminderDateTime = ZonedDateTime.of(reminderDate, reminderTime, zoneId);
                ZonedDateTime currentDateTime = ZonedDateTime.now(zoneId);


                if (!reminderDateTime.isAfter(currentDateTime)) {
                    errors.put("dateTime", "Reminder date and time must be in the future.");
                }
            } catch (Exception e) {
                errors.put("dateTime", "Invalid date or time format.");
            }
        }
        return errors;
    }

    private String getZoneId(String timezone) {
        switch (timezone) {
            case "IST": return "Asia/Kolkata";
            case "UTC": return "UTC";
            case "EST": return "America/New_York";
            case "PST": return "America/Los_Angeles";
            default: return "UTC";
        }
    }
}
