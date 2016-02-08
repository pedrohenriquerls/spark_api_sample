var TOKEN = "37d759acc748941dd27c7eea06f7cbdb";

angular.module('sparksample').factory('employeeService', function($location, $http) {
    return {
        create: function(employee){
            employee.company_uuid = employee.company.company_uuid
            delete employee.company

            employee.token = TOKEN

            $http.post('/api/v1/companies/employee/create', employee).success(function (data) {
                alert(data.message)
                $location.path('/');
            }).error(function (data, status) {
                alert('Error ' + data)
            })
        }
    }
})

angular.module('sparksample').factory('companyService', function($location, $http) {
    return {
        create: function (company) {
            company.token = TOKEN

            $http.post('/api/v1/companies/create', company).success(function (data) {
                alert(data.message)
                $location.path('/');
            }).error(function (data, status) {
                alert('Error ' + data)
            })
        },
        edit: function (company) {
            delete company.employees
            company.token = TOKEN

            $http.put('/api/v1/companies/update', company).success(function (data) {
                alert(data.message)
                $location.path('/');
            }).error(function (data, status) {
                alert('Error ' + data)
            })
        },
        get: function(uuid, done){
            return $http.get('/api/v1/companies/'+uuid+'/details?token='+TOKEN)
                .success(done)
                .error(function (data, status) {
                    alert('Error ' + data.message)
                })
        },
        getAll: function(done){
            return $http.get('/api/v1/companies/list?token='+TOKEN)
                .success(done)
                .error(function (data, status) {
                    alert('Error ' + data.message)
                })
        }
    }
});