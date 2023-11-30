app.controller("user-management-ctrl", function($scope, $http){
    $scope.rentals = [];
    $scope.retaildetail = [];
    $scope.userForm = {};
    $scope.username = $("#username").text()
    $scope.formEnabled = false;
    
    $scope.init = function() {
        console.log(username)
        $http.get("/rest/rental/username/" + $scope.username).then(resp =>{
            console.log(resp.data)
            $scope.rentals = resp.data;
        }).catch(error => {
            console.log(error)
        })
        $http.get("/rest/account/" + $scope.username).then(resp => {
            console.log(resp.data)
            $scope.userForm = resp.data;
        }).catch(error =>  {
            console.log(error)
        })
    },

    $scope.toggleForm = function() {
        $scope.formEnabled = !$scope.formEnabled;
    };

    $scope.watch = function(item) {
        $http.get("/rest/rental/id/" + item.retailid).then(resp => {
            $scope.retaildetail = resp.data;
        })
    },
    
    $scope.init();
})