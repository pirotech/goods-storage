var goods = angular.module('goods', []);

var goodsCtrl = goods.controller('goodsCtrl', function ($scope, $http) {
    $http.get('http://localhost:8080/api/goods/').then(function (response) {
        $scope.goods = response.data != null ? response.data : [];
    });
    $scope.editGoods = function (id) {
        console.log(id);
    };
    $scope.deleteGoods = function (id) {
        console.log(id);
    };
});