/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package userDao;

import dto.Item;
import java.sql.*;
import java.util.*;
import mylib.DBUtils;


/**
 *
 * @author Asus
 */
public class ItemUserDao {
     public List<Item> searchItemsByName(String keyword) {
        List<Item> itemList = new ArrayList<>();
        String query = "SELECT * FROM Items WHERE ItemName LIKE ?";

        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Item item = new Item(
                    rs.getInt("ItemId"),
                    rs.getString("ItemName"),
                    rs.getInt("Price"),
                    rs.getInt("typeID"),
                    rs.getInt("statusID"),
                    rs.getString("Image")
                );
                itemList.add(item);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return itemList;
    }
}
