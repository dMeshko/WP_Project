/**
 * Created by Darko on 2/26/2016.
 */

WPAngularStarter.factory("UserService", ["$http", "apiURL", function ($http, apiURL){
    var factory = {};

    factory.getAllUsers = function (){
        return $http.get(apiURL + "/user");
    };

    factory.removeUser = function (id){
        return $http.delete(apiURL + "/user/remove/" + id);
    };

    factory.getUser = function (id){
        return $http.get(apiURL + "/user/" + id);
    }

    return factory;
}]);