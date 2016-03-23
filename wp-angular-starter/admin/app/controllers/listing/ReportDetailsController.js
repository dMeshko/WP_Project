/**
 * Created by Darko on 3/20/2016.
 */

WPAngularStarter.controller("ReportDetailsController", ["$scope", "$state", "$stateParams", "ListingService", "serverURL", function ($scope, $state, $stateParams, ListingService, serverURL) {
    $scope.report = {};
    $scope.serverURL = serverURL;

    ListingService.getReport($stateParams.id).then(function (response) {
        $scope.report = response.data;
        if ($scope.report.seen == false) {
            ListingService.updateReport($scope.report.id, true).then(function () {
                $scope.updateUnreadCount();
            }, function () {
                console.log("error updating the report state!!");
            });
        }
    }, function () {
        console.log("error fetching report data!!");
    });

    $scope.deleteListing = function (id){
        ListingService.deleteListing(id).then(function (){
            $state.go("app.reports");
        }, function (){
            console.log("error occured while attempting to remove the listing!!");
        });
    };
}]);