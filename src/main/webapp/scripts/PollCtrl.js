'use strict';

var isDefined = angular.isDefined;

/**
 * controller to manage a poll: initial creation & options definition
 */

pollsApp
.controller('pollCtrl', function ($scope, pollService, $location, $routeParams, $filter) {

        //noinspection JSUnresolvedVariable
        var pollId = $routeParams.pollId;

        $scope.pollUrl = 'http://localhost:8080/polls/#/poll/' + pollId + '/fill';

        $scope.createPoll = function () {
            pollService.createPoll($scope.poll).then(function (id) {
                $location.path('poll/' + id + '/options');
            });
        };

        $scope.init = function () {
            if (isDefined(pollId)) {
                pollService.getPollById(pollId).then(function (data) {
                    $scope.poll = data;
                    $scope.poll.options = ["", "", ""];
                });
            }
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

        $scope.finishPoll = function () {
            $location.path('/');
        };

    });