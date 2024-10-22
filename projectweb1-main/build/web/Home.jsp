<%-- 
    Document   : Home
    Created on : Oct 10, 2024, 11:18:51 PM
    Author     : ADMIN
--%>

<%@page import="dto.Item"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Trang Chu</title>
         <style>
            .product-container {
                width: 200px;
                float: left;
                margin: 10px;
                padding: 10px;
                border: 1px solid #ddd;
                text-align: center;
            }
            .product-container img {
                width: 150px;
                height: 150px;
            }
            .no-product {
                color: red;
                font-size: 20px;
                font-weight: bold;
                text-align: center;
                margin-top: 20px;
            }
        </style>
    </head>
    <body>
        <h1>Welcome to Coffee & Bakery Store</h1>
        <p><a href="#">HOME</a> |
        <a href="loginpage.jsp">LOGIN</a> |
        <a href="searchItem.jsp">Search</a> |
        <a href="register.jsp">Register</a> |
        <a href="ViewCart.jsp">View Cart</a> |
        <a href="ViewWishlistServlet">Wishlist</a> |
           <form action="SearchServlet">
               <input type="text" name="txtsearch" value="<%= (request.getParameter("msg"))!=null?(request.getParameter("msg")):""%>"/>
               <input type="submit" value="Go"/>
           </form>
        <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Price</th>
            <th>Image</th>
            <th>Actions</th>
        </tr>
        <%
            ArrayList<Item> itemList = (ArrayList<Item>) request.getAttribute("itemList");
            if (itemList != null && !itemList.isEmpty()) {
                for (Item item : itemList) {
        %>
        <tr>
            <td><%= item.getItemId() %></td>
            <td><%= item.getItemName() %></td>
            <td><%= item.getPrice() %></td>
            <td><img src="<%= item.getImage() %>" width="100" height="100"/></td>
            <td>
                <a href="editItem.jsp?itemId=<%= item.getItemId() %>">Edit</a>
                <a href="deleteItem?id=<%= item.getItemId() %>">Delete</a>
            </td>
        </tr>
        <%
                }
            } else {
        %>
        <tr><td colspan="5">No items available.</td></tr>
        <%
            }
        %>
    </table>
        
        
    <!-- ------------------------------------------ -->
        
    </body>
</html>
