requirejs.config({
    baseUrl: '/profile/js',
    paths: {
        'jquery': 'jquery/1.10.2/jquery',
        'bootstrap': '../bootstrap/3.0.0/js/bootstrap',
        'jquery-pickadate': 'pickadate/3.2.2/picker',
        'jquery-pickadate-date': 'pickadate/3.2.2/picker.date',
        'jquery-pickadate-time': 'pickadate/3.2.2/picker.time'
    },
    shim: {
        'bootstrap': ['jquery'],
        'jquery-pickadate': ['jquery', 'jquery-pickadate-date', 'jquery-pickadate-time']
    }
});