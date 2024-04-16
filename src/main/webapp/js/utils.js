function hideAllFragments(name){
    document.getElementById('itemsInCart').style.display = 'none';
    document.getElementById('home').style.display = 'none';
    document.getElementById('product-details').style.display = 'none';
    document.getElementById('history-of-orders').style.display = 'none';
    document.getElementById('orders').style.display = 'none';

    document.getElementById(name).style.display = 'block';

}
const compareDates = (a, b) => {
    return new Date(b.order_date) - new Date(a.order_date);
};
function getElementFromCart(product_id){
    let res;
    console.log(cartItems);
    cartItems.forEach(element => {

        if(element.product_id.toString()===product_id.toString()){

            res = element;
        }
    });
    return res
}
function parseDate(dateString) {
    const months = {
        "янв.": 0, "февр.": 1, "мар.": 2, "апр.": 3, "мая": 4, "июн.": 5,
        "июл.": 6, "авг.": 7, "сент.": 8, "окт.": 9, "нояб.": 10, "дек.": 11
    };

    const [monthStr, day, year] = dateString.split(' ');
    const month = months[monthStr];

    return new Date(year, month, day.replace(',', ''));
}
const countDuplicates = (arr) => {
    // Let's use a JavaScript object to keep track of counts
    const counts = {};

    // Loop through each element in the array
    arr.forEach((value) => {

        // If the value is encountered for the first time, set the count to 1
        if (!counts[value.product_id]) {
            counts[value.product_id] = 1;
        } else {
            // If the value has been seen before, increment the count
            counts[value.product_id]++;
        }
    });
    const res = [];
    Object.keys(counts).forEach((object) =>{
        res.push({product: getElementFromCart(object), quantity: counts[object]});
        });

    return res;
};

function setCurrentLocation(location_name) {
    var url = new URL(window.location.href);
    url.searchParams.set('location', location_name);
    window.history.replaceState({}, '', url);

}
function clearSearchParams() {
    var url = new URL(window.location.href);

    // Get a copy of all search parameters
    var searchParamsCopy = new URLSearchParams(url.search);

    // Loop through each search parameter in the copy and delete it
    searchParamsCopy.forEach((value, key) => {
        url.searchParams.delete(key);
    });

    window.history.replaceState({}, '', url);

    console.log("Search parameters cleared");
}
