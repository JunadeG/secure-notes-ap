package com.notes.secure_notes_api.repository;

import com.notes.secure_notes_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username); // changed 'List' to Optional
    // Optional basically checks if user is valid or exists and handles it automatically
}
