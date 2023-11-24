app.controller("admin-book-ctrl", function($scope, $http){
    $scope.books = [];
    $scope.categories = [];
    $scope.bname = [];
    $scope.authors = [];
    $scope.publishers = [];
    $scope.issuer = [];
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
        $http.get("/rest/bname").then(resp => {
            $scope.bname = resp.data;
        })
        $http.get("/rest/authors").then(resp => {
            $scope.authors = resp.data;
        })
        $http.get("/rest/publishers").then(resp => {
            $scope.publishers = resp.data;
        })
        $http.get("/rest/issuer").then(resp => {
            $scope.issuer = resp.data;
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
    $scope.pager = {
        page: 0,
        size: 10,
        get items(){
            var start = this.page * this.size;
            return $scope.books.slice(start, start + this.size);
        },
        get count() {
            return Math.ceil(1.0 * $scope.books.length / this.size); 
        },
        first () {
            this.page = 0;
        },
        prev() {
            this.page--;
        },
        next() {
            this.page++;
        },
        last() {
            this.page = this.count - 1;
        }
    }
    $scope.init();

})