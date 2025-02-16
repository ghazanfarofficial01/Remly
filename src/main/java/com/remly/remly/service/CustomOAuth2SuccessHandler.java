package com.gm.remly.service;

import com.gm.remly.model.User;
import com.gm.remly.repository.UserRepository;
import com.gm.remly.utils.JwtUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Duration;
import java.util.Optional;

@Service
public class CustomOAuth2SuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private JwtUtil jwtUtil;

    private final UserRepository userRepository;

    public CustomOAuth2SuccessHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {


        OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();

        // Extract user details from OAuth2User
        String email = oauth2User.getAttribute("email");
        String name = oauth2User.getAttribute("name");
        String profilePicture = oauth2User.getAttribute("picture");

        // Check if user already exists
        Optional<User> existingUser = userRepository.findByEmail(email);
        User user;
        if (existingUser.isPresent()) {
            user = existingUser.get();
            boolean updated = false;

            if (!name.equals(user.getFullName())) {
                user.setFullName(name);
                updated = true;
            }
            if (!profilePicture.equals(user.getProfilePicture())) {
                user.setProfilePicture(profilePicture);
                updated = true;
            }


            if (updated) {
                userRepository.save(user);
            }
        }
        else {
            // Register new OAuth user
            user = new User();
            user.setEmail(email);
            user.setFullName(name);
            user.setProfilePicture(profilePicture);

            // OAuth users are verified
            userRepository.save(user);
        }

        String token = jwtUtil.generateToken(user.getEmail());

        // Set the JWT in an HTTP-only cookie
        Cookie cookie = new Cookie("jwt", token);
        cookie.setHttpOnly(true);
        //cookie.setSecure(true); // Enable in production (HTTPS only)
        cookie.setPath("/");
        response.addCookie(cookie);
        System.out.println("At Oauth Success Handler");
        response.sendRedirect("/home?token=" + token);
        //response.addHeader("Authorization", "Bearer " + token);


        System.out.println(oauth2User.getAttributes());
    }
}
