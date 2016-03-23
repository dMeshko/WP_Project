/**
 * Created by Mile on 03/09/2016.
 */
WPAngularStarter.config(function ($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/');
    $stateProvider
        .state('create-listing', {
            url: '/listing',
            templateUrl: '/views/listing.html',
            controller: 'ListingController'
        })
        .state('view-listings', {
            url: '/listings',
            templateUrl: '/views/view-listings.html',
            controller: 'ViewListingsController'
        })
        .state('view-listing', {
            url: '/listing/{id}',
            templateUrl: 'views/view-listing.html',
            controller: 'ViewListingController'
        })
        .state('sign-up', {
            url: '/signup',
            templateUrl: 'views/signup.html',
            controller: 'signupController',
            css: 'css/signup.css'
        })
        .state('home', {
            url: '/',
            templateUrl: 'views/home.html',
            controller: 'homeController',
            css: 'css/home.css'
        })
        .state('log-in', {
            url: '/login',
            templateUrl: 'views/login.html',
            controller: 'loginController',
            css: 'css/login.css'
        });
});
