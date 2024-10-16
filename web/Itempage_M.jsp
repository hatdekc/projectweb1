<%-- 
    Document   : Itempage_M
    Created on : Oct 12, 2024, 1:41:40 AM
    Author     : ADMIN
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="dto.Item"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            ArrayList<Item> list_1 = (ArrayList<Item>) request.getAttribute("list1");
            if(list_1 != null && !list_1.isEmpty()){
        %>
        <h1>Danh sách sản phẩm đang bán</h1>
        
        <table border = "1">
            <tr>
                <th>ID</th>
                <th>Tên sản phẩm</th>
                <th>Giá</th>
                <th>Loại</th>
                <th>Trạng thái</th>
                <th>Hình ảnh</th>
                <th>Hành động</th>
            </tr>
            <% for (Item t : list_1) {%>
            <tr>
                <td><%=t.getItemId()%></td>
                <td><%=t.getItemName()%></td>
                <td><%=t.getPrice()%></td>
                <td><%=t.getTypeID()%></td>
                <td><%=t.getStatusID()%></td>
                <td><%=t.getImage()%></td>
                <td>
                    <a href="#" >Edit</a>
                    <a href="#" >Delete</a>
                </td>
            </tr>
            <%}%>
        </table>
        <% } else {%>
        <p>Không có sản phẩm nào được tìm thấy.</p>
        <% } %>
    </body>
</html>
