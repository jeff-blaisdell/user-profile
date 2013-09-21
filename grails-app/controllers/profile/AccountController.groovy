package profile

class AccountController {

    def springSecurityService

    def account() {
        if (!springSecurityService.isLoggedIn()) {
            redirect controller: 'signIn', action: 'signin', params: params
            return
        }
        render(view: 'account')
    }

}
