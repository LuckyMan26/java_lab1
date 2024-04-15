
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
        const listItem = document.createElement("li");
        listItem.innerHTML =  '<strong>' + review.text + '</strong>';
        reviewsList.appendChild(listItem);
    });
}



function addReview(){
    var text = document.getElementById('review').value;
    var rating = document.getElementById('rating').value;


    var formData = new FormData();
    formData.append('text', text);
    formData.append('rating', parseFloat(rating));
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