package profile

import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

class SignOutController {

    def signout() {
        redirect uri: SpringSecurityUtils.securityConfig.logout.filterProcessesUrl
    }

}
