app.controller("book-dashboard-ctrl", function($scope, $http) {
    $scope.loadData = function() {
        $http.get("/rest/dashboard/book/countbookbymonth").then(resp => {
            var data = resp.data;
            var labels = data.map(function(item) {
                return "Tháng " + item.month
            })
            var chartData = data.map(function(item) {
                return item.numberOfAdded
            })
            $scope.bookCreateByMonth(chartData, labels)
        })
    }
    $scope.bookCreateByMonth = function(chartData, labels) {
        var ctx = document.getElementById('createPer').getContext('2d');
        new Chart(ctx, {
            type: 'line',
            data: {
                labels: labels,
                datasets: [{
                    label: 'Số lượng sách mới theo tháng',
                    data: chartData,
                    fill: false,
                    borderColor: 'rgb(75, 192, 192)',
                    tension: 0.1
                }]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });
    }
    $scope.loadData()
})