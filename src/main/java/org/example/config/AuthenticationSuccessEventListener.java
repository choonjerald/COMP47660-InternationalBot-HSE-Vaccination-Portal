package org.example.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.service.LoginAttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class AuthenticationSuccessEventListener implements
        ApplicationListener<AuthenticationSuccessEvent> {

    private static final Logger logger = LogManager.getLogger(AuthenticationSuccessEventListener.class);

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private LoginAttemptService loginAttemptService;

    @Override
    public void onApplicationEvent(final AuthenticationSuccessEvent e) {
        final String xfHeader = request.getHeader("X-Forwarded-For");
        String ip;
        if (xfHeader == null) {
            ip = request.getRemoteAddr();
            loginAttemptService.loginSucceeded(ip);
        } else {
            ip = xfHeader.split(",")[0];
            loginAttemptService.loginSucceeded(ip);
        }
        logger.info("**SUCCESS** Login attempt with username: " + e.getAuthentication().getName() + " for IP: " + ip);
    }
}