/**
 * Created by Dell on 16-Mar-16.
 */

WPAngularStarter.factory('UserService', ['$http', 'apiURL', function ($http, apiURL) {
<<<<<<< HEAD
    return {
        saveUser: function (data, config) {
            return $http.post("http://localhost:8080/servlet-showcase/api/user/signup", data, config);
=======

    return {

        saveUser: function (data, config) {
            return $http.post("http://localhost:8080/servlet-showcase/api/user/signup", data, config);
        },
        editUser: function (user) {
            return $http({
                method: 'POST',
                url: apiURL + '/user/update/' + user.id,
                data: $.param({
                    id: user.id,
                    name: user.name,
                    surname: user.surname,
                    email: user.email,
                    username: user.username,
                    password: user.password,
                    birthDate: user.birthDate
                }),
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }
            });
>>>>>>> origin/master
        },

        isUnique: function (username) {
            return $http.get("http://localhost:8080/servlet-showcase/api/users/" + username);
        },

        loginUser: function (data, config) {
            return $http.post("http://localhost:8080/servlet-showcase/api/user/login", data, config);
        },

<<<<<<< HEAD
=======
        getUser: function (id) {
            return $http({
                method: 'GET',
                url: apiURL + '/user/' + id
            });
        },

>>>>>>> origin/master
        sendMessage: function (message) {
            var data = $.param({
                "content": message.content,
                "userFromId": message.userFromId,
                "userToId": message.userToId
            });
            var config = {headers: {'Content-Type': 'application/x-www-form-urlencoded'}};
            return $http.post(apiURL + "/user/message/new", data, config);
        },

        inbox: function (userId) {
            return $http.get(apiURL + "/user/message/received/" + userId);
        },

        outbox: function (userId) {
            return $http.get(apiURL + "/user/message/sent/" + userId);
        },

        toggleMessageState: function (messageId, state) {
            var data = $.param({
                "state": state
            });
            var config = {headers: {'Content-Type': 'application/x-www-form-urlencoded'}};
            return $http.post(apiURL + "/user/message/update/" + messageId, data, config);
        },

        getMessage: function (messageId) {
            return $http.get(apiURL + "/user/message/" + messageId);
        },

        getUser: function (id) {
            return $http({
                url: apiURL + '/user/' + id,
                method: 'GET'
            });
        },

        editUser: function (user) {
            return $http({
                url: apiURL + '/user/update/' + user.id,
                method: 'POST',
                headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                data: $.param({
                    name: user.name,
                    surname: user.surname,
                    birthDate: user.birthDate,
                    email: user.email,
                    username: user.username,
                    password: user.password
                })
            });
        }
    }

}]);