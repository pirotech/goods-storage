var goods = angular.module('goods', []);

var goodsCtrl = goods.controller('goodsCtrl', function ($scope, $http) {
    // util
    var l = window.location;
    $scope.api = l.protocol + '//' + l.host + '/api/goods/';
    $scope.categoriesApi = l.protocol + '//' + l.host + '/api/categories/';
    $scope.loadCategories = function () {
        $http.get($scope.categoriesApi).then(function (response) {
            $scope.categories = response.data;
        });
    };

    // load
    $scope.loadGoods = function () {
        $http.get($scope.api).then(function (response) {
            $scope.goods = response.data != null ? response.data : [];
        });
    };

    // edit
    $scope.editorMode = false;
    $scope.editedNameError = false;
    $scope.editGoods = function (goods) {
        $scope.editorMode = true;
        $scope.edited = goods;
        $scope.editedNameError = false;
    };
    $scope.saveGoods = function () {
        $http.patch($scope.api + $scope.edited.id, {
            name: $('#name' + $scope.edited.id).val(),
            description: $('#description' + $scope.edited.id).val(),
            category: {
                id: $('#category-id' + $scope.edited.id).val()
            },
            price: $('#price' + $scope.edited.id).val(),
            count: $('#count' + $scope.edited.id).val()
        }).then(function () {
            $scope.loadGoods();
            $scope.editorMode = false;
            $scope.editedNameError = false;
        }, function () {
            $scope.editedNameError = true;
        })
    };
    $scope.cancelGoods = function () {
        $scope.editorMode = false;
        $scope.editedNameError = false;
    };

    // delete
    $scope.deleteGoods = function (category) {
        $scope.deleted = category;
    };
    $scope.reallyDeleteGoods = function () {
        $http.delete($scope.api + $scope.deleted.id).then(function () {
            $scope.loadGoods();
        });
    };

    // create
    $scope.created = {};
    $scope.createGoods = function () {
        var emptyName = $scope.created.name === '' || $scope.created.name === undefined;
        if (emptyName) {
            $scope.createdNameError = true;
        } else {
            $http.post($scope.api, $scope.created).then(function () {
                $scope.createdNameError = false;
                $scope.loadGoods();
                $scope.created = {};
            }, function () {
                $scope.createdNameError = true;
            });
        }
    };

    $scope.loadGoods();
    $scope.loadCategories();
});