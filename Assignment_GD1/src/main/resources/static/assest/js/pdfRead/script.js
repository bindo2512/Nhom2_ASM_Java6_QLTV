app.controller("pdf-read-controller", function($scope) {
    $scope.scale = 1.35;
    pdfjsLib.GlobalWorkerOptions.workerSrc = 'https://cdn.jsdelivr.net/npm/pdfjs-dist@2.7.570/build/pdf.worker.min.js';
    const pdfLink = document.getElementById("pdfLink").href;
    var pdfDoc = pdfjsLib.getDocument(pdfLink).promise
    var pageNum = 1;
    console.log(pdfLink);
    function renderPage(num, canvasId, scalepage) {
        pdfjsLib.getDocument(pdfLink).promise.then(function(pdf) {
            pdf.getPage(num).then(function(page) {
                var scale = scalepage;
                var viewport = page.getViewport({scale: scale});
        
                // Prepare canvas using PDF page dimensions
                var canvas = document.getElementById(canvasId);
                var context = canvas.getContext("2d");
                canvas.height = viewport.height;
                canvas.width = viewport.width;
        
                // Render PDF page into canvas context
                var renderContext = {
                    canvasContext: context,
                    viewport: viewport
                };
                page.render(renderContext);
            });
        })
    }
    $scope.goToNextPage = function() {
        if (pageNum >= pdfDoc.numPages) {
            console.log("Reached the end of the document");
            return;
        }
        if (pageNum == pdfDoc.numPages - 1) {
            pageNum++;
            renderPage(pageNum, "pdfFile1",$scope.scale);
        } else {
            pageNum = pageNum + 2;
            renderPage(pageNum, "pdfFile1",$scope.scale);
            renderPage(pageNum + 1, "pdfFile2",$scope.scale);
        }
    };
    

    $scope.goToPreviousPage = function() {
        if (pageNum <= 1) {
            console.log("Reached the start of the document");
            return;
        }
        if (pageNum == 2) {
            pageNum--;
            renderPage(pageNum, "pdfFile1",$scope.scale);
        } else {
            pageNum = pageNum - 2;
            renderPage(pageNum, "pdfFile1",$scope.scale);
            renderPage(pageNum + 1, "pdfFile2",$scope.scale);
        }
    };
    $scope.updateScale = function() {
        // Ensure that scale is a valid number
        if (!isNaN(parseFloat($scope.scale)) && isFinite($scope.scale)) {
            renderPage(pageNum, "pdfFile1",$scope.scale);
            renderPage(pageNum + 1, "pdfFile2",$scope.scale);
        } else {
            // Handle the case when the input is not a valid number
            // You can display an error message or take other actions here
        }
    };
    renderPage(pageNum, "pdfFile1",$scope.scale);
    renderPage(pageNum+1, "pdfFile2",$scope.scale);
});
