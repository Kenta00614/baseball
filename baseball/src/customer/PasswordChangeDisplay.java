package customer;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Provisional;
import dao.ProvisionalDAO;

@WebServlet("/customer/PasswordChangeDisplay")
public class PasswordChangeDisplay extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	String uuidString = request.getParameter("uuid");

        UUID uuid = UUID.fromString(uuidString);
        ProvisionalDAO provisionalDAO = new ProvisionalDAO();
        Provisional seerchProv= null;
        try {
        	seerchProv = provisionalDAO.searchUuid(uuid);
		} catch (Exception e) {
			e.printStackTrace();
		}

        if (seerchProv != null) {
        	request.setAttribute("uuid", uuidString);
            request.getRequestDispatcher("/customer/passwordChange.jsp").forward(request, response);
            return;
        } else {
        	request.setAttribute("sucssesFlg", "1");
        	request.getRequestDispatcher("/customer/passwordReset.jsp").forward(request, response);
        }
    }
}
