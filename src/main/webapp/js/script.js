var currentPage = 1;
var itemsPerPage = 15;
window.onload =  async function () {
    var res = null;

    currentPage = getParameterByName('page');
    if (currentPage === null || isNaN(currentPage)) {
        currentPage = 1;
    } else {
        currentPage = parseInt(currentPage);
    }
    await fetchBasket();
    await fetchData();

    document.getElementById('loadMoreButton').addEventListener('click', loadMoreGoods);
    document.getElementById('previousPageButton').addEventListener('click', previousPage);

};





    function hideBasketItems() {
    var basketItemsContainer = document.getElementById('basketItemsContainer');
    basketItemsContainer.classList.remove('show'); // Hide the container
}

    function displayGoods(products) {
        var goodsGridElement = document.getElementById('goodsGrid');

        // Clear existing content
        goodsGridElement.innerHTML = '';

        // Calculate start and end index for current page
        var startIndex = (currentPage - 1) * itemsPerPage;
        var endIndex = currentPage * itemsPerPage;

        // Loop through the list of goods and create div containers
        for (var i = startIndex; i < Math.min(endIndex, products.length); i++) {
            var product = products[i];
            const div = document.createElement('div');
            div.classList.add('good');


            div.innerHTML = '<div class="good-image"><img src="data:image/jpeg;base64,' + product.imageData + '" alt="' + product.name + '"></div>' +
                '<div class="good-details">' +
                '<div class="good-name">' + product.name + '</div>' +
                '<div class="good-price">$' + product.price + '</div>' +
                '</div>';
            (function (product) {
                // Add event listener to call "foo" method when div is clicked and pass the good object
                div.addEventListener('click', function () {
                    console.log('here');
                    displayProductDetails(product);
                });
            })(product);
            goodsGridElement.appendChild(div);
        }

        // Show or hide the load more button based on pagination
        var loadMoreButton = document.getElementById('loadMoreButton');
        if (endIndex < products.length) {
            loadMoreButton.style.display = 'block';
        } else {
            loadMoreButton.style.display = 'none';
        }

        // Enable or disable the previous page button based on current page
        var previousPageButton = document.getElementById('previousPageButton');
        if (currentPage > 1) {
            previousPageButton.disabled = false;
        } else {
            previousPageButton.disabled = true;
        }
    }

    function loadMoreGoods() {
    currentPage++;
    setCurrentPageParam(currentPage);
    fetchData();
}

    function previousPage() {
    if (currentPage > 1) {
    currentPage--;
    setCurrentPageParam(currentPage);
    fetchData();
}
}

     function fetchData() {
    fetch('/GoodServlet')
        .then(response =>  response.json())
        .then(data => {
            displayGoods(data);
        })
        .catch(error => console.error('Error:', error));
}

    function addItem() {
    // Retrieve values from form
    var itemName = document.getElementById('itemName').value;
    var itemPrice = document.getElementById('itemPrice').value;
    var itemQuantity = document.getElementById('itemQuantity').value;
    var itemDescription = document.getElementById('itemDescription').value;
    var fileInput = document.getElementById('fileUploader');
    var file = fileInput.files[0];
    console.log(itemName, itemPrice,itemQuantity, itemDescription);
    var formData = new FormData();
    formData.append('name', itemName);
    formData.append('price', parseFloat(itemPrice));
    formData.append('quantity', parseInt(itemQuantity));
    formData.append('description', itemDescription);
    formData.append('file', file);


    fetch('/AddGood', {
    method: 'POST',

    body: formData
    })
    .then(function(response) {
    console.log(response);
    if (!response.ok) {
    throw new Error('Network response was not ok');
}
});
    $('#successMessage').addClass('show');
    setTimeout(function() {
    $('#successMessage').removeClass('show');
}, 3000); // Hide after 3 seconds

    // Fade out the success message after a delay
    setTimeout(function() {
    $('#successMessage').css('opacity', 0);
}, 2000); // Fade out over 2 seconds, adjust as needed
    $('#addItemModal').modal('hide');


    document.getElementById('addItemForm').reset();

}

    function getParameterByName(name, url) {
    if (!url) url = window.location.href;
    name = name.replace(/[\[\]]/g, "\\$&");
    var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
    results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, " "));
}

    // Function to set the current page URL parameter
    function setCurrentPageParam(pageNumber) {
    var url = new URL(window.location.href);
    url.searchParams.set('page', pageNumber);
    window.history.replaceState({}, '', url);
}



    var fileInput = document.getElementById('fileUploader');
    var fileDropArea = document.getElementById('fileDropArea');

    // Prevent default behavior (open file in browser)
    fileDropArea.addEventListener('dragover', function(e) {
        e.preventDefault();
        fileDropArea.classList.add('dragover');
    });

    // Handle drag leave
    fileDropArea.addEventListener('dragleave', function(e) {
        e.preventDefault();
        fileDropArea.classList.remove('dragover');
    });

    // Handle file drop
    fileDropArea.addEventListener('drop', function(e) {
        e.preventDefault();
        fileDropArea.classList.remove('dragover');
        var files = e.dataTransfer.files;
        handleFiles(files);
    });

    // Handle file selection from input
    fileInput.addEventListener('change', function(e) {
        var files = e.target.files;
        handleFiles(files);
    });


    function handleFiles(files) {
        var fileList = files;

        console.log(fileList);
    }