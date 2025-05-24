package org.example.finalproject.repositories;

import org.example.finalproject.domains.User;
import org.example.finalproject.domains.WorkSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkSessionRepository extends JpaRepository<WorkSession, Long> {

    Optional<WorkSession> findFirstByUserEmailOrderByStartTimeDesc(String email);

    Optional<WorkSession> findFirstByUserAndEndTimeIsNullOrderByStartTimeDesc(User user);
}
