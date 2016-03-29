/**
 * Created by milew on 23.2.2016.
 */
WPAngularStarter.controller('ListingController', ['$scope', 'ListingService', function ($scope, ListingService) {

    $scope.createListing = function () {
        ListingService.save($scope.title, $scope.content, $scope.myFile).then(function () {
        }, function () {
            console.log("error creating the listing!!");
        });
    };

}]);