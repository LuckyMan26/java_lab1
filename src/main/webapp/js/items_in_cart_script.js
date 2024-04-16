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
function displayConfirmation(cost) {
    return new Promise((resolve, reject) => {
        const confirmationDialog = document.getElementById("confirmationDialog");

        const confirmBtn = document.getElementById("confirmBtn");
        const cancelBtn = document.getElementById("cancelBtn");
        const price = document.createElement('div');
        price.textContent = "Total price: " + cost + "$";
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