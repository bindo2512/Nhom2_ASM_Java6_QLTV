app.controller("account-admin-ctrl", function($scope, $http) {
    $scope.account = [];
    $scope.form = {};
    $scope.init = function() {
        $http.get("/rest/account").then(resp => {
            $scope.account = resp.data;
        })
    }
    $scope.init();
})