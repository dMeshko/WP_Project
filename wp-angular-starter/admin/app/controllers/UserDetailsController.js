/**
 * Created by Darko on 2/28/2016.
 */

WPAngularStarter.controller("UserDetailsController", ["$scope", "$stateParams", "toastr", "UserService", "serverURL", function ($scope, $stateParams, toastr, UserService, serverURL){
    $scope.user = {};
    $scope.serverURL = serverURL;

    UserService.getUser($stateParams.id).then(function (response){
        $scope.user = response.data;
    }, function (){
        toastr.error("Error retrieving user details!!");
    });


}]);