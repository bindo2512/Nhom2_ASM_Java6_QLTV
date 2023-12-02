app.controller("register-ctrl", function($scope, $http) {
    $scope.imageChange = function(files) {
        var data = new FormData();
        data.append('file', files[0]);
        $http.post('/rest/upload/image/users', data, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        }).then(resp => {
            alert("Thay ảnh đại diện thành công")
        }).catch(error => {
            alert("Lỗi update file hình ảnh");
            console.log(error);
        })
    }
})