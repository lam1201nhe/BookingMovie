package controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import dal.MenuDailyDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.MenuDaily;
import model.MenuDaily;

/**
 *
 * @author msi
 */
@WebServlet(name = "MenuServlet", urlPatterns = {"/menu"})
public class MenuDisplay extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet HomeDisplay</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HomeDisplay at " + request.getContextPath() + "</h1>");
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

        MenuDailyDAO menu = new MenuDailyDAO();

        String selectedCategory = request.getParameter("category");
        String selectedPrice = request.getParameter("order");

        int itemsPerPage = 6; 
        int currentPage = 1; 
// Kiểm tra và lấy giá trị trang hiện tại từ tham số truyền vào (nếu có)
        String currentPageParam = request.getParameter("page");
        if (currentPageParam != null) {
            currentPage = Integer.parseInt(currentPageParam);
        }

        List<MenuDaily> foodmenu;

        if (selectedCategory != null && (selectedCategory.equals("1") || selectedCategory.equals("2") || selectedCategory.equals("3"))) {
            foodmenu = menu.getFoodCategogy(selectedCategory);
        } else if (selectedPrice != null && (selectedPrice.equals("1") || selectedPrice.equals("2") || selectedPrice.equals("3") || selectedPrice.equals("4") || selectedPrice.equals("5") || selectedPrice.equals("6"))) {
            foodmenu = menu.getFoodPrice(selectedPrice);
        } else {
            foodmenu = menu.getFoodMenu();
        }

// Tính toán chỉ số bắt đầu và chỉ số kết thúc của bản ghi trên trang hiện tại8
        int startIndex = (currentPage - 1) * itemsPerPage;
        
        int endIndex = Math.min(startIndex + itemsPerPage, foodmenu.size());//itemsPerPage;

// Trích xuất danh sách bản ghi trên trang hiện tại
        List<MenuDaily> currentFoodMenu = foodmenu.subList(startIndex, endIndex);

        int totalItems = foodmenu.size(); // Tổng số bản ghi
        int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage); // Tính toán số lượng trang


        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", currentPage);
        //gán giá trị foodmenu cho subList bên jsp
        request.setAttribute("foodmenu", currentFoodMenu);
        request.getRequestDispatcher("menu.jsp").forward(request, response);
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
