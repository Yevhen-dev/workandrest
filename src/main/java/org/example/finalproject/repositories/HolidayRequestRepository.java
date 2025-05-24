package org.example.finalproject.repositories;

import org.example.finalproject.domains.HolidayRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HolidayRequestRepository extends JpaRepository<HolidayRequest, Long> {

    List<HolidayRequest> findByUserEmail(String UserEmail);
    List<HolidayRequest> findByStatus(HolidayRequest.Status status);

}
