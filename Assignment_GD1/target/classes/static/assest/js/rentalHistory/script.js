app.controller("rental-history-ctrl", function($scope, $http){
    $scope.rentals = [];
    $scope.retaildetail = [];
    $scope.username = $("#username").text()
    $scope.init = function() {
        $http.get("/rest/rental/username/" + $scope.username).then(resp =>{
            $scope.rentals = resp.data;
        })
    },
    $scope.watch = function(item) {
        $http.get("/rest/rental/id/" + item.retailid).then(resp => {
            $scope.retaildetail = resp.data;
        })
    },
    $scope.init();
})