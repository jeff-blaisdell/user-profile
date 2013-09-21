<!DOCTYPE html>
<html>
<head>
    <title><g:message code="profile.signIn.page.title" /></title>
    <meta name="layout" content="main">
</head>
<body>
<main>
    <section class="l-globalwidth">
        <div class="sign-box">
            <form id="signin-form" name="signin" action="${authUrl}" method="post">
                <h3><g:message code="profile.signIn.form.label" default="Sign In" /></h3>
                <fieldset>
                    <div class="form-group">
                        <label for="j_username">
                            <g:message code="user.email.label" default="Email:" />
                        </label>
                        <div class="input-group">
                            <span class="input-group-addon">@</span>
                            <g:field type="email" class="form-control" name="j_username" required="true" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="j_password">
                            <g:message code="user.password.label" default="Password:" />
                        </label>
                        <g:field type="password" class="form-control" name="j_password" required="true" />
                    </div>

                    <g:if test="${flash.message}">
                        <div class="alert alert-danger alert-dismissable">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                            ${flash.message}
                        </div>
                    </g:if>

                    <div class="checkbox">
                        <label for="rememberMeCheckBox">
                            <g:message id="rememberMeCheckBox" code="user.rememberMe.label" default="Remember Me:" />
                        </label>
                        <g:checkBox name="${rememberMeParameter}" checked="${hasCookie}" />
                    </div>

                    <g:submitButton class="btn btn-default" name="update" value="Sign In" />
                    <div class="form-group">
                        <facebookAuth:connect />
                    </div>
                    <div class="form-group">
                        <g:link controller="registration" action="register">
                            <input type="button" class="btn btn-default" value="${message(code: 'profile.signIn.registration.button')}"/>
                        </g:link>
                    </div>
                </fieldset>
            </form>
        </div>
    </section>
</main>
</body>
</html>
