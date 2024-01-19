package customer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/customer/ProvisionalSignupConfirm")
public class ProvisionalSignupConfirm extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");

    	String mail = request.getParameter("mail");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String tel = request.getParameter("tel");

        request.setAttribute("mail",mail);
        request.setAttribute("name",name);
        request.setAttribute("password",password);
        request.setAttribute("tel",tel);
        request.getRequestDispatcher("/customer/signupConfirm.jsp").forward(request, response);
    }
}
