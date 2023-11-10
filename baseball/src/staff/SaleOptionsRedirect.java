package staff;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/staff/SaleOptionsRedirect")
public class SaleOptionsRedirect extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String selectedOption = request.getParameter("sale");

        if ("cancel".equals(selectedOption)) {
            response.sendRedirect("SaleCancelCheck");
        } else if ("end".equals(selectedOption)) {
            response.sendRedirect("SaleStopCheck");
        }

    }
}