'use strict';

/* Controllers */

pollsApp.controller('WelcomeCtrl', function ($scope, $location) {

    $scope.start = function () {
        $location.path('poll');
    };
})
    .controller('PollCtrl', function ($scope, pollService, $location, $routeParams) {

        var pollId = $routeParams.pollId;

        $scope.createPoll = function () {
            pollService.createPoll($scope.userData).then(function (id) {
                $location.path('poll/' + id +'/options');
            });
        };

        $scope.poll = {
            id: pollId,
            options: ["","","","",""]
        };

        $scope.addOption = function () {
            $scope.poll.options.push("");
        };

        $scope.removeOption = function (index) {
            $scope.poll.options.splice(index, 1);
        };

        $scope.saveOptions = function () {

            pollService.savePoll($scope.poll).then(function () {
                $location.path('/poll/' + pollId + '/share');
            });
        };

        $scope.pollUrl = 'http://localhost:8080/polls/#/poll/' + pollId + '/fill';

        $scope.finishPoll = function () {
            $location.path('/');
        };
    });

