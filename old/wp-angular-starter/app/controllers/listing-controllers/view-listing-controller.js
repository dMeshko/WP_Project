/**
 * Created by Mile on 03/13/2016.
 */
WPAngularStarter.controller('ViewListingController', function ($scope, UserService, ListingService, $stateParams, serverURL, toastr) {
    $scope.serverURL = serverURL;
    $scope.showReport = false;
    $scope.showMessage = false;
    $scope.report = {
        "listingId": -1,
        "content": ""
    };
    $scope.message = {
        "userToId": -1,
        "content": ""
    };


    ListingService.queryListing($stateParams.id).then(function (response) {
        $scope.listing = response.data;
        $scope.report.listingId = response.data.id;
        $scope.message.userToId = response.data.user.id;
    });

    $scope.toggleReportModal = function(){
        if ($scope.showReport == false) //reset the content when opening the report
            $scope.report.content = "";
        $scope.showReport = !$scope.showReport;
    };

    $scope.toggleMessageModal = function(){
        if ($scope.showReport == false) //reset the content when opening the report
            $scope.message.content = "";
        $scope.showMessage = !$scope.showMessage;
    };

    $scope.reportPost = function (){
        ListingService.reportPost($scope.report).then(function (){
            toastr.success("Постот е успешно пријавен и истиот ќе биде разгледан од администраторите.  Ви благодариме.");
        }, function (){
            toastr.error("упсс, настана грешка..работиме на средување на истата!!");
            console.log("error reporting the post!!");
        });
        $scope.toggleReportModal();
    };

    $scope.sendMessage = function (){
        UserService.sendMessage($scope.message).then(function (){
            toastr.success("Пораката е успешно испратена!!");
        }, function (){
            toastr.error("упсс, настана грешка..работиме на средување на истата!!");
            console.log("error sending the message!!")
        });
        $scope.toggleMessageModal();
    };

    $scope.deleteListing = function() {
        ListingService.delete($scope.listing.id).then(function(response){
        });
    };
});