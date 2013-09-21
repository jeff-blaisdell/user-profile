package profile

import org.springframework.context.ApplicationListener
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;

class AuthFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    def ProfileService profileService

    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
        profileService.loginFailed(event.getAuthentication().getPrincipal())
    }

}