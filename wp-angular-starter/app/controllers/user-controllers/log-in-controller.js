/**
 * Created by Dell on 16-Mar-16.
 */

WPAngularStarter.controller('loginController', ['$scope', 'notifications',  '$state', 'UserService', "$rootScope", "adminURL", function($scope, notifications,  $state, UserService, $rootScope, adminURL){

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
                    if (data.isAdmin == true)
                        window.location.href = adminURL;
                    else{
                        $state.go("home");
                    }
                }else{
                    notifications.showSuccess({message: 'Your task posted successfully'});
                    $('#err-message').addClass('show');
                }

            }).error(function(){

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
