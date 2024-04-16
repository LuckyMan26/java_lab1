function showBasketItems(){
    hideAllFragments("itemsInCart");

    console.log("showBasketItems");
    displayItems(cartItems);
    console.log(cartItems);
}

function displayItems(data){
    const container = document.getElementById('itemsInCartContainer');
    const textContent = document.createElement('div');
    container.innerHTML = '';
    data.forEach(item => {
        displayOneProduct(item, container);
    });
    // Display the checkout section after items are loaded
    document.getElementById('checkoutSection').style.display = 'block';
    let price =0;
    for(let i =0; i < cartItems.length; i++){
        price+=cartItems[i].price;
    }
    let currentDate = new Date();
    document.getElementById('totalPrice').textContent = price + "$";
    document.getElementById('orderDate').textContent = currentDate.toLocaleDateString();
}
function displayConfirmation(cost) {
    return new Promise((resolve, reject) => {
        const confirmationDialog = document.getElementById("confirmationDialog");

        const confirmBtn = document.getElementById("confirmBtn");
        const cancelBtn = document.getElementById("cancelBtn");
        const price = document.getElementById('total-price');
        confirmationDialog.appendChild(price);
        confirmBtn.addEventListener("click", () => {
            resolve();
            confirmationDialog.style.display = "none";
        });

        cancelBtn.addEventListener("click", () => {
            reject("Purchase canceled by user");
            confirmationDialog.style.display = "none";
        });

        confirmationDialog.style.display = "block";

    });
}

function buy() {
    let price =0;
    for(let i =0; i < cartItems.length; i++){
        price+=cartItems[i].price;
    }
    displayConfirmation(price)
        .then(() => {
            // User confirmed, proceed with purchase logic
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
            cartItems = [];
            cartItemCount = 0;


        })
        .catch((error) => {
            // User canceled the purchase
            console.log(error);
        });
}