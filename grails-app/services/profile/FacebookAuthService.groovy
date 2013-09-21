package profile

import com.the6hours.grails.springsecurity.facebook.FacebookAuthToken
import org.springframework.social.facebook.api.Facebook
import org.springframework.social.facebook.api.FacebookProfile
import org.springframework.social.facebook.api.impl.FacebookTemplate
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import org.springframework.social.facebook.api.Location;

class FacebookAuthService {

    def prepopulateAppUser(def user, FacebookAuthToken token) {
        def securityConf = SpringSecurityUtils.securityConfig
        def dateParser = new java.text.SimpleDateFormat("MM/DD/YYYY")
        Facebook facebook = new FacebookTemplate(token.accessToken.accessToken)
        FacebookProfile fbProfile = facebook.userOperations().userProfile
        Location location = null
        if (fbProfile.location?.id != null)
            location = facebook.fetchObject(fbProfile.location.id, Location.class);

        user.setProperty(securityConf.userLookup.usernamePropertyName, fbProfile.email)
        user.setProperty(securityConf.userLookup.passwordPropertyName, token.accessToken.accessToken)
        user.setProperty(securityConf.userLookup.enabledPropertyName, true)
        user.setProperty(securityConf.userLookup.accountExpiredPropertyName, false)
        user.setProperty(securityConf.userLookup.accountLockedPropertyName, false)
        user.setProperty(securityConf.userLookup.passwordExpiredPropertyName, false)

        user.firstName = fbProfile.firstName
        user.lastName = fbProfile.lastName
        user.birthDate = dateParser.parse(fbProfile.birthday)
        user.email = fbProfile.email

        def address = new Address()
        address.city = location.city
        address.state = location.state
        address.location.lat = location.latitude
        address.location.long = location.longitude
        user.address = address
    }
}
