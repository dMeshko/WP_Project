/**
 * Created by Darko on 3/10/2016.
 */

WPAngularStarter.factory("ListingService", ["$http", "apiURL", function ($http, apiURL){
    var factory = {};

    factory.getListingsByUser = function (id){
        return $http.get(apiURL + "/listing/user/" + id);
    };

    return factory;
}]);