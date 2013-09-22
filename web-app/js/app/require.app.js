requirejs.config({
    baseUrl: '/profile/static/js',
    paths: {
        'jquery': 'jquery/jquery-1.10.2.min',
        'bootstrap': '../../bootstrap/3.0.0/js/bootstrap',
        'modernizr': 'modernizr/2.6.2/modernizr',
        'jquery-pickadate': 'pickadate/3.2.2/picker'
    },
    shim: {
        'bootstrap': ['jquery'],
        'jquery-pickadate': ['jquery'],
        'modernizr': {
            exports: 'Modernizr'
        }
    }
});