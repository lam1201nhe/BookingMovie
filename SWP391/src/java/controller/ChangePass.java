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
 *
 * @author msi
 */
@WebServlet(name = "ChangePass", urlPatterns = {"/changepass"})
public class ChangePass extends HttpServlet {

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
            out.println("<title>Servlet ChangePass</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangePass at " + request.getContextPath() + "</h1>");
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
        String p1 = request.getParameter("passold");
        String p2 = request.getParameter("passnew");
        String p3 = request.getParameter("repeat");

        LoginDAO obj = new LoginDAO();

        Account a = obj.getCheckAcc(u, p1); // Kiểm tra có tài khoản ko

        String p4 = "";
        HomeDAO obj2 = new HomeDAO();

        try {
            p4 = obj2.delSpace2(p2);
        } catch (Exception e) {
            System.out.println(e);
        }

        if (a == null) { // Nếu không có tài khoản trả về null
            request.setAttribute("error", "Not invalid");
        } else {
            // Kiểm tra chuỗi kí tự ko dấu và không cách
            if (p2.equalsIgnoreCase(p4) && obj.checkInput(p2)) {
                // Kiểm tra chuỗi kí tự có trong phạm vi chỉ định
                if ((obj.checkInputRegiter(p2))) {
                    if (p2.equalsIgnoreCase(p3)) {
                        obj.getUpdatePass(u, p2);
                        request.setAttribute("error", "Thay đổi mật khẩu thành công");
                    } else {
                        request.setAttribute("error", "Mật khẩu không khớp");
                    }

                } else {
                    request.setAttribute("error", "Mật khẩu trên 6 kí tự");
                }
            } else {
                request.setAttribute("error", "Hãy nhập kí tự liền không dấu");
            }

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
