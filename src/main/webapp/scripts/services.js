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
        getPoll: function (username) {
            return $http.get('rest/poll/' + username).then(function (res) {
                return res.data;

            });
        }
    };
});