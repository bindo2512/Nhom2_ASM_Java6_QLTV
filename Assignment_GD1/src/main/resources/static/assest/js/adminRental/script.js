app.controller("admin-rental-ctrl", function($scope,$http) {
    $scope.rentals = [];
    $scope.rentalsdetail = [];
    $scope.orderstate = [];
    $scope.currentDate = new Date();
    $scope.form = {};
    $scope.init = function() {
        $http.get("/rest/rental").then(resp => {
            $scope.rentals = resp.data;
        }).catch(error => {
            console.log(error)
        });
        $http.get("/rest/orderstate").then(resp => {
            $scope.orderstate = resp.data;
        })
    }

    $scope.edit = function(item) {
        $scope.form = angular.copy(item);
    },

    $scope.update = function() {
        var item = angular.copy($scope.form);
        $http.put('/rest/rental/${item.retailid}', item).then(resp => {
            var index = $scope.rentals.findIndex(r => r.id == item.id);
            $scope.rentals[index] = item;
            $scope.init();
        }).catch(error => {
            console.log(error);
        })
    }

    $scope.init();
})