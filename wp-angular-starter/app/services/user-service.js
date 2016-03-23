/**
 * Created by Dell on 16-Mar-16.
 */

WPAngularStarter.factory('User', ['$http', function($http){

    return {

        saveUser: function(data, config){

            return $http.post("http://localhost:8080/servlet-showcase/api/user/signup",data, config);
        },

        isUnique: function(username)
        {
            return $http.get("http://localhost:8080/servlet-showcase/api/users/" +  username );
        },

        loginUser: function(data, config){
            return $http.post("http://localhost:8080/servlet-showcase/api/user/login", data, config);
        }

    }

}]);