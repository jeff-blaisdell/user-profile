package profile

import org.springframework.context.ApplicationListener
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;

class AuthSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {

    def ProfileService profileService

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {

        def user = event.getAuthentication().getPrincipal();
        profileService.loginSuccess(user)
    }
}
