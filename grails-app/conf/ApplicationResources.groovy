modules = {
    app {
        resource url:'/js/require/2.1.8/require.js', disposition: 'head'
        resource url:'/js/app/require.app.js', disposition: 'head'
        resource url:'/js/app/main.js', disposition: 'head'
    }

    registration {
        resource url:'/js/app/registration.js', disposition: 'defer'
    }
}