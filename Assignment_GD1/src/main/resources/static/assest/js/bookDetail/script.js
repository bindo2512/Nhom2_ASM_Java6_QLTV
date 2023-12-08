app.controller("book-detail-controller", function($scope, $http) {
    $scope.commentform = {},
    $scope.commentlist = [],
    $scope.bookid = $("#bookid").text(),
    

    $scope.comment = {
		init() {
            console.log($scope.bookid)
            var bookid = $scope.bookid
			$http.get(`/rest/comments/${bookid}`).then(resp => {
			$scope.commentlist = resp.data;
            console.log(resp.data)
        })
		},

        addComment(){
            console.log($scope.bookid)
            var bookid = $scope.bookid
            var item = angular.copy($scope.commentform)
            $http.post(`/rest/comments/${bookid}`, item).then(resp => {
                $scope.commentlist.push(resp.data)
            })
        },

        pager: {
            page: 0,
            size: 10,
            get items() {
                var start = this.page * this.size;
                return $scope.commentlist.slice(start, start + this.size);
            },
            get count() {
                return Math.ceil(1.0 * $scope.commentlist.length / this.size); 
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
    $scope.comment.init();
    console.log($scope.bookid);
})