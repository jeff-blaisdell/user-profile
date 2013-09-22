import profile.Address
import profile.Role
import profile.User
import profile.UserRole

class BootStrap {

    def init = { servletContext ->
        //Configure Security Roles
        def userRole = Role.findByAuthority('ROLE_USER') ?: new Role(authority: 'ROLE_USER').save(failOnError: true)
        def facebookRole = Role.findByAuthority('ROLE_FACEBOOK') ?: new Role(authority: 'ROLE_FACEBOOK').save(failOnError: true)
        def adminRole = Role.findByAuthority('ROLE_ADMIN') ?: new Role(authority: 'ROLE_ADMIN').save(failOnError: true)

        def adminUser = User.findByEmail('jeff.j.blaisdell@gmail.com') ?: new User(
                email: 'jeff.j.blaisdell@gmail.com',
                password: 'password',
                firstName: 'Jeff',
                lastName: 'Blaisdell',
                birthDate: Date.parse('MMM dd yyyy', 'May 05 1981'),
                address: new Address(
                        line1: '7172 Jenner Bay S',
                        city: 'Cottage Grove',
                        state: 'MN',
                        country: 'US',
                        postalCode: '55016',
                        location: [lat: 44.848217d, long: -92.911141d]
                ),
                enabled: true).save(failOnError: true)

        adminUser.save()

        if (!adminUser.authorities.contains(adminRole)) {
            UserRole.create(adminUser, adminRole)
        }

        if (!adminUser.authorities.contains(userRole)) {
            UserRole.create(adminUser, userRole)
        }

    }
    def destroy = {
    }
}
