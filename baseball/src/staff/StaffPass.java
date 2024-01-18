package staff;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StaffDAO;

@WebServlet("/staff/StaffPass")
public class StaffPass extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String password = request.getParameter("password");

        StaffDAO staffDAO = new StaffDAO();

        try {
            int result = staffDAO.updateStaff(id, password);
            if (result > 0) {
                // パスワード更新成功
            	request.setAttribute("id", id);
            	request.setAttribute("password", password);
            	request.getRequestDispatcher("/staff/staffPassComplete.jsp").forward(request, response);
            	return;
            } else {
//              生年月日と新しいパスワードが一緒の場合
            	request.setAttribute("id", id);
            	request.getRequestDispatcher("/staff/staffPass.jsp").forward(request, response);
            }
        } catch (Exception e) {
        	// パスワード更新失敗
        	response.sendRedirect("/baseball/staff/staffPassFailed.jsp");
            throw new ServletException(e);
        }
    }
}
