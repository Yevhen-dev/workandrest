package org.example.finalproject.domains;

import jakarta.persistence.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "work_session")
public class WorkSession {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    private String userEmail;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private Duration duration;

    @ManyToOne( fetch = FetchType.EAGER )
    @JoinColumn( name = "user_id" )
    private User user;

    public WorkSession() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public String getFormattedStartTime() {
        return startTime != null ? startTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) : "Empty";
    }

    public String getFormattedEndTime() {
        return endTime != null ? endTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) : "Empty";
    }

    public String getFormattedDuration() {
        if (startTime == null || endTime == null) {
            return "In progress...";
        }
        long hours = duration.toHours();
        long minutes = duration.toMinutesPart();
        long seconds = duration.toSecondsPart();
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
