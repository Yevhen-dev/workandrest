package org.example.finalproject.controllers;

import org.example.finalproject.domains.User;
import org.example.finalproject.domains.WorkSession;
import org.example.finalproject.services.WorkSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Objects;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private WorkSessionService workSessionService;

    @GetMapping("/userDashboard")
    public String userDashboardPage(@AuthenticationPrincipal User user, Model model) {

        Optional<WorkSession> currentShift = workSessionService.findActiveShift(user);
        boolean isShiftActive = currentShift.isPresent();

        model.addAttribute("user", user);
        model.addAttribute("activeShift", isShiftActive);
        model.addAttribute("shifts", workSessionService.getUserSessions(user.getEmail()));
        return "userDashboard";
    }

    @PostMapping("/userDashboard/start")
    public String startWork(@AuthenticationPrincipal User user) {
        workSessionService.startWorkSession(user.getEmail(), user);
        if(Objects.equals(user.getRole(), "ROLE_USER")) {
            return "redirect:/userDashboard";
        } else {
            return "redirect:/adminDashboard";
        }
    }

    @PostMapping("/userDashboard/end")
    public String endWork(@AuthenticationPrincipal User user) {
        workSessionService.endWorkSession(user.getEmail());
        if(Objects.equals(user.getRole(), "ROLE_USER")) {
            return "redirect:/userDashboard";
        } else {
            return "redirect:/adminDashboard";
        }
    }

}
