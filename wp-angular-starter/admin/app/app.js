var WPAngularStarter = angular.module('admin-angular-starter', [
  'ui.router',
  'ngResource',
  'pascalprecht.translate',
  'smart-table',
  'mgcrea.ngStrap',
  'toastr',
  'angular-loading-bar',
  'ui.select',
  'ngQuickDate']);

  WPAngularStarter.constant("serverURL", "http://localhost:8080/servlet-showcase");
  WPAngularStarter.constant("apiURL", "http://localhost:8080/servlet-showcase/api");

  WPAngularStarter.config(["$stateProvider", "$urlRouterProvider", function ($stateProvider, $urlRouterProvider){
    //for undefined state, redirect to home
    $urlRouterProvider.otherwise("/");

    //here we defined the state
    $stateProvider.state("app", {
      url: "/",
      views: {
        "header": {
          templateUrl: "/admin/views/header.html"
        },
        "content": {
          templateUrl: "/admin/views/title.html",
          controller: "HomeController"
        }
      }
    }).state("app.users", {
      url: "/users",
      views: {
        "content@": {
          templateUrl: "/admin/views/user/users.html",
          controller: "UserController"
        }
      }
    }).state("app.userDetails", {
      url: "/users/:id",
      views: {
        "content@": {
          templateUrl: "/admin/views/user/userDetails.html",
          controller: "UserDetailsController"
        }
      }
    }).state("app.listings", {
      url: "/listings",
      views: {
        "content@": {
          templateUrl: "/admin/views/listing/listings.html",
          controller: "ListingController"
        }
      }
    }).state("app.messages", {
      url: "/messages",
      views: {
        "content@": {
          templateUrl: "/admin/views/listing/listings.html",
          controller: "ListingController"
        }
      }
    });
  }]);
