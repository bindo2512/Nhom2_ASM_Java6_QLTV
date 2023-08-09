app.controller("admin-book-ctrl", function($scope, $http){
    $scope.books = [];
    $scope.categories = [];
    $scope.form = {
        image: 'not_available.png'
    };
    $scope.init = function() {
        $http.get("/rest/book").then(resp => {
            $scope.books = resp.data;
        })  

        $http.get("/rest/categories").then(resp => {
            $scope.categories = resp.data;
        })
    },

    $scope.reset = function() {
        $scope.form = {
            available: true,
            image: 'not_available.png'
        }
    },
    $scope.edit = function(item) {
        $scope.form = angular.copy(item);
    },
    $scope.create = function() {
        var item = angular.copy($scope.form);
        $http.post('/rest/book', item).then(resp =>{
            $scope.books.push(resp.data);
            $scope.reset();
            alert("Thêm thành công");
        }).catch(error => {
            alert("Lỗi");
            console.log(error);
        })
    },
    $scope.delete = function() {},
    $scope.pdfChange = function(files){
        var data = new FormData();
        data.append('file', files[0]);
        $http.post('/rest/upload/pdf', data, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        }).then(resp => {
            $scope.form.image = resp.data.name;
        }).catch(error => {
            alert("Lỗi update file pdf");
            console.log(error);
        })
    },
    $scope.imageChange = function(files) {
        var data = new FormData();
        data.append('file', files[0]);
        $http.post('/rest/upload/image/products', data, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        }).then(resp => {
            $scope.form.image = resp.data.name;
        }).catch(error => {
            alert("Lỗi update file hình ảnh");
            console.log(error);
        })
    }
    $scope.init();

})