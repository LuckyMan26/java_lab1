<%--
  Created by IntelliJ IDEA.
  User: ArtemPC
  Date: 20.03.2024
  Time: 15:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Hello!</h1>
<a href="/goods">Click here</a>
</body>
<script>
    document.addEventListener("DOMContentLoaded", function() {
        // Execute this code when the DOM is ready

        // Make a GET request to the server endpoint
        fetch('/')
            .then(response => {
                // Check if the response is successful
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                // Parse the JSON body of the response
                return response.json();
            })
            .then(data => {
                // Process the data received from the server
                console.log(data);
                // You can manipulate or display the data here
            })
            .catch(error => {
                // Handle errors that may occur during the request
                console.error('There was a problem with the fetch operation:', error);
            });
    });
</script>
</html>
