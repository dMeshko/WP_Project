/**
 * Created by Dell on 16-Mar-16.
 */

<<<<<<< HEAD
WPAngularStarter.controller('loginController', ['$scope', 'notifications',  '$state', 'UserService', "$rootScope", "adminURL", function($scope, notifications,  $state, UserService, $rootScope, adminURL){
=======
WPAngularStarter.controller('loginController', ['$scope', 'notifications', '$state', 'UserService', "$rootScope", "adminURL", function($scope, notifications, $state, UserService, $rootScope, adminURL){
>>>>>>> origin/master

    $scope.username = '';
    $scope.password = '';

    $scope.save = function(){
        if($scope.username != '' && $scope.password != '') {

            var data = $.param({
                "username": $scope.username,
                "password": $scope.password
            });

            var config = {
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }
            };

            UserService.loginUser(data, config).success(function(data){
                if(data){
                    $('#err-message').removeClass('show');
                    $scope.createCookie("userId", data.id, 1); //add the current user cookie
                    $scope.createCookie("userIsAdmin", data.isAdmin, 1); //is he admin?
                    $scope.createCookie("userName", data.username, 1); //add the current user cookie
                    $rootScope.isLoggedIn = true;
                    $rootScope.isAdmin = data.isAdmin;
                    $rootScope.userName = data.username;
<<<<<<< HEAD
=======
                    notifications.showSuccess(data.username + ', добредојде назад!');
>>>>>>> origin/master
                    if (data.isAdmin == true)
                        window.location.href = adminURL;
                    else{
                        $state.go("home");
                    }
                }else{
<<<<<<< HEAD
                    notifications.showSuccess({message: 'Your task posted successfully'});
=======
                    notifications.showError('Настана грешка. Не успеавме да ве најавиме.');
>>>>>>> origin/master
                    $('#err-message').addClass('show');
                }

            }).error(function(){
<<<<<<< HEAD
=======
                notifications.showError('Настана грешка. Не успеавме да ве најавиме.');
>>>>>>> origin/master

            });

        }
    };

    $scope.signup = function(){

        $state.go("sign-up");
    };

    $scope.cancel = function(){

        $scope.username = '';
        $scope.password = '';

        $state.go("home");
    };
}]);
