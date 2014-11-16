'use strict';

/* Controllers */

var isDefined = angular.isDefined;

pollsApp.controller('WelcomeCtrl', function ($scope, $location) {

    $scope.start = function () {
        $location.path('poll');
    };

    $scope.viewPolls = function () {
        $location.path('polls/' + $scope.userName);
    };
})
    .controller('PollCtrl', function ($scope, pollService, $location, $routeParams) {

        var pollId = $routeParams.pollId;

        $scope.createPoll = function () {
            pollService.createPoll($scope.poll).then(function (id) {
                $location.path('poll/' + id + '/options');
            });
        };

        if (isDefined(pollId)) {
            pollService.getPollById(pollId).then(function (data) {
                $scope.poll = data;
                $scope.poll.options = ["", "", "", "", ""];
            });
        }

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
    })
    .controller('VotesCtrl', function ($scope, $routeParams, pollService, participantService, $location, notify) {

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
    })
    .controller('ThanksCtrl', function ($scope, $routeParams, $location, participantService, notify) {

        var pollId = $routeParams.pollId;
        participantService.getById(pollId, $routeParams.participantId).then(function (data) {
            $scope.participant = data;
        }, function (data) {
            console.log('Exception', data);
            notify({message: 'An unexpected error has occurred.', classes: 'alert-danger'});
        });

        $scope.back = function () {
            $location.path('/poll/' + pollId + '/fill/');
        };
    })
    .controller('PollsCtrl', function ($scope, $routeParams, $location, notify, pollService) {
        $scope.userName = $routeParams.username;

        pollService.getPolls($scope.userName).then(function (data) {
            $scope.polls = data;
        });
    });

