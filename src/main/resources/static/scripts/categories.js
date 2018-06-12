var categories = angular.module('categories', []);

var categoriesCtrl = categories.controller('categoriesCtrl', function ($scope, $http) {
    // util
    var l = window.location;
    $scope.api = l.protocol + '//' + l.host + '/api/categories/';
    $scope.goodsApi = l.protocol + '//' + l.host + '/api/goods/';

    // load
    $scope.loadCategories = function() {
        $http.get($scope.api).then(function (response) {
            $scope.categories = response.data != null ? response.data : [];

            $scope.categories.forEach(function (element) {
                element.goodsCount = 0;
            });
            $http.get($scope.goodsApi).then(function (responseGoods) {
                var goods = responseGoods.data;
                var categories = $scope.categories;
                for (var i = 0; i < goods.length; ++i) {
                    for (var j = 0; j < categories.length; ++j) {
                        var category = categories[j];
                        if (goods[i].category.id === category.id) {
                            category.goodsCount++;
                        }
                    }
                }
            });
        });
    };

    // edit
    $scope.editorMode = false;
    $scope.editCategory = function (category) {
        $scope.editorMode = true;
        $scope.edited = category;
    };
    $scope.saveCategory = function () {
        $http.patch($scope.api + $scope.edited.id, {
            name: $('#name' + $scope.edited.id).val(),
            description: $('#description' + $scope.edited.id).val()
        }).then(function () {
            $scope.loadCategories();
            $scope.editorMode = false;
        });
    };
    $scope.cancelCategory = function () {
        $scope.editorMode = false;
    };

    // delete
    $scope.deleteCategory = function (category) {
        $scope.deleted = category;
    };
    $scope.reallyDeleteCategory = function () {
        $http.delete($scope.api + $scope.deleted.id).then(function () {
            $scope.loadCategories();
        });
    };

    // create
    $scope.created = {};
    $scope.createdNameError = false;
    $scope.createCategory = function () {
        var emptyName = $scope.created.name === '' || $scope.created.name === undefined;
        if (emptyName) {
            $scope.createdNameError = true;
        } else {
            $scope.createdNameError = false;
            $http.post($scope.api, $scope.created).then(function () {
                $scope.loadCategories();
                $scope.created = {};
            });
        }
    };

    $scope.loadCategories();
});