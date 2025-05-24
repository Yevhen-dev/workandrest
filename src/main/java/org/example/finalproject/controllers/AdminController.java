package org.example.finalproject.controllers;

import org.example.finalproject.domains.User;
import org.example.finalproject.domains.WorkSession;
import org.example.finalproject.repositories.UserRepository;
import org.example.finalproject.repositories.WorkSessionRepository;
import org.example.finalproject.services.UserService;
import org.example.finalproject.services.WorkSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WorkSessionService workSessionService;

    @Autowired
    private WorkSessionRepository workSessionRepository;


    @GetMapping("/adminDashboard")
    public String adminDashboardPage(@AuthenticationPrincipal User user, Model model) {

        Optional<WorkSession> currentShift = workSessionService.findActiveShift(user);
        boolean isShiftActive = currentShift.isPresent();

        List<WorkSession> shifts = workSessionService.getUserSessions(user.getEmail());
        shifts.sort(Comparator.comparing(WorkSession::getStartTime).reversed());

        List<User> users = userRepository.findAll();


        model.addAttribute("user", user);
        model.addAttribute("users", users);

        model.addAttribute("activeShift", isShiftActive);
        model.addAttribute("shifts", shifts);
        return "/adminDashboard";
    }

    @GetMapping("/addUser" )
    public String addUserPage(@AuthenticationPrincipal User user, Model model) {
        return "/addUser";
    }

    @PostMapping("/addUser")
    public String addUser(@AuthenticationPrincipal User user,@RequestParam String name, @RequestParam String email, @RequestParam String password, Model model) {
        userService.registerUser( name, password, email, "ROLE_USER");
        return "redirect:/adminDashboard";
    }

    @GetMapping("/allShifts")
    public String allShiftsPage(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("allShifts", workSessionService.getAllUsersSessions());
        return "/allShifts";
    }

    @GetMapping( "/editShift" )
    public String editShiftPage(@AuthenticationPrincipal User user, @RequestParam Long id, Model model) {

        WorkSession shift = workSessionRepository.findById(id).orElseThrow();
        model.addAttribute("shift", shift);
        return "/editShift";

    }

    @PostMapping( "/updateShift" )
    public String updateShift(@AuthenticationPrincipal User user, @RequestParam Long id,
                              @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
                              @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {

        WorkSession shift = workSessionRepository.findById(id).orElseThrow();
        shift.setStartTime(startTime);
        shift.setEndTime(endTime);
        shift.setDuration(Duration.between(shift.getStartTime(), shift.getEndTime()));
        workSessionRepository.save(shift);

        return "redirect:/allShifts";
    }

}
