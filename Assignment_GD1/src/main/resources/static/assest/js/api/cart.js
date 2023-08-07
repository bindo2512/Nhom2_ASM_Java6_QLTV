const app = angular.module("library-app", []);

app.controller("library-ctrl", function($scope, $http){
    alert("HERE WE GO")
    $scope.cart = {
        items: [],
        add(bookid) {
            alert(bookid)
        },
    }
})