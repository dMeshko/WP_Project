/**
 * Created by Mile on 03/13/2016.
 */
WPAngularStarter.controller('ViewListingsController', function ($scope, ListingService) {
    $scope.usersPerPage = 9;
    $scope.currentPage = 1;

    ListingService.query().then(function (response) {
        $scope.listings = response.data;
        /*$scope.pages = $scope.listings.length / 9;
        if($scope.listings.length > 9){
            $scope.pages += 1;
        }*/
    });
});
