package org.example.finalproject.controllers;


import org.example.finalproject.domains.HolidayRequest;
import org.example.finalproject.domains.User;
import org.example.finalproject.repositories.HolidayRequestRepository;
import org.example.finalproject.services.HolidayRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class HolidayController {

    @Autowired
    private HolidayRequestService holidayRequestService;

    @Autowired
    private HolidayRequestRepository holidayRequestRepository;

    @GetMapping("/holiday")
    public String holidayPage(@AuthenticationPrincipal User user, Model model) {

        model.addAttribute("user", user);
        model.addAttribute("holidays", holidayRequestService.getAllUserHolidayRequests(user.getUsername()));

        return "holiday";
    }

    @PostMapping( "/holiday/request" )
    public String holidayRequest(@AuthenticationPrincipal User user, @RequestParam String startDate, @RequestParam String endDate) {

        holidayRequestService.makeHolidayRequest(user.getUsername(), LocalDate.parse(startDate), LocalDate.parse(endDate));
        return "redirect:/holiday";

    }

    @GetMapping( "/allHolidays" )
    public String allHolidaysPage(Model model) {
        List<HolidayRequest> holidayRequestList = holidayRequestRepository.findAll();
        model.addAttribute("holidays", holidayRequestList);
        return "/allHolidays";
    }

    @PostMapping( "/allHolidays/updateStatus" )
    public String updateStatus(@AuthenticationPrincipal User user, @RequestParam Long id, @RequestParam String status, Model model) {
        holidayRequestService.updateHolidayRequestStatus( id, HolidayRequest.Status.valueOf(status) );
        return "redirect:/allHolidays";
    }
}
