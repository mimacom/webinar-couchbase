'use strict';

/* Services */
pollsApp
    .factory('participantService', function($http){
        return {
            saveParticipant: function (pollId, participant) {
                return $http.post('rest/poll/' + pollId + '/participant', participant).then(function (res){
                    return res.data;
                });
            },
            getParticipants: function(pollId){
                return $http.get('rest/poll/' + pollId + '/participants').then(function (res) {
                    return res.data;
                });
            },
            getVotesForOption: function(pollId, option){
                return $http.get('rest/poll/' + pollId + '/option/' + option + '/total').then(function (res) {
                    return res.data;
                });
            },
            getById: function (pollId, participantId){
                return $http.get('rest/poll/' + pollId + '/participant/' + participantId).then(function (res) {
                    return res.data;
                });
            }
        };

    });