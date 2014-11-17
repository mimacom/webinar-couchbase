'use strict';

pollsApp
    .controller('thanksCtrl', function ($scope, $routeParams, $location, participantService, notify) {

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
    });

