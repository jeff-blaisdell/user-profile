package profile

import groovy.text.SimpleTemplateEngine

class ProfileService {

    def grailsApplication
    def mailService
    def grailsLinkGenerator
    def springSecurityService

    @grails.validation.Validateable
    public class PasswordCommand {
        String password
        String confirmPassword

        static constraints = {
            password(blank: false, minSize: 6)
            confirmPassword(validator: { val, obj ->
                if (!val) {
                    return ['default.null.message', 'confirmPassword', 'UserController']
                }

                if (!val.equals(obj.password)) {
                    return ['signin.passwords.do.not.match']
                }
            })
        }
    }

    def register(user) {

        def registerToken = UUID.randomUUID().toString().replaceAll('-', '')
        user['registerToken'] = registerToken
        user.setEnabled(false)

        if (!user.save()) {
            log error "Unable to create user account. " + user.dump();
        }


        def role = Role.findByAuthority('ROLE_USER')
        UserRole.create(user as User, role as Role)

        def url = grailsLinkGenerator.link(controller: 'account', action: 'verifyRegistration', absolute: true, params: [token: registerToken, email: user.getEmail()])
        def conf = grailsApplication.config
        def body = conf.user.register.emailBody
        if (body.contains('$')) {
            body = new SimpleTemplateEngine().createTemplate(body).make([user: user, url: url])
        }

        mailService.sendMail {
            to user.email
            from conf.user.register.emailFrom
            subject conf.user.register.emailSubject
            html body.toString()
        }

    }

    def verifyRegistration(user, token) {

        if (!user || !user['registerToken'] || !user['registerToken'].equals(token)) {
            return false
        }

        user.setEnabled(true)
        user['registerToken'] = null
        if(!user.save()) {
            log error "Unable to update user account. " + user.dump();
        }

        return true
    }

    def passwordReset(email) {
        def user = User.findByEmail(email as String)
        if (user) {
            if (!isSocialUser(user)) {
                def passwordResetToken = UUID.randomUUID().toString().replaceAll('-', '')
                user['passwordResetToken'] = passwordResetToken
                user.save()

                def url = grailsLinkGenerator.link(controller: 'account', action: 'passwordReset', absolute: true, params: [token: passwordResetToken, email: user.getEmail()])
                def conf = grailsApplication.config
                def body = conf.user.passwordReset.emailBody
                if (body.contains('$')) {
                    body = new SimpleTemplateEngine().createTemplate(body).make([user: user, url: url])
                }

                mailService.sendMail {
                    to user.email
                    from conf.user.passwordReset.emailFrom
                    subject conf.user.passwordReset.emailSubject
                    html body.toString()
                }

                return true
            }
        }
        return false
    }

    def verifyPasswordResetToken(email, token) {

        if (email && token) {
            def user = User.findByEmail(email as String)
            if (user && token.equals(user['passwordResetToken'])) {
                return true
            }
        }
        return false
    }

    def changePassword(email, token, password) {
        if (email && token && password) {
            def user = User.findByEmail(email)
            if (user && token.equals(user['passwordResetToken'])) {
                user['passwordResetToken'] = null
                user.password = springSecurityService.encodePassword(password as String)
                if (!user.save()) {
                    log error 'Unable to change password for user [' + email + ']'
                }
            }
        }
    }

    def isSocialUser(user) {
        def facebookRole = Role.findByAuthority('ROLE_FACEBOOK')
        def roles = user.getAuthorities()
        return ( roles && roles.contains(facebookRole))
    }

    def loginFailed(email) {

        def user = User.findByEmail(email)

        if (user) {
            int failures = (user.getFailedLoginCount()?:0) + 1
            user.setFailedLoginCount(failures)

            if (user.getFailedLoginCount() > 4) {
                user.setAccountLocked(true)
            }
            if(!user.save()) {
                log error "Unable to process failed login email[" + email + "] " + user.dump()
            }
        }
    }

    def loginSuccess(user) {
        if (user) {
            user = User.findByEmail(user.email as String)
            user.setFailedLoginCount(0)
            user.setAccountLocked(false)
            if(!user.save()) {
                log error "Unable to process success login: " + user.dump()
            }
        }
    }

}
