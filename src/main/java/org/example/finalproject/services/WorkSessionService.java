package org.example.finalproject.services;


import org.example.finalproject.domains.User;
import org.example.finalproject.domains.WorkSession;
import org.example.finalproject.repositories.WorkSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WorkSessionService {

    @Autowired
    private WorkSessionRepository workSessionRepository;

    public void startWorkSession(String userEmail, User user) {
        WorkSession workSession = new WorkSession();
        workSession.setUserEmail(userEmail);
        workSession.setStartTime(LocalDateTime.now());
        workSession.setUser(user);
        workSessionRepository.save(workSession);
    }

    public void endWorkSession(String userEmail) {

        Optional<WorkSession> workSession = workSessionRepository.findFirstByUserEmailOrderByStartTimeDesc(userEmail);

        if (workSession.isPresent()) {
            workSession.get().setEndTime(LocalDateTime.now());

            Duration duration = Duration.between(workSession.get().getStartTime(), workSession.get().getEndTime());
            workSession.get().setDuration(duration);
            workSessionRepository.save(workSession.get());
        }

    }

    public List<WorkSession> getUserSessions(String userEmail) {
        return workSessionRepository.findAll().stream()
                .filter(session -> session.getUserEmail().equals(userEmail))
                .collect(Collectors.toList());
    }

    public List<WorkSession> getAllUsersSessions() {
        return workSessionRepository.findAll();
    }

    public Optional<WorkSession> findActiveShift(User user) {
        return workSessionRepository.findFirstByUserAndEndTimeIsNullOrderByStartTimeDesc(user);
    }

}
