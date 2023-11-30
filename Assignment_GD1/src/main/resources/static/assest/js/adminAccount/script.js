app.controller("account-admin-ctrl", function($scope, $http) {
    $scope.account = [];
    $scope.accountdetail = [];
    $scope.form = {
        image: "not_available.png",
        isadmin: true,
    };
    $scope.edit = function(item) {
        $scope.form = angular.copy(item);
        console.log($scope.form.accountdetail)
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
    $scope.imageChange = function(files) {
        var data = new FormData();
        data.append('file', files[0]);
        $http.post('/rest/upload/image/users', data, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        }).then(resp => {
            $scope.form.image = resp.data.name;
        }).catch(error => {
            alert("Lỗi update file hình ảnh");
            console.log(error);
        })
    }

    $scope.update = function() {
        var item = angular.copy($scope.form);
        $http.put("/rest/account/${item.username}", item).then(resp => {
            var index = $scope.account.findIndex(a => a.username == item.username);
            $scope.account[index] = item;
            alert("Update thành công")
        }).catch(error => {
            alert("Lỗi cập nhật");
            console.log(error);
        })
    }
    $scope.init();
})