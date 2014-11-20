'use strict';

/**
 * Services to manage poll objects
 */

pollsApp.factory('pollService', function ($http) {
    return {
        createPoll: function (poll) {
            return $http.post('rest/poll', poll).then(function (res) {
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
        closePoll: function (pollId, selectedOption) {
            return $http.put('rest/poll/' + pollId + '/close', selectedOption);
        }
    };
});

