'use strict';

/* App Module */

var pollsApp = angular.module('pollsApp', [
    'ngResource', 'ngRoute', 'cgNotify']);

pollsApp
    .config(function ($routeProvider) {
        $routeProvider.when('/', {templateUrl: 'views/welcome.html', controller: 'WelcomeCtrl'});
        $routeProvider.when('/poll', {templateUrl: 'views/poll.html', controller: 'PollCtrl'});
        $routeProvider.when('/poll/:pollId/options', {templateUrl: 'views/options.html', controller: 'PollCtrl'});
        $routeProvider.when('/poll/:pollId/share', {templateUrl: 'views/share.html', controller: 'PollCtrl'});
        $routeProvider.when('/poll/:pollId/fill', {templateUrl: 'views/votes.html', controller: 'VotesCtrl'});
        $routeProvider.when('/poll/:pollId/participant/:participantId/thanks', {templateUrl: 'views/thanks.html', controller: 'ThanksCtrl'});
        $routeProvider.when('/polls/:username', {templateUrl: 'views/polls.html', controller: 'PollsCtrl'});
        $routeProvider.otherwise('/');
        });
