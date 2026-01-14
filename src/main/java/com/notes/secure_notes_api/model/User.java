package com.notes.secure_notes_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity // tells the database how to store user information
@Table(name ="app_users")

public class User implements UserDetails {  // this class is the blueprint for each user that is using this app.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // we use @Column(unique true) to ensure no two users have the same username
    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = false)
    private String password; // this will store the hashed password

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
        //this method is for user roles like Admin and standard Users.
    }

    @Override
    public String getPassword(){
        return this.password;
        // we override getPassword() for the UserDetails contract...
    }

    @Override
    public String getUsername(){
        return this.username;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
        // this method is used to check account status
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
