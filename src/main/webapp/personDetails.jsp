<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.yourcompanyname.model.Person" %>
<%@ page import="com.yourcompanyname.util.PersonDAO" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Person Details</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<style>
    body {
        font-family: "Times New Roman", Times, serif;
        background-color: #f7f7f7; /* Light grey background color */
        margin: 0;
        padding: 0;
    }
    .container {
        max-width: 800px;
        margin: 20px auto;
        padding: 20px;
        background-color: #fff; /* White background color */
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }
    h2 {
        text-align: center;
        margin-bottom: 30px;
        color: #333; /* Dark grey color */
    }
    table {
        width: 100%;
        border-collapse: collapse;
        margin-bottom: 20px;
        background-color: #fff; /* White background color */
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }
    th, td {
        padding: 12px;
        text-align: left;
        border-bottom: 1px solid #ddd;
    }
    th {
        background-color: #f2f2f2; /* Light grey background color */
    }
    tr:hover {
        background-color: #f5f5f5; /* Light grey hover color */
    }
    .add-person-form {
        background-color: #fff; /* White background color */
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }
    .form-group {
        margin-bottom: 15px;
    }
    .form-group label {
        display: block;
        font-weight: bold;
        margin-bottom: 5px;
        color: #333; /* Dark grey color */
    }
    .form-group input[type="text"],
    .form-group input[type="email"] {
        width: 100%;
        padding: 10px;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
    }
    .form-group input[type="file"] {
        margin-top: 5px;
    }
    .form-group input[type="submit"] {
        width: 100%;
        background-color: #4CAF50; /* Green background color */
        color: white;
        padding: 10px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        font-size: 16px;
    }
    .form-group input[type="submit"]:hover {
        background-color: #45a049; /* Darker green hover color */
    }
</style>
</head>
<body>

<div class="container">
    <h2>Person Details</h2>

    <% 
        // Retrieve person details from database
        List<Person> persons = PersonDAO.getAllPersons();
    %>

    <% if (persons != null && !persons.isEmpty()) { %>
        <table>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Document</th>
                <th>Photo</th>
            </tr>
            <% for (Person person : persons) { %>
                <tr>
                    <td><%= person.getId() %></td>
                    <td><%= person.getName() %></td>
                    <td><%= person.getEmail() %></td>
                    <td><img src="<%= person.getDocumentPath() %>" width="100" height="100"></td>
                    <td><img src="<%= person.getPhotoPath() %>" width="100" height="100"></td>
                </tr>
            <% } %>
        </table>
    <% } else { %>
        <p>No persons found.</p>
    <% } %>

    <h2>Add New Person</h2>
    <form action="AddPersonServlet" method="post" enctype="multipart/form-data" class="add-person-form">
        <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" required>
        </div>
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>
        </div>
        <div class="form-group">
            <label for="document">Document:</label>
            <input type="file" id="document" name="document">
        </div>
        <div class="form-group">
            <label for="photo">Photo:</label>
            <input type="file" id="photo" name="photo">
        </div>
        <div class="form-group">
            <input type="submit" value="Add Person">
        </div>
    </form>
</div>

</body>
</html>
