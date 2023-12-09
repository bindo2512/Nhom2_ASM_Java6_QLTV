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
        $http.get("/rest/dashboard/book/countreadbyname").then(resp => {
            var data = resp.data;
            var labels = data.map(function(item) {
                return item.bookname
            })
            var chartData = data.map(function(item) {
                return item.numberOfRead
            })
            $scope.countBookReadByBookname(chartData, labels)
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

    $scope.countBookReadByBookname = function(chartData, labels) {
        var ctx = document.getElementById('countRead').getContext('2d');
        new Chart(ctx, {
            type: 'polarArea',
            data: {
                labels: labels,
                datasets: [{
                    label: "Phân loại sách theo lượt đọc",
                    data: chartData,
                    backgroundColor: [
                        'rgb(255, 99, 132)',
                        'rgb(75, 192, 192)',
                        'rgb(255, 205, 86)',
                        'rgb(201, 203, 207)',
                        'rgb(54, 162, 235)'
                      ]
                }]
            }
        })
    }
    $scope.loadData()
})