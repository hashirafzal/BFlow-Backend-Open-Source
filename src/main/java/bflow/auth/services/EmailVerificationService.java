package bflow.auth.services;

import bflow.auth.entities.User;

import java.util.UUID;

public interface EmailVerificationService {

    void sendVerificationEmail(User user);

    void verifyEmail(String token);

    void resendVerification(UUID userId);
}
