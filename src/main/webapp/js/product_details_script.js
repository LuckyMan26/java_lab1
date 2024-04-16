
function setCurrentGoodParam(id) {
    var url = new URL(window.location.href);
    url.searchParams.set('good_id', id);
    window.history.replaceState({}, '', url);
}
async function displayProductDetails(product) {

    hideAllFragments("product-details");

    var goodDetailsElement = document.getElementById('goodDetails');
    goodDetailsElement.innerHTML = "";


    var good = {
        name: product.name,
        price: product.price,
        description: product.description,
        imageData: product.imageData
    };
    //console.log("displayGoodDetails");

    const productDiv = document.createElement('div');
    const textContent = document.createElement('div');
    productDiv.classList.add('product-details');
    textContent.classList.add('product-text')
    const image = document.createElement('img');
    image.src = "data:image/jpeg;base64," + product.imageData ;

    image.alt = product.name;
    image.classList.add('product-image');
    productDiv.appendChild(image);

    const productName = document.createElement('span');
    productName.textContent = 'Product Name: ' + product.name;
    textContent.appendChild(productName);

    const price = document.createElement('span');
    price.textContent = 'Price: $' + product.price;
    const btn = document.createElement('button');
    btn.addEventListener("click", function (){
        addToCart(product);
    })
    btn.textContent = "Add to Cart";
    btn.classList.add("add-to-cart-button");
    textContent.appendChild(price);
    textContent.appendChild(btn);
    productDiv.appendChild(textContent);
    goodDetailsElement.appendChild(productDiv);
    setCurrentGoodParam(product.product_id);


    window.product_id = product.product_id;
    //console.log(window.product_id);
    const good_id = window.product_id;
    await sendGoodId(good_id);

}



async function fetchReviews(){
    fetch('/GetReviews')
        .then(response =>  response.json())
        .then(data => {
            renderExistingReviews(data);
        })
        .catch(error => console.error('Error:', error));
}
async function sendGoodId(id){
    //console.log("id ", id)
    const data = {
        command: 'good_id',
        good_id: id
    };

    fetch('/GetReviews', {
        method: 'POST',

        body: JSON.stringify(data)
    })
        .then(function(response) {
            //console.log(response);
            fetchReviews();
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
        });
}


function renderExistingReviews(reviews) {
    //console.log(reviews);
    const reviewsList = document.getElementById("reviewsList");
    reviewsList.innerHTML = "";

    reviews.forEach(review => {
        const listItem = document.createElement("div");

        // Create a container for the review content
        const reviewContent = document.createElement("div");
        reviewContent.classList.add("review-content");

        // Display the author's full name
        const authorName = document.createElement("div");
        authorName.classList.add("author-name");
        authorName.textContent = "Artem Volyk";
        authorName.style.fontSize = "12px"; // Smaller font size
        authorName.style.color = "#888"; // Shade of gray

        // Display the review grade as stars
        const ratingStars = document.createElement("div");
        ratingStars.classList.add("rating-stars");
        for (let i = 0; i < review.stars; i++) {
            const starIcon = document.createElement("span");
            starIcon.innerHTML = "&#9733;"; // Star symbol
            ratingStars.appendChild(starIcon);
        }

        // Display the review text
        const reviewText = document.createElement("div");
        reviewText.textContent = review.text;

        // Append all elements to the review content container
        reviewContent.appendChild(authorName);
        reviewContent.appendChild(ratingStars);
        reviewContent.appendChild(reviewText);

        // Append the review content container to the list item
        listItem.appendChild(reviewContent);

        // Append the list item to the reviews list
        reviewsList.appendChild(listItem);
    });
}


function getSelectedStars() {
    // Get all star elements
    var stars = document.querySelectorAll('.rating  .radio-btn');

    // Initialize counter for selected stars
    var selectedStars = 0;

    // Iterate over star elements
    stars.forEach(function(star) {
        // Check if the star is checked
        if (star.checked) {
            console.log("here");
            console.log(star.value);
            // Increment the counter if the star is checked
            selectedStars =  star.value;
        }
    });

    // Return the number of selected stars
    return selectedStars;
}
function addReview(){
    const stars = document.querySelectorAll('.rating input[type="radio"]');
    let selectedRating = getSelectedStars();


    var text = document.getElementById('review').value;

    document.getElementById('review').value = '';
    stars.forEach(function(star) {
        // Check if the star is checked
        if (star.checked) {
            star.checked = false;
        }
    });


    var formData = new FormData();
    formData.append('text', text);
    formData.append('rating', parseFloat(selectedRating));
    formData.append('client_id', 16);
    formData.append('good_id', window.product_id);


    fetch('/AddReview', {
        method: 'POST',

        body: formData
    })
        .then(function(response) {
            //console.log(response);
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
        });
}

