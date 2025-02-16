package com.remly.remly.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "password_hash",length = 255)
    private String passwordHash;

    @Column(name = "phone_number", unique = true, length = 15)
    private String phoneNumber;

    @Column(name = "profile_picture")
    private String profilePicture;  // URL to profile picture

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "oauth_provider", length = 20)
    private String oauthProvider; // google, outlook, etc.

    @Column(name = "oauth_provider_id", unique = true, length = 50)
    private String oauthProviderId; // Provider user ID

    @Column(name = "is_verified", nullable = false)
    private Boolean isVerified = true;

    @Column(name = "verification_token")
    private String verificationToken;

    @Column(name = "password_reset_token")
    private String passwordResetToken;

    @Column(name = "failed_login_attempts")
    private Integer failedLoginAttempts = 0;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    @Column(name = "role", nullable = false, length = 20)
    private String role = "USER";  // Possible values: USER, ADMIN

    @Column(name = "two_factor_enabled", nullable = false)
    private Boolean twoFactorEnabled = false;

    @Column(name = "subscription_plan", length = 50)
    private String subscriptionPlan = "Free";  // e.g., Free, Premium

    @Column(name = "subscription_expiry")
    private LocalDate subscriptionExpiry;

    @Column(name = "notification_preferences", columnDefinition = "TEXT")
    private String notificationPreferences;  // Can store JSON as string

    @Column(name = "timezone", length = 50)
    private String timezone;

    @Column(name = "language", length = 20)
    private String language = "en";

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    //Constructors
    public User(String fullName, String email, String passwordHash, String phoneNumber, String profilePicture, LocalDate dateOfBirth, String oauthProvider, String oauthProviderId, Boolean isVerified, String verificationToken, String passwordResetToken, Integer failedLoginAttempts, LocalDateTime lastLogin, String role, Boolean twoFactorEnabled, String subscriptionPlan, LocalDate subscriptionExpiry, String notificationPreferences, String timezone, String language, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.fullName = fullName;
        this.email = email;
        this.passwordHash = passwordHash;
        this.phoneNumber = phoneNumber;
        this.profilePicture = profilePicture;
        this.dateOfBirth = dateOfBirth;
        this.oauthProvider = oauthProvider;
        this.oauthProviderId = oauthProviderId;
        this.isVerified = isVerified;
        this.verificationToken = verificationToken;
        this.passwordResetToken = passwordResetToken;
        this.failedLoginAttempts = failedLoginAttempts;
        this.lastLogin = lastLogin;
        this.role = role;
        this.twoFactorEnabled = twoFactorEnabled;
        this.subscriptionPlan = subscriptionPlan;
        this.subscriptionExpiry = subscriptionExpiry;
        this.notificationPreferences = notificationPreferences;
        this.timezone = timezone;
        this.language = language;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public User() {
    }
   //Constructors END

    //Getter and Settter
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getOauthProvider() {
        return oauthProvider;
    }

    public void setOauthProvider(String oauthProvider) {
        this.oauthProvider = oauthProvider;
    }

    public String getOauthProviderId() {
        return oauthProviderId;
    }

    public void setOauthProviderId(String oauthProviderId) {
        this.oauthProviderId = oauthProviderId;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Boolean getVerified() {
        return isVerified;
    }

    public void setVerified(Boolean verified) {
        isVerified = verified;
    }

    public String getVerificationToken() {
        return verificationToken;
    }

    public void setVerificationToken(String verificationToken) {
        this.verificationToken = verificationToken;
    }

    public String getPasswordResetToken() {
        return passwordResetToken;
    }

    public void setPasswordResetToken(String passwordResetToken) {
        this.passwordResetToken = passwordResetToken;
    }

    public Integer getFailedLoginAttempts() {
        return failedLoginAttempts;
    }

    public void setFailedLoginAttempts(Integer failedLoginAttempts) {
        this.failedLoginAttempts = failedLoginAttempts;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Boolean getTwoFactorEnabled() {
        return twoFactorEnabled;
    }

    public void setTwoFactorEnabled(Boolean twoFactorEnabled) {
        this.twoFactorEnabled = twoFactorEnabled;
    }

    public String getSubscriptionPlan() {
        return subscriptionPlan;
    }

    public void setSubscriptionPlan(String subscriptionPlan) {
        this.subscriptionPlan = subscriptionPlan;
    }

    public LocalDate getSubscriptionExpiry() {
        return subscriptionExpiry;
    }

    public void setSubscriptionExpiry(LocalDate subscriptionExpiry) {
        this.subscriptionExpiry = subscriptionExpiry;
    }

    public String getNotificationPreferences() {
        return notificationPreferences;
    }

    public void setNotificationPreferences(String notificationPreferences) {
        this.notificationPreferences = notificationPreferences;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", oauthProvider='" + oauthProvider + '\'' +
                ", oauthProviderId='" + oauthProviderId + '\'' +
                ", isVerified=" + isVerified +
                ", verificationToken='" + verificationToken + '\'' +
                ", passwordResetToken='" + passwordResetToken + '\'' +
                ", failedLoginAttempts=" + failedLoginAttempts +
                ", lastLogin=" + lastLogin +
                ", role='" + role + '\'' +
                ", twoFactorEnabled=" + twoFactorEnabled +
                ", subscriptionPlan='" + subscriptionPlan + '\'' +
                ", subscriptionExpiry=" + subscriptionExpiry +
                ", notificationPreferences='" + notificationPreferences + '\'' +
                ", timezone='" + timezone + '\'' +
                ", language='" + language + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
