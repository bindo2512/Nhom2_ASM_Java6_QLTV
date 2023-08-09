app.controller("admin-rental-controller", function($http, $scope) {
    $scope.rentals = [];
    $scope.rentalsdetail = [];
    $scope.init = function() {
        $http.get("/rest/rental").then(resp => {
            $scope.rentals = resp.data;
        })
    }
})