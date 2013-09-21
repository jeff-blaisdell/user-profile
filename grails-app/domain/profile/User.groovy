package profile

import org.bson.types.ObjectId
import org.codehaus.groovy.grails.plugins.springsecurity.GrailsUser
import org.springframework.security.core.GrantedAuthority;
import com.mongodb.WriteConcern;

class User {

    transient springSecurityService

    ObjectId id
    String firstName
    String lastName
    Date birthDate
    Address address
    String email
    String password
    boolean enabled
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired
    Integer failedLoginCount

    static embedded = ['address']

    static constraints = {
        email blank:false, unique:true, maxSize:100
        password blank:false, maxSize: 100
        firstName blank:false, maxSize:100
        lastName blank:false, maxSize:100
        birthDate blank:false, maxSize:100
        address nullable:true, blank:true
        failedLoginCount nullable: true
    }

    static mapping = {
        password column: '`password`'
    }

    Set<Role> getAuthorities() {
        UserRole.findAllByUser(this).collect { it.role } as Set
    }

    def beforeInsert() {
        this.email = this.email.toLowerCase()
        this.enabled = true
        encodePassword()
    }

    def beforeUpdate() {

    }

    protected void encodePassword() {
        password = springSecurityService.encodePassword(password)
    }
}
