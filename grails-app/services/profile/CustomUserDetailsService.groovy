package profile

import org.codehaus.groovy.grails.plugins.springsecurity.GrailsUserDetailsService
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.GrantedAuthorityImpl
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

class CustomUserDetailsService implements GrailsUserDetailsService {

    /**
     * Some Spring Security classes (e.g. RoleHierarchyVoter) expect at least
     * one role, so we give a user with no granted roles this one which gets
     * past that restriction but doesn't grant anything.
     */
    static final List NO_ROLES = [
            new GrantedAuthorityImpl(SpringSecurityUtils.NO_ROLE)
    ]

    @Override
    UserDetails loadUserByUsername(String username, boolean loadRoles)
    throws UsernameNotFoundException {
        return loadUserByUsername(username)
    }

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User.withTransaction { status ->

            User user = User.findByEmail(username)
            if (!user) throw new UsernameNotFoundException(
                    'User not found', username)

            def authorities = user.authorities.collect {
                new GrantedAuthorityImpl(it.authority)
            }

            createUserDetails(user, authorities)
        }
    }

    UserDetails createUserDetails(user, Collection<GrantedAuthority> authorities) {

        def conf = SpringSecurityUtils.securityConfig

        String usernamePropertyName = conf.userLookup.usernamePropertyName
        String passwordPropertyName = conf.userLookup.passwordPropertyName
        String enabledPropertyName = conf.userLookup.enabledPropertyName
        String accountExpiredPropertyName = conf.userLookup.accountExpiredPropertyName
        String accountLockedPropertyName = conf.userLookup.accountLockedPropertyName
        String passwordExpiredPropertyName = conf.userLookup.passwordExpiredPropertyName

        String username = user."$usernamePropertyName"
        String password = user."$passwordPropertyName"
        boolean enabled = enabledPropertyName ? user."$enabledPropertyName" : true
        boolean accountExpired = accountExpiredPropertyName ? user."$accountExpiredPropertyName" : false
        boolean accountLocked = accountLockedPropertyName ? user."$accountLockedPropertyName" : false
        boolean passwordExpired = passwordExpiredPropertyName ? user."$passwordExpiredPropertyName" : false

        new SecUser(username, password, enabled, !accountExpired, !passwordExpired, !accountLocked, authorities, user.id, user.firstName, user.lastName)
    }
}
