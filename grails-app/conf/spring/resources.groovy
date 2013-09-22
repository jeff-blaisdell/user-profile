import profile.AuthFailureListener
import profile.AuthSuccessListener
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler

// Place your Spring DSL code here
beans = {
    userDetailsService(profile.CustomUserDetailsService)

    redirectFailureHandlerExample(SimpleUrlAuthenticationFailureHandler) {
        defaultFailureUrl = '/failed' //redirect to this URL when authentication fails
    }

    redirectSuccessHandlerExample(SimpleUrlAuthenticationSuccessHandler) {
        defaultTargetUrl = '/' //redirect to this URL when authentication fails
    }

    authFailureListener(AuthFailureListener) {
        profileService = profileService
    }

    authSuccessListener(AuthSuccessListener) {
        profileService = profileService
    }
}
