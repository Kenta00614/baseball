package staff;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Constants;

@WebServlet("/staff/Entry")
public class Entry extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setAttribute("seatTypeList", Constants.SEAT_TYPE);
        request.getRequestDispatcher("/staff/entry.jsp").forward(request, response);
    }
}
