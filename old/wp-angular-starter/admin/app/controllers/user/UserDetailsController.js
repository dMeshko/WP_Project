/**
 * Created by Darko on 2/28/2016.
 */

WPAngularStarter.controller("UserDetailsController", ["$scope", "$stateParams", "toastr", "UserService", "ListingService", "serverURL", function ($scope, $stateParams, toastr, UserService, ListingService, serverURL){
    $scope.user = {};
    $scope.listings = [];
    $scope.serverURL = serverURL;

    UserService.getUser($stateParams.id).then(function (response){
        $scope.user = response.data;
    }, function (){
        toastr.error("Error retrieving user details!!");
    });

    ListingService.getListingsByUser($stateParams.id).then(function (response){
        $scope.listings = response.data;
    }, function (){
        toastr.error("Error retrieving the listings for this user!!");
    });
}]);