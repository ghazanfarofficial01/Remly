//package com.remly.remly.model;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//
//import java.util.Collection;
//import java.util.List;
//import java.util.Map;
//
//public class UserPrincipal implements UserDetails {
//
//    private final String email;
//    private final User user;
//
//    public UserPrincipal(String email, User user) {
//        if (user == null) {
//            throw new IllegalArgumentException("User cannot be null");
//        }
//        this.email = email;
//        this.user = user;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
//    }
//
//    @Override
//    public String getPassword() {
//        return user.getPasswordHash();
//    }
//
//    @Override
//    public String getUsername() {
//        return user.getEmail();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//
//    public static class CustomUserPrincipal implements OAuth2User {
//        private OAuth2User oauth2User;
//        private Long userId; // Custom field for database user ID
//        //private String role;
//        private String email;// Custom field for user role
//
//        public CustomUserPrincipal(OAuth2User oauth2User, Long userId, String email) {
//            this.oauth2User = oauth2User;
//            this.userId = userId;
//            this.email = email;
//        }
//
//        @Override
//        public String getName() {
//            return oauth2User.getAttribute("name"); // Default name attribute
//        }
//
//        @Override
//        public Map<String, Object> getAttributes() {
//            return oauth2User.getAttributes();
//        }
//
//        @Override
//        public Collection<? extends GrantedAuthority> getAuthorities() {
//            return oauth2User.getAuthorities();
//        }
//
//        // Custom methods
//        public Long getUserId() {
//            return userId;
//        }
//
//        public String getEmail() {
//            return email;
//        }
//    }
//}
