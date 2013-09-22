<!DOCTYPE html>
<html>
<head>
    <title><g:message code="profile.registration.page.title"/></title>
    <meta name="layout" content="main">
    <r:require modules="registration"/>
</head>

<body>
<main>

<g:if test="${emailSent}">
    <br/>
    <g:message code='profile.registration.email.sent'/>
</g:if>
<g:elseif test="${badRegistration}">
    <br/>
    <g:message code='profile.registration.bad'/>
</g:elseif>
<g:else>
    <section class="l-globalwidth">
        <div class="account-box">
            <g:form id="account-form" name="account" action="register" method="post">
                <h3><g:message code="profile.registration.form.label" default="Create Account"/></h3>
                <fieldset>
                    <div class="form-group">
                        <label for="firstName">
                            <g:message code="user.firstName.label" default="First Name:"/>
                        </label>
                        <g:field type="text" class="form-control" name="firstName" required="true"value="${user?.firstName}"/>
                        <g:hasErrors bean="${user}" field="firstName">
                            <div class="alert alert-danger alert-dismissable">
                                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                <g:renderErrors bean="${user}" as="list" field="firstName"/>
                            </div>
                        </g:hasErrors>
                    </div>

                    <div class="form-group">
                        <label for="lastName">
                            <g:message code="user.lastName.label" default="Last Name:"/>
                        </label>
                        <g:field type="text" class="form-control" name="lastName" required="true"
                                 value="${user?.lastName}"/>
                        <g:hasErrors bean="${user}" field="lastName">
                            <div class="alert alert-danger alert-dismissable">
                                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                <g:renderErrors bean="${user}" as="list" field="lastName"/>
                            </div>
                        </g:hasErrors>
                    </div>

                    <div class="form-group">
                        <label for="email">
                            <g:message code="user.email.label" default="Email:"/>
                        </label>

                        <div class="input-group">
                            <span class="input-group-addon">@</span>
                            <g:field type="email" class="form-control" name="email" required="true" value="${user?.email}"/>
                        </div>
                        <g:hasErrors bean="${user}" field="email">
                            <div class="alert alert-danger alert-dismissable">
                                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                <g:renderErrors bean="${user}" as="list" field="email"/>
                            </div>
                        </g:hasErrors>
                    </div>

                    <div class="form-group">
                        <label for="birthDate">
                            <g:message code="user.birthDate.label" default="Birth Date:"/>
                        </label>
                        <g:datePicker name="birthDate" noSelection="['': '-Choose-']" required="true" precision="day" value="${user?.birthDate}" default="none"/>
                        <g:hasErrors bean="${user}" field="birthDate">
                            <div class="alert alert-danger alert-dismissable">
                                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                <g:renderErrors bean="${user}" as="list" field="birthDate"/>
                            </div>
                        </g:hasErrors>
                    </div>

                    <div class="form-group">
                        <label for="password">
                            <g:message code="user.password.label" default="Password:"/>
                        </label>
                        <g:field type="password" class="form-control" name="password" required="true"/>
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
                            <g:message code="user.confirmPassword.label" default="Confirm Password:"/>
                        </label>
                        <g:field type="password" class="form-control" name="confirmPassword" required="true"/>
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

                    <div class="form-group">
                        <label for="line1">
                            <g:message code="address.line1.label" default="Line 1:"/>
                        </label>
                        <g:field type="text" name="line1" class="form-control" value="${user?.address?.line1}" autocomplete="true"/>
                        <g:hasErrors bean="${user}" field="address.line1">
                            <div class="alert alert-danger alert-dismissable">
                                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                <g:renderErrors bean="${user}" as="list" field="address.line1"/>
                            </div>
                        </g:hasErrors>
                    </div>

                    <div class="form-group">
                        <label for="line2">
                            <g:message code="address.line2.label" default="Line 2:"/>
                        </label>
                        <g:field type="text" name="line2" class="form-control" value="${user?.address?.line2}" autocomplete="true"/>
                        <g:hasErrors bean="${user}" field="address.line2">
                            <div class="alert alert-danger alert-dismissable">
                                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                <g:renderErrors bean="${user}" as="list" field="address.line2"/>
                            </div>
                        </g:hasErrors>
                    </div>

                    <div class="form-group">
                        <label for="line3">
                            <g:message code="address.line3.label" default="Line 3:" autocomplete="true"/>
                        </label>
                        <g:field type="text" name="line3" class="form-control" value="${user?.address?.line3}"/>
                        <g:hasErrors bean="${user}" field="address.line3">
                            <div class="alert alert-danger alert-dismissable">
                                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                <g:renderErrors bean="${user}" as="list" field="address.line3"/>
                            </div>
                        </g:hasErrors>
                    </div>

                    <div class="form-group">
                        <label for="city">
                            <g:message code="address.city.label" default="City:"/>
                        </label>
                        <g:field type="text" name="line1" class="form-control" value="${user?.address?.city}" autocomplete="true"/>
                        <g:hasErrors bean="${user}" field="address.city">
                            <div class="alert alert-danger alert-dismissable">
                                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                <g:renderErrors bean="${user}" as="list" field="address.city"/>
                            </div>
                        </g:hasErrors>
                    </div>

                    <div class="form-group">
                        <label for="state">
                            <g:message code="address.state.label" default="State:"/>
                        </label>
                        <g:field type="text" name="state" class="form-control" value="${user?.address?.state}" autocomplete="true"/>
                        <g:hasErrors bean="${user}" field="address.state">
                            <div class="alert alert-danger alert-dismissable">
                                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                <g:renderErrors bean="${user}" as="list" field="address.state"/>
                            </div>
                        </g:hasErrors>
                    </div>

                    <div class="form-group">
                        <label for="country">
                            <g:message code="address.country.label" default="Country:" autocomplete="true"/>
                        </label>
                        <g:field type="text" name="country" class="form-control" value="${user?.address?.country}"/>
                        <g:hasErrors bean="${user}" field="address.country">
                            <div class="alert alert-danger alert-dismissable">
                                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                <g:renderErrors bean="${user}" as="list" field="address.country"/>
                            </div>
                        </g:hasErrors>
                    </div>

                    <div class="form-group">
                        <label for="postalCode">
                            <g:message code="address.postalCode.label" default="Postal Code:"/>
                        </label>
                        <g:field type="text" name="postalCode" class="form-control" value="${user?.address?.postalCode}"
                                 autocomplete="true"/>
                        <g:hasErrors bean="${user}" field="address.postalCode">
                            <div class="alert alert-danger alert-dismissable">
                                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                <g:renderErrors bean="${user}" as="list" field="address.postalCode"/>
                            </div>
                        </g:hasErrors>
                    </div>

                    <div class="form-group">
                        <g:submitButton class="btn btn-primary" name="update" value="Create Account"/>
                    </div>
                </fieldset>
            </g:form>
        </div>
    </section>
</g:else>
</main>
</body>
</html>
