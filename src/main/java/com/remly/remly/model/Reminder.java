package com.remly.remly.model;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

@Entity
@Table(name = "reminders",
        indexes = {
                @Index(name = "idx_reminder_time_sent", columnList = "reminder_date, reminder_time, is_sent"),
                @Index(name = "idx_user_id", columnList = "user_id")
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reminder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "message", nullable = false, columnDefinition = "TEXT")
    private String message;

    @Column(name = "reminder_date", nullable = false)
    private LocalDate reminderDate = LocalDate.now();

    @Column(name = "reminder_time", nullable = false)
    private LocalTime reminderTime;

    @Column(name = "is_daily", nullable = false)
    private Boolean isDaily = false;

    @Column(name = "is_sent", nullable = false)
    private Boolean isSent = false;

    @Column(name = "timezone", nullable = false, length = 50)
    private String timezone;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Reminder{" +
                "id=" + id +
                ", user=" + user +
                ", title='" + title + '\'' +
                ", message='" + message + '\'' +
                ", reminderDate=" + reminderDate +
                ", reminderTime=" + reminderTime +
                ", isDaily=" + isDaily +
                ", isSent=" + isSent +
                ", timezone='" + timezone + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
