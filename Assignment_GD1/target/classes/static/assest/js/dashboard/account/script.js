app.controller("account-dashboard-ctrl", function($scope, $http){
    $scope.loadData = function() {
        $http.get("/rest/dashboard/account/registrationpermonth").then(resp => {
            var data = resp.data;
            var labels = data.map(function(item) {
                return "Tháng " + item.month
            })
            var chartData = data.map(function(item) {
                return item.numberOfRegistraion
            })
            $scope.registration(chartData, labels)
        })
        $http.get("/rest/dashboard/account/activeaccountspermonth").then(resp => {
            var data = resp.data;
            var labels = data.map(function(item) {
                return "Tháng " + item.month
            })
            var chartData = data.map(function(item) {
                return item.activeAccount
            })
            $scope.active(chartData, labels)
        })
    }
    $scope.registration = function(chartData, labels) {
        var ctx = document.getElementById('registrationPer').getContext('2d');
        new Chart(ctx, {
            type: 'line',
            data: {
                labels: labels,
                datasets: [{
                    label: 'Số lượt đăng ký tài khoản theo tháng',
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
    $scope.active = function(chartData, labels) {
        var ctx = document.getElementById("activePer").getContext("2d");
        new Chart(ctx, {
            type: 'bar',
            data: {
              labels: labels,
              datasets: [{
                label: 'Lượng tài khoản hoạt động theo tháng',
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
          });
    }
    $scope.loadData();
})