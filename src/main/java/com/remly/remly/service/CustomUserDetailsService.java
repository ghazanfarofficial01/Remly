//package com.remly.remly.service;
//
//
//import com.remly.remly.DAO.UserDAO;
//import com.remly.remly.model.User;
//import com.remly.remly.model.UserPrincipal;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//    @Autowired
//    UserDAO userDAO;
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        //System.out.println(email);
//        Optional<User> user = userDAO.findByEmail(email);
//        System.out.println("User found: " + user);
//       if (user.isEmpty()) {
//          throw new UsernameNotFoundException("User not found");
//       }
//
//
//        return new UserPrincipal(email, user.get());
//
//
//    }
//}
