'use strict';

/* Services */
pollsApp.factory('pollService', function ($http) {
    return {
        createPoll: function (poll) {
            return $http.post('rest/poll', poll).then(function(res){
                return res.data;
            });
        },
        savePoll: function (poll) {
            return $http.put('rest/poll/' + poll.id, poll);
        },
        getPolls: function (username) {
            return $http.get('rest/poll/username/' + username).then(function (res) {
                return res.data;
            });
        },
        getPollById: function (pollId) {
            return $http.get('rest/poll/' + pollId).then(function (res) {
                return res.data;

            });
        },
        closePoll: function(pollId, selectedOption){
            return $http.put('rest/poll/' + pollId + '/close', selectedOption);
        }
    };
})
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