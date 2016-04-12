/**
 * Created by Mile on 03/13/2016.
 */

WPAngularStarter.controller('ViewListingController', function ($sce, $scope, $state, UserService, ListingService, $stateParams, serverURL, NgMap, notifications) {
    $scope.serverURL = serverURL;
    $scope.showReport = false;
    $scope.showMessage = false;
    $scope.showDelete = false;
    $scope.isAuthor = false;
    $scope.report = {
        "userFromId": -1,
        "listingId": -1,
        "content": ""
    };
    $scope.message = {
        "userFromId": -1,
        "userToId": -1,
        "content": ""
    };
    $scope.googleMapsUrl = "https://maps.googleapis.com/maps/api/js?libraries=places&key=AIzaSyDMyT0hlU4u9mJGaokKUHEDqnkHdt369eA&v=3.exp";


    ListingService.queryById($stateParams.id).then(function (response) {
        $scope.listing = response.data;
        $scope.listing.content = $sce.trustAsHtml($scope.listing.content);
        $scope.report.listingId = response.data.id;
        $scope.report.userFromId = $scope.userId;
        $scope.message.userToId = response.data.user.id;
        $scope.message.userFromId = $scope.userId;
        if (response.data.user.id == $scope.userId)
            $scope.isAuthor = true;

        //the map
        NgMap.getMap().then(function(map) {
            var location = {lat: parseFloat(response.data.locations[0].lat), lng: parseFloat(response.data.locations[0].lng)};
            map.setCenter(location);
            map.setZoom(15);

            console.log(map.getCenter());
            console.log('markers', map.markers);
            console.log('shapes', map.shapes);
        });
    });

    $scope.toggleReportModal = function () {
        if ($scope.showReport == false) //reset the content when opening the report
            $scope.report.content = "";
        $scope.showReport = !$scope.showReport;
    };

    $scope.toggleMessageModal = function () {
        if ($scope.showReport == false) //reset the content when opening the report
            $scope.message.content = "";
        $scope.showMessage = !$scope.showMessage;
    };

    $scope.reportPost = function () {
        ListingService.reportPost($scope.report).then(function () {
            notifications.showWarning({message: 'Огласот е пријавен и истиот ќе биде разгледан од администраторите. Ви благодариме.'});
        }, function () {
            notifications.showError({message: 'Настана грешка. Огласот не е пријавен.'});
        });
        $scope.toggleReportModal();
    };

    $scope.sendMessage = function () {
        UserService.sendMessage($scope.message).then(function () {
            notifications.showSuccess({message: 'Пораката е пратена!'});
        }, function () {
            notifications.showSuccess({message: 'Настана грешка. Пораката не е пратена.'});
        });
        $scope.toggleMessageModal();
    };

    ListingService.isOwner($stateParams.id).then(function (response) {
        $scope.show = response.data;
    });

    $scope.editListing = function () {
        $state.go('edit-listing', {listing: $scope.listing});
    };

    $scope.confirmDelete = function () {
        $scope.showDelete = !$scope.showDelete;
    };

    $scope.deleteListing = function () {
        $scope.confirmDelete();

        setTimeout(function () {
            ListingService.delete($scope.listing.id).then(function () {
                notifications.showSuccess({message: 'Огласот е успешно избришан!'});
                $state.go('view-listings');
            }, function(){
                notifications.showError({message: 'Настана грешка. Огласот не е избришан.'});
            })
        }, 300);
    };
});