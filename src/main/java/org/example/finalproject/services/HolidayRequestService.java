package org.example.finalproject.services;

import org.example.finalproject.domains.HolidayRequest;
import org.example.finalproject.repositories.HolidayRequestRepository;
import org.example.finalproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class HolidayRequestService {

    @Autowired
    private HolidayRequestRepository holidayRequestRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService userService;

    public void makeHolidayRequest(String userEmail, LocalDate startDate, LocalDate endDate) {
        HolidayRequest holidayRequest = new HolidayRequest(userEmail, startDate, endDate);
        holidayRequestRepository.save(holidayRequest);
    }

    public List<HolidayRequest> getAllUserHolidayRequests(String userEmail) {
        return holidayRequestRepository.findByUserEmail(userEmail);
    }

    public void updateHolidayRequestStatus( Long id, HolidayRequest.Status newStatus ) {
        HolidayRequest holiday = holidayRequestRepository.findById(id).orElse(null);

        if( holiday != null ) {
            holiday.setStatus(newStatus);
            holidayRequestRepository.save(holiday);


            String title = "Holiday status";
            String message = "Hello " + holiday.getUserEmail()  + ",\n\nYour holiday request has been " + holiday.getStatus() + ".";
            emailService.sendEmail( userService.findUserByUsername( holiday.getUserEmail() ).get().getEmail(), title, message);

        }
    }

}
