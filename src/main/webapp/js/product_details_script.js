
function setCurrentGoodParam(id) {
    var url = new URL(window.location.href);
    url.searchParams.set('good_id', id);
    window.history.replaceState({}, '', url);
}
async function displayProductDetails(product) {


    var goodDetailsElement = document.getElementById('goodDetails');
    var wrapper = document.getElementById("product-details");
    wrapper.style.display='block';
    var good = {
        name: product.name,
        price: product.price,
        description: product.description,
        imageData: product.imageData
    };
    console.log("displayGoodDetails");
    goodDetailsElement.querySelector('img').src = "data:image/jpeg;base64, " + good.imageData;
    goodDetailsElement.querySelector('h2').textContent = good.name;
    goodDetailsElement.querySelector('p:nth-of-type(1)').textContent = 'Price: $' + good.price;
    goodDetailsElement.querySelector('p:nth-of-type(2)').textContent = 'Description: ' + good.description;
    goodDetailsElement.querySelector('button').addEventListener('click', function () {
        addToCart(product);
    });
    setCurrentGoodParam(product.product_id);
    goodDetailsElement.style.display = 'block';
    document.getElementById("home").style.display = 'none';
    window.product_id = product.product_id;
    console.log(window.product_id);
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
    console.log("id ", id)
    const data = {
        command: 'good_id',
        good_id: id
    };

    fetch('/GetReviews', {
        method: 'POST',

        body: JSON.stringify(data)
    })
        .then(function(response) {
            console.log(response);
            fetchReviews();
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
        });
}


function renderExistingReviews(reviews) {
    console.log(reviews);
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
            console.log(response);
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
        });
}