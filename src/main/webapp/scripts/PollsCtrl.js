'use strict';

pollsApp
    .controller('pollsCtrl', function ($scope, $routeParams, $filter, pollService) {
        $scope.userName = $routeParams.username;

        pollService.getPolls($scope.userName).then(function (data) {
            $scope.polls = $filter('orderBy')(data, 'createdOn', true);
        });
    });

