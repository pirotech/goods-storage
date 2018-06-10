var categories = angular.module('categories', []);

var categoriesCtrl = categories.controller('categoriesCtrl', function ($scope, $http) {
    $http.get('http://localhost:8080/api/categories/').then(function (response) {
        $scope.categories = response.data != null ? response.data : [];
        console.log(response.data);
    });
    $scope.editCategory = function (id) {
        console.log(id);
    };
    $scope.deleteCategory = function (id) {
        console.log(id);
    };
});