package org.example.finalproject.domains;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table( name = "holidays")
public class HolidayRequest {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    private String userEmail;

    private LocalDate startDate;
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        PENDING, APPROVED, REJECTED
    }

    public HolidayRequest() {
    }

    public HolidayRequest(String userEmail, LocalDate startDate, LocalDate endDate) {
        this.userEmail = userEmail;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = Status.PENDING;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
