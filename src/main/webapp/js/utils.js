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
function parseDate(dateString) {
    const months = {
        "янв.": 0, "февр.": 1, "мар.": 2, "апр.": 3, "мая": 4, "июн.": 5,
        "июл.": 6, "авг.": 7, "сент.": 8, "окт.": 9, "нояб.": 10, "дек.": 11
    };

    const [monthStr, day, year] = dateString.split(' ');
    const month = months[monthStr];

    return new Date(year, month, day.replace(',', ''));
}