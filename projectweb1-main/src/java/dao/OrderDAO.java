/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Order;
import mylib.DBUtils;


/**
 *
 * @author Asus
 */
public class OrderDAO {
    public void addOrder(Order order) throws ClassNotFoundException {
        String sql = "INSERT INTO [Order] (accID, orderDate, statusID, tableID) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, order.getAccID());
            ps.setDate(2, new java.sql.Date(order.getOrderDate().getTime()));
            ps.setInt(3, order.getStatusID());
            ps.setInt(4, order.getTableID());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Order> getAllOrders() throws SQLException, ClassNotFoundException {
        List<Order> list = new ArrayList<>();
        String sql = "SELECT * FROM [Order]";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Order order = new Order(
                    rs.getInt("orderID"),
                    rs.getInt("accID"),
                    rs.getDate("orderDate"),
                    rs.getInt("statusID"),
                    rs.getInt("tableID")
                );
                list.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
