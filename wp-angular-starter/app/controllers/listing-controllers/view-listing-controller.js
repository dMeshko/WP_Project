/**
 * Created by Mile on 03/13/2016.
 */
WPAngularStarter.controller('ViewListingController', function ($scope, ListingService, $stateParams) {
    ListingService.queryListing($stateParams.id).then(function (response) {
        $scope.listing = response.data;
        $scope.images = response.data.imageURLs;
    });

    $scope.deleteListing = function() {
        ListingService.delete($scope.listing.id).then(function(response){
        });
    };
});