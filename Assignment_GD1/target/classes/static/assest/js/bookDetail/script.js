app.controller("book-detail-controller", function($scope, $http) {
    $scope.commentform = {},
    $scope.commentlist = [],
    $scope.bookid;
    $scope.comment = {
		init() {
			$http.get("/rest/comments").then(resp => {
			$scope.commentlist = resp.data;
            console.log($scope.bookid)
        })
		}
	}
    $scope.comment.init();
})