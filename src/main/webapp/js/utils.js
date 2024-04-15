function hideAllFragments(name){
    document.getElementById('itemsInCart').style.display = 'none';
    document.getElementById('home').style.display = 'none';
    document.getElementById('product-details').style.display = 'none';
    document.getElementById('history-of-orders').style.display = 'none';
    document.getElementById('orders').style.display = 'none';

    document.getElementById(name).style.display = 'block';

}