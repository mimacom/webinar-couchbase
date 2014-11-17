'use strict';

/* App Module */

var pollsApp = angular.module('pollsApp', [
    'ngResource', 'ngRoute', 'cgNotify']);

pollsApp
    .config(function ($routeProvider) {
        $routeProvider.when('/', {templateUrl: 'views/welcome.html', controller: 'welcomeCtrl'});
        $routeProvider.when('/poll', {templateUrl: 'views/poll.html', controller: 'pollCtrl'});
        $routeProvider.when('/poll/:pollId/options', {templateUrl: 'views/options.html', controller: 'pollCtrl'});
        $routeProvider.when('/poll/:pollId/share', {templateUrl: 'views/share.html', controller: 'pollCtrl'});
        $routeProvider.when('/poll/:pollId/fill', {templateUrl: 'views/votes.html', controller: 'votesCtrl'});
        $routeProvider.when('/poll/:pollId/participant/:participantId/thanks', {templateUrl: 'views/thanks.html', controller: 'thanksCtrl'});
        $routeProvider.when('/polls/:username', {templateUrl: 'views/polls.html', controller: 'pollsCtrl'});
        $routeProvider.otherwise('/');
        });
