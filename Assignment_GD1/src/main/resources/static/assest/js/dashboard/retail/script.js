app.controller("retail-dashboard-ctrl", function($http, $scope) {
    $scope.loadData = function() {
        $http.get("/rest/dashboard/retail/retailByMonth").then(resp => {
            var data = resp.data;
            var labels = data.map(function(item) {
                return "Tháng " + item.retailMonth
            });
            var chartData = data.map(function(item) {
                return item.retailCount
            });
            $scope.retailByMonthChart(chartData, labels)
        })
        $http.get("/rest/dashboard/retail/retailByUsername").then(resp => {
            var data = resp.data;
            var labels = data.map(function(item) {
                return "Tên tài khoản " + item.username
            })
            var chartData = data.map(function(item) {
                return item.numberOfRetail
            })
            $scope.retailByUsername(chartData, labels)
        })
        $http.get("/rest/dashboard/retail/retailByUsernameInMonth").then(resp => {
            var data = resp.data;
            var labels = data.map(function(item) {
                return "Tên tài khoản " + item.username
            })
            var chartData = data.map(function(item) {
                return parseInt(item.numberOfRetail)
            })
            $scope.retailByUsernameInMonth(chartData, labels)
        })
        $http.get("/rest/dashboard/retail/retailByBookname").then(resp => {
            var data = resp.data;
            var labels = data.map(function(item) {
                return item.bookname
            })
            var chartData = data.map(function(item) {
                return item.numberOfRetail
            })
            $scope.retailByBookname(chartData, labels)
        })
    }
    $scope.retailByMonthChart = function(chartData, labels) {
        var ctx = document.getElementById('retailByMonth').getContext('2d');
        new Chart(ctx, {
            type: 'line',
            data: {
                labels: labels,
                datasets: [{
                    label: 'Số đơn mượn sách theo tháng',
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
    $scope.retailByUsername = function(chartData, labels) {
        var ctx = document.getElementById('retailByUsername').getContext('2d');

        new Chart(ctx, {
          type: 'bar',
          data: {
            labels: labels,
            datasets: [{
              label: 'Số đơn mượn sách theo tài khoản',
              data: chartData,
              borderWidth: 1
            }]
          },
          options: {
            scales: {
              y: {
                beginAtZero: true
              }
            }
          }
        })
    }
    
    $scope.retailByUsernameInMonth = function(chartData, labels) {
        var ctx = document.getElementById("retailByUsernameInMonth").getContext("2d")
        new Chart(ctx, {
            type: 'doughnut',
            data: {
                labels: labels, 
                datasets: [{
                    label: "Số đơn đặt mượn sách của người dùng trong tháng",
                    data: chartData,
                    backgroundColor: [
                        'rgb(255, 99, 132)',
                        'rgb(54, 162, 235)',
                        'rgb(255, 205, 86)'
                      ],
                    hoverOffset: 1
                }]
            }
        })
    }
    $scope.retailByBookname = function(chartData, labels) {
        var ctx = document.getElementById("retailByBookname").getContext("2d")
        new Chart(ctx, {
            type: 'doughnut',
            data: {
                labels: labels, 
                datasets: [{
                    label: "Số đơn đặt mượn sách tính theo đầu sách",
                    data: chartData,
                    backgroundColor: [
                        'rgb(255, 99, 132)',
                        'rgb(54, 162, 235)',
                        'rgb(255, 205, 86)',
                        'rgb(67, 75, 86)',
                        'rgb(86, 168, 179)',
                        'rgb(84, 130, 130)',
                        'rgb(135,206,250)',
                        'rgb(65,105,225)'
                      ],
                    hoverOffset: 1
                }]
            }
        })
    }
    $scope.loadData()
})