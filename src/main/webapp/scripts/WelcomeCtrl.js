'use strict';

pollsApp.controller('welcomeCtrl', function ($scope, $location) {

    $scope.start = function () {
        $location.path('poll');
    };

    $scope.viewPolls = function () {
        $location.path('polls/' + $scope.userName);
    };
});