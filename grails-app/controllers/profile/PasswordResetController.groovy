package profile

import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

class PasswordResetController {

    def springSecurityService
    def profileService

    def passwordReset() {
        if (springSecurityService.isLoggedIn()) {
            redirect uri: config.successHandler.defaultTargetUrl
            return
        }

        if (params.email && params.token) {
            if(profileService.verifyPasswordResetToken(params.email, params.token)) {
                def postUrl = createLink(action: 'changePassword', params: [email: params.email, token: params.token])
                render (view: 'passwordReset', model: [passwordResetVerifiedSuccess: true, postUrl: postUrl])
                return
            } else {
                render (view: 'passwordReset', model: [passwordResetVerifiedFailure: true])
                return
            }
        }

        if (params.email) {
            if (profileService.passwordReset(params.email)) {
                render (view: 'passwordReset', model: [passwordResetEmailSuccess: true])
                return
            } else {
                render (view: 'passwordReset', model: [passwordResetEmailFailure: true])
                return
            }
        }

        render view: 'passwordReset'
    }

    def changePassword(ProfileService.PasswordCommand passwordCommand) {
        if (!passwordCommand.validate()) {
            def postUrl = createLink(action: 'changePassword', params: [email: params.email, token: params.token])
            render (view: 'passwordReset', model: [passwordResetVerifiedSuccess: true, postUrl: postUrl, 'passwordCommand': passwordCommand])
            return
        }
        profileService.changePassword(params.email, params.token, params.password)

        springSecurityService.reauthenticate(params.email as String)

        def config = SpringSecurityUtils.securityConfig

        redirect uri: config.successHandler.defaultTargetUrl
    }

}
