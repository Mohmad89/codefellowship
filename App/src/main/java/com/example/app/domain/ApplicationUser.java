package com.example.app.domain;


import lombok.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;


@Entity
public class ApplicationUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @NonNull
    private String dataOfBirth;


    private String username;

    @NonNull
    private String password;

    @Column(columnDefinition = "TEXT")
    private String bio;

    public ApplicationUser() {

    }

    public ApplicationUser(@NonNull String firstName, @NonNull String lastName, @NonNull String dataOfBirth, @NonNull String username, @NonNull String password, String bio) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dataOfBirth = dataOfBirth;
        this.username = username;
        this.password = password;
        this.bio = bio;
    }


    public Long getId() {
        return id;
    }

    @NonNull
    public String getFirstName() {
        return firstName;
    }

    @NonNull
    public String getLastName() {
        return lastName;
    }

    @NonNull
    public String getDataOfBirth() {
        return dataOfBirth;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    @NonNull
    public String getPassword() {
        return password;
    }

    public String getBio() {
        return bio;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
