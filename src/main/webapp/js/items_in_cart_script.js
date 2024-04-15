function showBasketItems(){
    hideAllFragments("itemsInCart");

    console.log("showBasketItems");
    displayitems(cartItems);
    console.log(cartItems);
}

function displayitems(data){
    const container = document.getElementById('itemsInCartContainer');
    const textContent = document.createElement('div');
    container.innerHTML = '';
    data.forEach(item => {
        displayOneProduct(item,container);

    });
}
function buy() {
    // Add your logic for buying here
    let currentDate = new Date();
    const data = {

        products: cartItems,
        date: currentDate,
        client_id: 16,
    };

    fetch('/MakeOrder', {
        method: 'POST',

        body: JSON.stringify(data)
    })
        .then(function(response) {

            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
        });
    alert("Buying functionality to be implemented!");
}