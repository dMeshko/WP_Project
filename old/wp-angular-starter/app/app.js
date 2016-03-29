var WPAngularStarter = angular.module('wp-angular-starter', [
    'ui.router',
    'ngResource',
    'pascalprecht.translate',
    'smart-table',
    'mgcrea.ngStrap',
    'toastr',
    'angular-loading-bar',
    'ui.select',
    'ngQuickDate',
    'uiRouterStyles',
    'ngMessages',
    'angularUtils.directives.dirPagination']);

WPAngularStarter.constant("serverURL", "http://localhost:8080/servlet-showcase");
WPAngularStarter.constant("apiURL", "http://localhost:8080/servlet-showcase/api");
WPAngularStarter.constant("siteURL", "http://localhost:8000/#");

WPAngularStarter.config(function (paginationTemplateProvider){
    paginationTemplateProvider.setPath('../bower_components/angularUtils-pagination/dirPagination.tpl.html');
});

