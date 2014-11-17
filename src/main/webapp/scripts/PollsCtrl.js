'use strict';

pollsApp
    .controller('pollsCtrl', function ($scope, $routeParams, $location, notify, pollService) {
        $scope.userName = $routeParams.username;

        pollService.getPolls($scope.userName).then(function (data) {
            $scope.polls = data;
        });
    });

