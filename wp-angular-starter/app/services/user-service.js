/**
 * Created by Dell on 16-Mar-16.
 */

WPAngularStarter.factory('UserService', ['$http', 'apiURL', function($http, apiURL){

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
        },

        sendMessage: function (message){
            var data = $.param({
                "content": message.content,
                "userToId": message.userToId
            });
            var config = {headers: {'Content-Type': 'application/x-www-form-urlencoded'}};
            return $http.post(apiURL + "/user/message/new", data, config);
        }
    }

}]);