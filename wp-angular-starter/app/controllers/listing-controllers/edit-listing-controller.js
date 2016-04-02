/**
 * Created by Mile on 03/27/2016.
 */
WPAngularStarter.controller('EditListingController', function ($sce, $scope, serverURL, ListingService, $stateParams, $state) {
    $scope.listing = $stateParams.listing;
    $scope.serverURL = serverURL;

    $scope.editListing = function () {
        $scope.listing.content = $sce.valueOf($scope.listing.content);
        ListingService.editListing($scope.listing).then(function () {
            $state.go('view-listing', {id: $scope.listing.id});
        },
        function(){
            $state.go('view-listing', {id: $scope.listing.id});
            window.alert('Настана грешка. Огласот не е изменет.');
        });
    }
});