class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller: "browse", action:"index")
        "500"(controller: 'error', action: 'internalServerError')
        "403"(controller: 'error', action: 'forbidden')
        "404"(controller: 'error', action: 'notFound')
        "/account"(controller: "account", action: "account")
        "/account/signin"(controller: "signIn", action: "signin")
        "/account/signout"(controller: "signOut", action: "signout")
        "/account/passwordReset"(controller: "passwordReset", action: "passwordReset")
        "/account/changePassword"(controller: "passwordReset", action: "changePassword")
        "/account/register"(controller: "registration", action: "register")
        "/account/create"(controller: "registration", action: "create")
        "/account/verify"(controller: "registration", action: "verifyRegistration")
    }
}
