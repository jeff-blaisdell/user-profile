package profile

import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

class RegistrationController {

    def springSecurityService
    def profileService

    def register() {
        def config = SpringSecurityUtils.securityConfig

        if (springSecurityService.isLoggedIn()) {
            redirect uri: config.successHandler.defaultTargetUrl
            return
        }

        render (view: 'register')
    }

    def create(ProfileService.PasswordCommand passwordCommand) {
        def user = new User(params)

        if (!user.validate()) {
            render(view: 'register', model: ['user': user, 'passwordCommand': passwordCommand])
            return
        }

        if (!passwordCommand.validate()) {
            render(view: 'register', model: ['user': user, 'passwordCommand': passwordCommand])
            return
        }

        profileService.register(user)

        render (view: 'register', model: [emailSent: true])
    }

    def verifyRegistration() {

        if (!params.token || !params.email) {
            render (controller: 'account', view: 'account', model: [badRegistration: true])
            return
        }

        def user = User.findByEmail(params.email as String)

        if (!profileService.verifyRegistration(user, params.token)) {
            render (controller: 'account', view: 'account', model: [badRegistration: true])
            return
        }

        springSecurityService.reauthenticate(user.email)

        def config = SpringSecurityUtils.securityConfig

        redirect uri: config.successHandler.defaultTargetUrl
    }

}
