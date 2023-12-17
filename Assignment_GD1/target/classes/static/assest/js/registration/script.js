app.controller("register-ctrl", function($scope, $http) {
    $scope.image = "not_available.png";
    $scope.password;
    $scope.repassword;
    $scope.isPasswordMatch = false;
    $scope.form = {
        username: "",
    };
    $scope.username;

    $scope.checkPasswordMatch = function () {
        $scope.isPasswordMatch = ($scope.password === $scope.repassword);
    };


    $scope.imageChange = function(files) {
        var data = new FormData();
        data.append('file', files[0]);
        $http.post('/rest/upload/image/users', data, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        }).then(resp => {
            alert("Thay ảnh đại diện thành công")
            $scope.image = resp.data
        }).catch(error => {
            alert("Lỗi update file hình ảnh");
            console.log(error);
        })
    }
})