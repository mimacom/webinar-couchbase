'use strict';

var isDefined = angular.isDefined;

/**
 * controller to manage the votes of the participants
 */

pollsApp
    .controller('votesCtrl', function ($scope, $routeParams, pollService, participantService, $location, notify) {

        //noinspection JSUnresolvedVariable
        var pollId = $routeParams.pollId;
        $scope.isAdmin = $routeParams.isAdmin;

        init();

        $scope.isMax = function (vote) {
            return $scope.popular.votes === vote.total;
        };

        $scope.closePoll = function () {
            if ($scope.popular.options.length > 1) {
                $scope.showSelect = true;
            } else {
                $scope.selectedOption = $scope.popular.options[0];
                pollService.closePoll(pollId, $scope.selectedOption).then(function () {
                    $scope.isReadonly = true;
                }, function (data) {
                    console.log('Exception', data);
                    notify({message: 'An unexpected error has occurred.', classes: 'alert-danger'});
                });
            }
        };

        $scope.setOption = function (opt) {
            $scope.selectedOption = opt;
        };

        $scope.save = function () {
            participantService.saveParticipant(pollId, $scope.participant).then(function (participantId) {
                $location.path('/poll/' + pollId + '/participant/' + participantId + '/thanks');
            }, function (data) {
                console.log('Exception', data);
                notify({message: 'An unexpected error has occurred.', classes: 'alert-danger'});
            });
        };

        function init() {
            pollService.getPollById(pollId).then(function (data) {
                $scope.poll = data;
                $scope.isReadonly = data.closed;
                $scope.participant = {votes: populateVotes($scope.poll.options)};
                count();
            }, function (data) {
                console.log('Exception', data);
                notify({message: 'An unexpected error has occurred.', classes: 'alert-danger'});
            });
            participantService.getParticipants(pollId).then(function (data) {
                $scope.participants = data;
            }, function (data) {
                console.log('Exception', data);
                notify({message: 'An unexpected error has occurred.', classes: 'alert-danger'});
            });

        }

        function populateVotes(options) {
            var votes = [];
            for (var i = 0; i < options.length; i++) {
                votes.push({option: options[i]});
            }
            return votes;
        }

        function count() {
            $scope.votes = populateVotes($scope.poll.options);
            $scope.popular = {votes: 0};
            angular.forEach($scope.votes, function (value, key) {
                participantService.getVotesForOption(pollId, value.option).then(function (data) {
                    $scope.votes[key].total = parseInt(data);
                    if (parseInt(data) > $scope.popular.votes) {
                        $scope.popular.votes = parseInt(data);
                        $scope.popular.options = [value.option];
                    } else if (isDefined($scope.popular.options) && parseInt(data) === $scope.popular.votes) {
                        $scope.popular.options.push(value.option);
                    }
                }, function (data) {
                    console.log('Exception', data);
                    notify({message: 'An unexpected error has occurred.', classes: 'alert-danger'});
                });
            });
        }
    });