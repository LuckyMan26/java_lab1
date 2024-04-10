window.onload = function () {
    $(document).ready(function() {
        $("#loadSecondJSPF").click(function() {
            // AJAX call to load the second JSPF file
            $.ajax({
                url: "product-details.jspf",
                success: function(result) {
                    // Inject the content of second.jspf into the dynamicContent div
                    $("#dynamicContent").html(result);
                }
            });
        });
    });
    fetch('/GetReviews')
        .then(response => response.json())
        .then(data => {
            displayGoods(data);
        })
        .catch(error => console.error('Error:', error));
};


    function submitReview() {
    const rating = document.querySelector('input[name="rating"]:checked');
    const reviewText = document.getElementById("reviewText").value;

    if (rating === null) {
    alert("Please select a rating!");
    return;
}

    // For demonstration purposes, let's just log the review
    console.log("Rating: " + rating.value);
    console.log("Review Text: " + reviewText);

    // You can submit this data to your backend or handle it as needed
    // For simplicity, we're just logging it here
}
