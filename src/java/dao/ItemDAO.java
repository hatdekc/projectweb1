/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Item;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import mylib.DBUtils;

/**
 *
 * @author ADMIN
 */
public class ItemDAO {
    //lấy tất cả các item dựa trên status
    public ArrayList<Item> getAllItems(int status){
        ArrayList<Item> list = new ArrayList<>();
        Connection cn = null;
        try{
            cn = DBUtils.makeConnection();
            String sql = "select ItemId, ItemName, Price, typeID, statusID, Image\n"
                    + "from dbo.Items\n"
                    + "where statusID = ?";
            PreparedStatement st = cn.prepareStatement(sql);
            st.setInt(1, status);
            ResultSet table = st.executeQuery();
            if(table != null){
                while(table.next()){
                    Item t = new Item();
                    t.setItemId(table.getInt("ItemId"));
                    t.setItemName(table.getString("ItemName"));
                    t.setPrice(table.getInt("Price"));
                    t.setTypeID(table.getInt("typeID"));
                    t.setStatusID(table.getInt("statusID"));
                    t.setImage(table.getString("Image"));
                    list.add(t);
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    
    //thêm sản phẩm vào cơ sở dữ liệu
    public int insertItem(String name, int price, int type, int status, String imagepath){
        int rs = 0;
        Connection cn = null;
        try{
            cn = DBUtils.makeConnection();
            if(cn != null){
                String sql = "insert dbo.Items (ItemName, Price, typeID, statusID, Image) values (?,?,?,?,?)";
                PreparedStatement st = cn.prepareStatement(sql);
                st.setString(1, name);
                st.setInt(2, price);
                st.setInt(3, type);
                st.setInt(4, status);
                st.setString(5, imagepath);
                rs = st.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if(cn != null) cn.close();
             } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return rs;
    }
    
    //lấy tất cả các item dựa vào tên sản phẩm
    public ArrayList<Item> getAllItem(String name){
        ArrayList<Item> list = new ArrayList<>();
        Connection cn = null;
        try{
            cn = DBUtils.makeConnection();
            if(cn != null){
                String sql = "select ItemId, ItemName, Price, typeID, statusID, Image\n"
                        + "from dbo.Items\n"
                        + "where ItemName like ?";
                PreparedStatement st = cn.prepareStatement(sql);
                st.setString(1, "%" + name + "%");
                ResultSet table = st.executeQuery();
                if(table != null){
                    while(table.next()){
                        Item t = new Item();
                        t.setItemId(table.getInt("ItemId"));
                        t.setItemName(table.getString("ItemName"));
                        t.setPrice(table.getInt("Price"));
                        t.setTypeID(table.getInt("typeID"));
                        t.setStatusID(table.getInt("statusID"));
                        t.setImage(table.getString("Image"));
                        list.add(t);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if(cn != null) cn.close();
             } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    
    public Item getItem(int itemid){
        Item it = null;
        ArrayList<Item> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null){
                String sql = "select ItemId, ItemName, Price, typeID, statusID, Image\n"
                        + "from dbo.Items\n"
                        + "where ItemId = ?";
                PreparedStatement st = cn.prepareStatement(sql);
                st.setInt(1, itemid);
                ResultSet table = st.executeQuery();
                if(table != null){
                    while(table.next()){
                        Item t = new Item();
                        t.setItemId(table.getInt("ItemId"));
                        t.setItemName(table.getString("ItemName"));
                        t.setPrice(table.getInt("Price"));
                        t.setTypeID(table.getInt("typeID"));
                        t.setStatusID(table.getInt("statusID"));
                        t.setImage(table.getString("Image"));
                        list.add(t);
                    }
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return it;
    }
    
    //phương thức cập nhật sản phẩm
    public boolean updateItem(Item item){
        Connection cn = null;
        boolean success = false;
        try{
            cn = DBUtils.makeConnection();
            String sql = "update Items set ItemName=?, Price=?, typeID=?, statusID=?, Image=?"
                    + "where ItemId";
            PreparedStatement st = cn.prepareStatement(sql);
            st.setString(1, item.getItemName());
            st.setInt(2, item.getPrice());
            st.setInt(3, item.getTypeID());
            st.setInt(4, item.getStatusID());
            st.setString(5, item.getImage());
            st.setInt(6, item.getItemId());
            int rows = st.executeUpdate();
            success = (rows > 0);
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return success;
    }
    
    //phương thức xóa sản phẩm
    public boolean deleteItem(int itemId){
        Connection cn = null;
        boolean success = false;
        try{
           cn = DBUtils.makeConnection();
           String sql = "delete from Items where ItemId=?";
           PreparedStatement st = cn.prepareStatement(sql);
           st.setInt(1, itemId);
           int rows = st.executeUpdate();
           success = (rows > 0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return success;
    }
}

