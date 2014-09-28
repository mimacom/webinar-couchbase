'use strict';

/* App Module */

var pollsApp = angular.module('pollsApp', [
    'ngResource', 'ngRoute']);

pollsApp
    .config(function ($routeProvider, $httpProvider) {
        $routeProvider.when('/', {templateUrl: 'views/welcome.html', controller: 'WelcomeCtrl'})
        $routeProvider.when('/poll', {templateUrl: 'views/poll.html', controller: 'PollCtrl'})
        $routeProvider.when('/poll/:pollId/options', {templateUrl: 'views/options.html', controller: 'PollCtrl'})
        $routeProvider.when('/poll/:pollId/share', {templateUrl: 'views/share.html', controller: 'PollCtrl'})
            .otherwise('/');
        });
