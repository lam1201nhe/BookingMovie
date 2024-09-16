/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.HomeDAO;
import dal.LoginDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;

/**
 * Lớp gọi hàm và đưa dữ liệu lên trang
 *
 * @Phiên Bản : 1.0 04/06/2023
 * @Tác giả: Nguyễn Văn Thịnh
 */
@WebServlet(name = "Register", urlPatterns = {"/register"})
public class Register extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Register</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Register at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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

        String u = request.getParameter("user");
        String p = request.getParameter("pass");
        String r = request.getParameter("repeat");

        String u2 = "", p2 = "";

        LoginDAO obj = new LoginDAO();
        HomeDAO obj2 = new HomeDAO();

        try {
            u2 = obj2.delSpace2(u); // Xóa mọi khoảng trống trong chuỗi
            p2 = obj2.delSpace2(p);
        } catch (Exception e) {
            System.out.println(e);
        }
        
        // Kiểm tra chuỗi kí tự ko dấu và không cách
        if (u2.equalsIgnoreCase(u) && p2.equalsIgnoreCase(p) && obj.checkInput(u) && obj.checkInput(p)) { 
            // Kiểm tra chuỗi kí tự có trong phạm vi chỉ định
            if ((obj.checkInputRegiter(u) == false) || (obj.checkInputRegiter(p) == false)) {
                request.setAttribute("error", "Mật khẩu và tài khoản trên 6 kí tự");
            } else {
                // Kiểm tra tài khaonr đã tồn tại chưa
                if (obj.getCheckUser(u) == true) {
                    request.setAttribute("error", "Tên tài khoản đã tồn tại");
                } else {
                    //Thêm tài khoản mới
                    if (p.equalsIgnoreCase(r)) {
                        Account c = new Account((obj.getCountAcc() + 1), u, p, "customer");
                        obj.getCreateAcc(c);
                        request.setAttribute("error", "Tạo tài khoản thành công");
                    } else {
                        request.setAttribute("error", "Mật khẩu không khớp");
                    }
                }
            }
        } else {
            request.setAttribute("error", "Hãy nhập kí tự liền không dấu");
        }

        request.getRequestDispatcher("login.jsp").forward(request, response);

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
