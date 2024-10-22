<%-- 
    Document   : searchItem
    Created on : Oct 21, 2024, 3:10:23 PM
    Author     : Asus
--%>

<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Item" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Trang tìm kiếm sản phẩm</title>
    </head>
    <body>
        <!-- Form tìm kiếm -->
        <form action="SearchItemServlet" method="get">
            <input type="text" name="keyword" placeholder="Tìm món ăn hoặc thức uống..." required>
            <button type="submit">Tìm kiếm</button>
        </form>

        <hr />

        <% 
            // Lấy danh sách items từ request và keyword từ tham số
            List<Item> items = (List<Item>) request.getAttribute("items");
            String keyword = request.getParameter("keyword");
            
            // Kiểm tra nếu keyword khác null và danh sách items không rỗng
            if (keyword != null) {
                if (items != null && !items.isEmpty()) {
        %>
            <h3>Kết quả tìm kiếm:</h3>
            <table border="1">
                <tr>
                    <th>ID</th>
                    <th>Tên sản phẩm</th>
                    <th>Giá</th>
                    <th>Loại</th>
                    <th>Trạng thái</th>
                    <th>Hình ảnh</th>
                </tr>
                <% for (Item item : items) { %>
                <tr>
                    <td><%= item.getItemId() %></td>
                    <td><%= item.getItemName() %></td>
                    <td><%= item.getPrice() %></td>
                    <td><%= item.getTypeID() %></td>
                    <td><%= item.getStatusID() %></td>
                    <td><img src="<%= item.getImage() %>" alt="<%= item.getItemName() %>" width="100"></td>
                </tr>
                <% } %>
            </table>
        <% 
                } else { 
        %>
            <h3>Không tìm thấy sản phẩm nào.</h3>
        <% 
                } 
            }
        %>
    </body>
</html>
