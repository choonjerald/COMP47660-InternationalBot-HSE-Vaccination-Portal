package org.example.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.service.LoginAttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class AuthenticationFailureListener implements
        ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    private static final Logger logger = LogManager.getLogger(AuthenticationFailureListener.class);

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private LoginAttemptService loginAttemptService;

    @Autowired
    private MessageSource messages;

    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent e) {
        final String xfHeader = request.getHeader("X-Forwarded-For");
        String ip;
        if (xfHeader == null) {
            ip = request.getRemoteAddr();
            loginAttemptService.loginFailed(ip);
        } else {
            ip = xfHeader.split(",")[0];
            loginAttemptService.loginFailed(ip);
        }

        logger.warn("**FAILED** Login attempt with username: " + e.getAuthentication().getName() + " for IP: " + ip);
    }
}
