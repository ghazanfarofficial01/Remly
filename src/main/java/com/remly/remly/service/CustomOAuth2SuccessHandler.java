package com.remly.remly.service;

import com.remly.remly.DAO.UserDAO;
import com.remly.remly.model.User;
import com.remly.remly.model.UserPrincipal;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class CustomOAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final UserDAO userDAO;

    public CustomOAuth2SuccessHandler(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();

        // Extract user details from OAuth2User
        String email = oauth2User.getAttribute("email");
        String name = oauth2User.getAttribute("name");
        String profilePicture = oauth2User.getAttribute("picture");

        // Check if user already exists
        User user = userDAO.findByEmail(email).orElseGet(() -> {
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setFullName(name);
            newUser.setProfilePicture(profilePicture);
            return userDAO.save(newUser);
        });

        // Update user details if needed
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
            userDAO.save(user);
        }


        System.out.println("At OAuth Success Handler");

        // Redirect to the original requested URL or homepage
//        response.sendRedirect(request.getSession().getAttribute("redirect_uri") != null ?
//                request.getSession().getAttribute("redirect_uri").toString() : "/");
        response.sendRedirect("/remly/setReminder");
    }
}
