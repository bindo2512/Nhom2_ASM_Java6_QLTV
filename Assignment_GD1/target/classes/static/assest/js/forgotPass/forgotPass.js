app.controller("forgot-part-ctrl", function($scope, $interval, $timeout, $http) {
    $scope.message = "",
    $scope.countdown = 300,
    $interval(function() {
        if ($scope.countdown > 0) {
            $scope.countdown--;
        } else {
            $http.get("/rest/forgot").then(resp => {
                $scope.message = resp.data;
            })
        }
    }, 1000)
});

app.filter('secondsToTimeFormat', function() {
    return function(seconds) {
        var min = Math.floor(seconds / 60);
        var sec = seconds % 60;
        return min + ':' + (sec < 10 ? '0' + sec : sec);
    };
});