<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title><g:layoutTitle default="Profile"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">

    <script type="text/javascript">
        // Facebook adds ugly hash on auth redirect.
        if (window.location.hash == '#_=_') {
            window.location.hash = ''; // for older browsers, leaves a # behind
            history.pushState('', document.title, window.location.pathname); // nice and clean
            e.preventDefault(); // no page reload
        }
    </script>
    <r:require modules="app"/>
    <g:layoutHead/>
    <r:layoutResources />
</head>
<body>
<section class="nav-section l-globalwidth clearfix">
    <nav class="pull-right">
        <ul class="nav nav-pills">
            <li><g:link controller="browse" action="index">Home</g:link></li>
            <sec:ifNotLoggedIn>
                <li><g:link controller="signIn" action="signin">Sign In</g:link></li>
            </sec:ifNotLoggedIn>
            <sec:ifLoggedIn>
                <li><g:link controller="signOut" action="signout">Sign Out</g:link></li>
                <li><g:link controller="account" action="account">My Account</g:link></li>
            </sec:ifLoggedIn>
        </ul>
        <sec:ifLoggedIn>
            <span class="nav-welcome">Welcome <sec:loggedInUserInfo field="firstName" /></span>
        </sec:ifLoggedIn>
    </nav>
</section>
<g:layoutBody/>
<r:layoutResources />
</body>
</html>
