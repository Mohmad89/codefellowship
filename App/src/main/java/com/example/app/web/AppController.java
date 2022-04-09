package com.example.app.web;

import com.example.app.domain.CodeFellowship;
import com.example.app.infrastructure.CodeFellowshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;


@Controller
public class AppController {

    @Autowired
    PasswordEncoder passwordEncoder;

    final CodeFellowshipRepository codeFellowshipRepository;

    public AppController(CodeFellowshipRepository codeFellowshipRepository) {
        this.codeFellowshipRepository = codeFellowshipRepository;
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
        CodeFellowship codeFellowship = new CodeFellowship(firstName, lastName, dataOfBirth, username, passwordEncoded, bio);
        codeFellowshipRepository.save(codeFellowship);
        return new RedirectView("/login");
    }

//    @GetMapping ("/main")
//    public String loginAuth (HttpServletRequest request, Model model) {
//
//    }

    @GetMapping ("/main")
    public String logAuth (){
        return "homeauth";
    }
}
