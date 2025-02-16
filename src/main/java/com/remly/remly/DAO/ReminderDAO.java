package com.remly.remly.DAO;

import com.remly.remly.model.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReminderDAO extends JpaRepository<Reminder, Long> {
}
