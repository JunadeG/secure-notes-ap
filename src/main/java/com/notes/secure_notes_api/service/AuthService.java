package com.notes.secure_notes_api.service;


import com.notes.secure_notes_api.model.User;
import com.notes.secure_notes_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

   /* public User registerUser(String username, String rawPassword){
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(rawPassword));
        return userRepository.save(newUser);

    }*/

    // refined to meet the controller needs...
    public User register(User user) {
    // 1. Get the plain-text password from the incoming user object.
         String rawPassword = user.getPassword();

    // 2. Encode (hash) the plain-text password.
        String encodedPassword = passwordEncoder.encode(rawPassword);

    // 3. Overwrite the plain-text password with the encoded one.
         user.setPassword(encodedPassword);

    // 4. Save the user object with the now-hashed password.
        return userRepository.save(user);
}
}
