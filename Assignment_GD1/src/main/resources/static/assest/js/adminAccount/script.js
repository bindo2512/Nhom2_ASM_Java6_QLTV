app.controller("account-admin-ctrl", function($scope, $http) {
    $scope.account = [];
    $scope.accountdetail = [];
    $scope.form = {
        image: "not_available.png",
        isadmin: true,
    };
    $scope.edit = function(item) {
        $scope.form = angular.copy(item);
    };
    $scope.init = function() {
        $http.get("/rest/account").then(resp => {
            $scope.account = resp.data;
            console.log(resp.data)
        })
        $http.get("/rest/accountdetail").then(resp => {
            $scope.accountdetail = resp.data;
            console.log(resp.data)
        })
    }
    $scope.init();
})