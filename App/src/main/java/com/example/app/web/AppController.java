package com.example.app.web;

import com.example.app.domain.ApplicationUser;
import com.example.app.infrastructure.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;


@Controller
public class AppController {

    @Autowired
    PasswordEncoder passwordEncoder;

    final ApplicationUserRepository applicationUserRepository;

    public AppController(ApplicationUserRepository codeFellowshipRepository) {
        this.applicationUserRepository = codeFellowshipRepository;
    }


    @GetMapping ("/")
    public String homePage () {
        return "home";
    }

    @GetMapping ("/login")
    public String loginPage () {

        return "login";
    }

    @GetMapping ("/signup")
    public String signupPage () {
        return "signup";
    }

    @PostMapping ("/signup")
    public RedirectView signupPost (@RequestParam String firstName, @RequestParam String lastName, @RequestParam String dataOfBirth, @RequestParam String username, @RequestParam String password , @RequestParam String bio) {
        String passwordEncoded = passwordEncoder.encode(password);
        ApplicationUser codeFellowship = new ApplicationUser(firstName, lastName, dataOfBirth, username, passwordEncoded, bio);
        applicationUserRepository.save(codeFellowship);
        return new RedirectView("/login");
    }
    

    @GetMapping ("/dashboard")
    public String logAuth (Principal p, Model model){
        model.addAttribute("username", p.getName());
        return "homeauth";
    }
}
