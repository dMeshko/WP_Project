/**
 * Created by Dell on 16-Mar-16.
 */

WPAngularStarter.controller('loginController', ['$scope', 'toastr', '$state', 'UserService', function($scope, toastr, $state, UserService){

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
                    toastr.success("successful logging in");
                    $state.go("home");
                }else{
                    toastr.error("error logging in");
                    $('#err-message').addClass('show');
                }

            }).error(function(){
                toastr.error("error logging in");

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
    }

}]);
