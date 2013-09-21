<!DOCTYPE html>
<html>
<head>
    <title>Password Reset</title>
    <meta name="layout" content="main">
</head>
<body>
<main>
    <section class="l-globalwidth">

        <div class="box password-reset-box">
            <g:if test="${passwordResetVerifiedSuccess}">
                <form id="password-reset-form" name="changePassword" action="${postUrl}" method="post">
                    <h3><g:message code="password.reset.label" /></h3>
                    <p class="lead"><g:message code="password.reset.instructions" /></p>
                    <fieldset>
                        <div class="form-group">
                            <label for="password">
                                <g:message code="user.password.label" default="Password:" />
                            </label>
                            <g:field type="password" class="form-control" name="password" required="true" />
                            <g:hasErrors bean="${passwordCommand}" field="password">
                                <div class="alert alert-danger alert-dismissable">
                                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                    <g:renderErrors bean="${passwordCommand}" as="list" field="password"/>
                                </div>
                            </g:hasErrors>
                            <g:hasErrors bean="${user}" field="password">
                                <div class="alert alert-danger alert-dismissable">
                                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                    <g:renderErrors bean="${user}" as="list" field="password"/>
                                </div>
                            </g:hasErrors>
                        </div>

                        <div class="form-group">
                            <label for="confirmPassword">
                                <g:message code="user.confirmPassword.label" default="Confirm Password:" />
                            </label>
                            <g:field type="password" class="form-control" name="confirmPassword" required="true" />
                            <g:hasErrors bean="${passwordCommand}" field="confirmPassword">
                                <div class="alert alert-danger alert-dismissable">
                                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                    <g:renderErrors bean="${passwordCommand}" as="list" field="confirmPassword"/>
                                </div>
                            </g:hasErrors>
                            <g:hasErrors bean="${user}" field="confirmPassword">
                                <div class="alert alert-danger alert-dismissable">
                                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                    <g:renderErrors bean="${user}" as="list" field="confirmPassword"/>
                                </div>
                            </g:hasErrors>
                        </div>

                        <g:submitButton class="btn btn-default" name="update" value="Submit" />

                    </fieldset>
                </form>
            </g:if>
            <g:elseif test="${passwordResetVerifiedFailure}">
                <h3><g:message code="password.reset.label" /></h3>
                <p class="lead"><g:message code="password.reset.verified.failure" /></p>
            </g:elseif>
            <g:else>
                <form id="password-reset-form" name="passwordReset" action="passwordReset" method="post">
                    <h3><g:message code="password.reset.label" /></h3>
                    <p class="lead"><g:message code="password.reset.instructions" /></p>
                    <fieldset>
                        <div class="form-group">
                            <label for="email">
                                <g:message code="user.email.label" default="Email:" />
                            </label>
                            <div class="input-group">
                                <span class="input-group-addon">@</span>
                                <g:field type="email" class="form-control" name="email" required="true" />
                            </div>
                        </div>

                        <g:if test="${passwordResetEmailFailure}">
                            <div class="alert alert-danger alert-dismissable">
                                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                <g:message code="password.reset.email.failure" />
                            </div>
                        </g:if>

                        <g:if test="${passwordResetEmailSuccess}">
                            <div class="alert alert-success alert-dismissable">
                                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                <g:message code="password.reset.email.success" />
                            </div>
                        </g:if>

                        <g:submitButton class="btn btn-default" name="update" value="Submit" />

                    </fieldset>
                </form>
            </g:else>
        </div>
    </section>
</main>
</body>
</html>
