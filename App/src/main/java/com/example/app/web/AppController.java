package com.example.app.web;

import com.example.app.domain.ApplicationUser;
import com.example.app.domain.Post;
import com.example.app.infrastructure.ApplicationUserRepository;
import com.example.app.infrastructure.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@Controller
public class AppController implements ErrorController {

    @Autowired
    PasswordEncoder passwordEncoder;

    final ApplicationUserRepository applicationUserRepository;
    final PostRepository postRepository;

    public AppController(ApplicationUserRepository codeFellowshipRepository, PostRepository postRepository) {
        this.applicationUserRepository = codeFellowshipRepository;
        this.postRepository = postRepository;
    }


    @RequestMapping("/error")
    public String errorPage (){
        return "error";
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
    public RedirectView signupPost (@RequestParam String firstName, @RequestParam String lastName, @RequestParam String dataOfBirth, @RequestParam String username, @RequestParam String password , @RequestParam String address, @RequestParam String email) {
        String passwordEncoded = passwordEncoder.encode(password);
        ApplicationUser codeFellowship = new ApplicationUser(firstName, lastName, dataOfBirth, username, passwordEncoded, address, email);
        applicationUserRepository.save(codeFellowship);
        return new RedirectView("/login");
    }


    @GetMapping ("/dashboard")
    public String logAuth (Principal p, Model model){
        model.addAttribute("username", p.getName());
        return "homeauth";
    }

    @GetMapping ("/profile")
    public String userProfile (Model model, Principal principal){
        ApplicationUser users = applicationUserRepository.findByUsername(principal.getName());
        System.out.println(principal.getName());
        model.addAttribute("user", users);
        return "profile";
    }

    @GetMapping ("/posts")
    public String posts (Model model) {
        List <Post> posts = new ArrayList<>(postRepository.findAll());
        model.addAttribute("posts", posts);
        return "posts";
    }

    @GetMapping ("/addpost")
    public String addPost () {
        return "addpost";
    }

    @PostMapping ("/addpost")
    public RedirectView addPosts (Principal p, @RequestParam String body){
        ApplicationUser user = applicationUserRepository.findByUsername(p.getName());
        Post post = new Post(body, user);
//        post.setUser(user);
        postRepository.save(post);
        return new RedirectView("/posts");
    }

    @GetMapping("/user/{id}")
    public String getSpecificUser(@PathVariable long id, Model model) {
        ApplicationUser user = applicationUserRepository.getById(id);
        model.addAttribute("user",user);
        return "specificuser";
    }
}
