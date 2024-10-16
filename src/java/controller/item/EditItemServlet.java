/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.item;

import dao.ItemDAO;
import dto.Item;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
public class EditItemServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            int itemId = Integer.parseInt(request.getParameter("ItemId"));
            ItemDAO dao = new ItemDAO();
            Item item = dao.getItem(itemId);//lấy thông tin sản phẩm
            
            if(item != null){
                request.setAttribute("item", item);
                request.getRequestDispatcher("editItem.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "Sản phẩm không tồn tại.");
                request.getRequestDispatcher("errorMessage.jsp").forward(request, response);
            }
        } catch (Exception e){
            e.printStackTrace();
            request.setAttribute("errorMessage", "Có lỗi xảy ra khi tải sản phẩm");
            request.getRequestDispatcher("errorMessage.jsp").forward(request, response);
        }
        
        int itemId = Integer.parseInt(request.getParameter("ItemId"));
            String itemName = request.getParameter("ItemName");
            int price = Integer.parseInt(request.getParameter("Price"));
            int typeID = Integer.parseInt(request.getParameter("TypeID"));
            int statusID = Integer.parseInt(request.getParameter("StatusID"));
            String image = request.getParameter("Image");

            ItemDAO dao = new ItemDAO();
            boolean success = dao.updateItem(new Item(itemId, itemName, price, typeID, statusID, image));

            if (success) {
                response.sendRedirect("GetItemServlet");  // Chuyển về trang hiển thị sản phẩm
            } else {
                request.setAttribute("errorMessage", "Cập nhật sản phẩm thất bại.");
                request.getRequestDispatcher("errorPage.jsp").forward(request, response);
            }
        }
    
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
    
