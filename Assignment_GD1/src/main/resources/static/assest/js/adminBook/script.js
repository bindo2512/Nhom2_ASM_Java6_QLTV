app.controller("admin-book-ctrl", function($scope, $http){
    $scope.books = [];
    $scope.categories = [];
    $scope.bname = [];
    $scope.authors = [];
    $scope.publishers = [];
    $scope.issuer = [];
    $scope.form = {
        image: 'not_available.png',
        createdate: new Date(),

    };
    $scope.bnameform = {};
    $scope.authorsform = {
        authorimage: 'not_available.png',
    };
    $scope.publishersform = {
        publisherimage: 'not_available.png',
    };
    $scope.issuersform = {
        issuerimage: 'not_available.png',
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
 
    $scope.update = function() {
        var item = angular.copy($scope.form);
        $http.put(`/rest/book/${item.bookid}`, item).then(resp => {
            var index = $scope.books.findIndex(b => b.bookid == item.bookid);
            $scope.books[index] = item;
            alert("Update thành công")
        }).catch(error => {
            alert("Lỗi cập nhật");
            console.log(error);
        })
    }
    $scope.delete = function() {},
    $scope.pdfChange = function(files){
        var data = new FormData();
        data.append('file', files[0]);
        $http.post('/rest/upload/pdf', data, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        }).then(resp => {
            $scope.form.pdf = resp.data.name;
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

    $scope.bnamefunction = {
        createBname()  {
            var item = angular.copy($scope.bnameform);
            $http.post('/rest/bname', item).then(resp =>{
                $scope.bname.push(resp.data);
                $scope.reset();
                alert("Thêm thành công");
            }).catch(error => {
                alert("Lỗi");
                console.log(error);
            })
        },
        update() {
            var item = angular.copy($scope.bnameform);
            $http.put(`/rest/bname/${item.booknameid}`, item).then(resp => {
                var index = $scope.bname.findIndex(bn => bn.booknameid == item.booknameid);
                $scope.bname[index] = item;
                alert("Update thành công")
            }).catch(error => {
                alert("Lỗi cập nhật");
                console.log(error);
            })
        },

        reset() {
            $scope.bnameform = {}
        },

        edit(item) {
            $scope.bnameform = angular.copy(item);
        },
        pager: {
            page: 0,
            size: 5,
            get items() {
                var start = this.page * this.size;
                return $scope.bname.slice(start, start + this.size);
            },
            get count() {
                return Math.ceil(1.0 * $scope.bname.length / this.size); 
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
    }


    
    $scope.authorsfunction = {
        createAuthor()  {
            var item = angular.copy($scope.authorsform);
            $http.post('/rest/authors', item).then(resp =>{
                $scope.authors.push(resp.data);
                $scope.reset();
                alert("Thêm thành công");
            }).catch(error => {
                alert("Lỗi");
                console.log(error);
            })
        },
        edit(item) {
            $scope.authorsform = angular.copy(item);
        },
        update() {
            var item = angular.copy($scope.authorsform);
            $http.put(`/rest/authors/${item.authorid}`, item).then(resp => {
                var index = $scope.authors.findIndex(a => a.authorid == item.authorid);
                $scope.authors[index] = item;
                alert("Update thành công")
            }).catch(error => {
                alert("Lỗi cập nhật");
                console.log(error);
            })
        },


        reset() {
            $scope.authorsform = {
                authorimage: 'not_available.png'
            }
        },

        imageChangeAuthor(files) {
            var data = new FormData();
            data.append('file', files[0]);
            $http.post('/rest/upload/image/authors', data, {
                transformRequest: angular.identity,
                headers: {'Content-Type': undefined}
            }).then(resp => {
                console.log(resp.data.name)
                $scope.authorsform.authorimage = resp.data.name;
            }).catch(error => {
                alert("Lỗi update file hình ảnh");
                console.log(error);
            })
        },
        pager: {
            page: 0,
            size: 5,
            get items() {
                var start = this.page * this.size;
                return $scope.authors.slice(start, start + this.size);
            },
            get count() {
                return Math.ceil(1.0 * $scope.authors.length / this.size); 
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
    }

    $scope.publishersfunction = {
        createPublisher()  {
            var item = angular.copy($scope.publishersform);
            $http.post('/rest/publishers', item).then(resp =>{
                $scope.publishers.push(resp.data);
                $scope.reset();
                alert("Thêm thành công");
            }).catch(error => {
                alert("Lỗi");
                console.log(error);
            })
        },
        update() {
            var item = angular.copy($scope.pulishersform);
            $http.put(`/rest/pulishers/${item.publisherid}`, item).then(resp => {
                var index = $scope.publishers.findIndex(p => p.publisherid == item.publisherid);
                $scope.publishers[index] = item;
                alert("Update thành công")
            }).catch(error => {
                alert("Lỗi cập nhật");
                console.log(error);
            })
        },


        reset() {
            $scope.publishersform = {
                publisherimage: 'not_available.png'
            }
        },

        edit(item) {
            $scope.publishersform = angular.copy(item);
        },
        imageChangePublisher(files) {
            var data = new FormData();
            data.append('file', files[0]);
            $http.post('/rest/upload/image/publishers', data, {
                transformRequest: angular.identity,
                headers: {'Content-Type': undefined}
            }).then(resp => {
                console.log(resp.data.name)
                $scope.publishersform.publisherimage = resp.data.name;
            }).catch(error => {
                alert("Lỗi update file hình ảnh");
                console.log(error);
            })
        },
        pager: {
            page: 0,
            size: 5,
            get items() {
                var start = this.page * this.size;
                return $scope.publishers.slice(start, start + this.size);
            },
            get count() {
                return Math.ceil(1.0 * $scope.publishers.length / this.size); 
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
    }

    $scope.issuersfunction = {
        createIssuer()  {
            var item = angular.copy($scope.issuersform);
            $http.post('/rest/issuer', item).then(resp =>{
                $scope.issuer.push(resp.data);
                $scope.reset();
                alert("Thêm thành công");
            }).catch(error => {
                alert("Lỗi");
                console.log(error);
            })
        },

        reset() {
            $scope.form = {
                issuerimage: 'not_available.png'
            }
        },
        edit(item) {
            $scope.issuersform = angular.copy(item);
        },
        imageChangeIssuer(files) {
            var data = new FormData();
            data.append('file', files[0]);
            $http.post('/rest/upload/image/issuers', data, {
                transformRequest: angular.identity,
                headers: {'Content-Type': undefined}
            }).then(resp => {
                console.log(resp.data.name)
                $scope.issuersform.issuerimage = resp.data.name;
            }).catch(error => {
                alert("Lỗi update file hình ảnh");
                console.log(error);
            })
        },
        update() {
            var item = angular.copy($scope.issuersform);
            $http.put(`/rest/issuer/${item.issuerid}`, item).then(resp => {
                var index = $scope.issuer.findIndex(i => i.issuerid == item.issuerid);
                $scope.issuer[index] = item;
                alert("Update thành công")
            }).catch(error => {
                alert("Lỗi cập nhật");
                console.log(error);
            })
        },
        pager: {
            page: 0,
            size: 5,
            get items() {
                var start = this.page * this.size;
                return $scope.issuer.slice(start, start + this.size);
            },
            get count() {
                return Math.ceil(1.0 * $scope.issuer.length / this.size); 
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
    }
})