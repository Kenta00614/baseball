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
                response.sendRedirect("/baseball/staff/staffPassComplete.jsp");
            } else {
                // パスワード更新失敗
                response.sendRedirect("/baseball/staff/staffPassFailed.jsp");
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
