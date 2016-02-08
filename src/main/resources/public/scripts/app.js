var app = angular.module('sparksample', [
    'ngCookies',
    'ngResource',
    'ngSanitize',
    'ngRoute'
]);

app.config(function ($routeProvider) {
    $routeProvider.when('/', {
        templateUrl: 'views/companies/list.html',
        controller: 'CompaniesListCtrl'
    }).when('/companies/create', {
        templateUrl: 'views/companies/create.html',
        controller: 'CompaniesCreateCtrl'
    }).when('/companies/:uuid/edit', {
        templateUrl: 'views/companies/edit.html',
        controller: 'CompaniesEditCtrl'
    }).when('/companies/employee/create', {
        templateUrl: 'views/employees/create.html',
        controller: 'EmployeeCreateCtrl'
    }).otherwise({
        redirectTo: '/'
    })
});

app.controller('CompaniesListCtrl', function ($scope, companyService) {
    companyService.getAll(function(data){
        $scope.companies = data.response
    })
});

app.controller('EmployeeCreateCtrl', function ($scope, employeeService, companyService) {
    $scope.employee = {}
    companyService.getAll(function(data){
        $scope.companies = data.response
    })

    $scope.create = function(){
        employeeService.create($scope.employee)
    }
});


app.controller('CompaniesCreateCtrl', function ($scope, companyService) {
    $scope.company = {}
    $scope.create = function(){
        companyService.create($scope.company)
    }
});

app.controller('CompaniesEditCtrl', function ($scope, companyService, $routeParams) {
    companyService.get($routeParams.uuid, function(data){
        var response = data.response
        $scope.company = response
    })

    $scope.edit = function(){
        companyService.edit($scope.company)
    }
});