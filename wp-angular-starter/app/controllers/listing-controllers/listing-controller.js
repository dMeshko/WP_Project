/**
 * Created by milew on 23.2.2016.
 */
<<<<<<< HEAD
WPAngularStarter.controller('ListingController', function ($scope, $state, ListingService, notifications) {
=======
WPAngularStarter.controller('ListingController', function ($scope, notifications, $state, ListingService) {
>>>>>>> origin/master
    $scope.title = '';
    $scope.content = '';
    $scope.location = "";

    $scope.createListing = function () {
        if ($scope.title != '' && $scope.content != '') {
            ListingService.save($scope.title, $scope.content, $scope.myFile, $scope.userId, $scope.location).then(function (response) {
                $state.go('view-listing', {id: response.data.id});
<<<<<<< HEAD
                notifications.showSuccess({message: 'Огласот е креаиран!'});
=======
                notifications.showSuccess('Огласот е испратен!');
>>>>>>> origin/master
            }, function () {
                notifications.showError('Настана грешка. Огласот не е креиран.');
            });
        }
    };

    $scope.autocompleteOptions = {
        componentRestrictions: { country: 'mk' },
        types: ['geocode']
    }
});