package profile

import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import org.springframework.security.authentication.AccountExpiredException
import org.springframework.security.authentication.CredentialsExpiredException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.LockedException
import org.springframework.security.web.WebAttributes

class SignInController {

    def springSecurityService

    def signin() {

        def config = SpringSecurityUtils.securityConfig

        if (springSecurityService.isLoggedIn()) {
            redirect uri: config.successHandler.defaultTargetUrl
            return
        }
        def authUrl = "${request.contextPath}${config.apf.filterProcessesUrl}"
        def model =  [authUrl: authUrl, rememberMeParameter: config.rememberMe.parameter]

        def exception = session[WebAttributes.AUTHENTICATION_EXCEPTION]
        if (exception) {
            if (exception instanceof AccountExpiredException) {
                flash.message = g.message(code: "springSecurity.errors.login.expired")
            }
            else if (exception instanceof CredentialsExpiredException) {
                flash.message = g.message(code: "springSecurity.errors.login.passwordExpired")
            }
            else if (exception instanceof DisabledException) {
                flash.message = g.message(code: "springSecurity.errors.login.disabled")
            }
            else if (exception instanceof LockedException) {
                flash.message = g.message(code: "springSecurity.errors.login.locked")
            }
            else {
                flash.message = g.message(code: "springSecurity.errors.login.fail")
            }
        }

        render view: 'signin', model: model
    }

}
