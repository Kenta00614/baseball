package staff;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SchoolDAO;

@WebServlet("/staff/HighschoolRegistration")
public class HighschoolRegistration extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    	request.setCharacterEncoding("UTF-8");
        SchoolDAO dao = new SchoolDAO();
        int tournamentId = Integer.parseInt(request.getParameter("tournamentId")); // 大会IDを取得

        try {
            String[] schoolName = new String[49];
            for (int i = 1; i <= 49; i++) {
                schoolName[i - 1] = request.getParameter("schoolName" + i);
            }
            // 高校名をデータベースに登録
            int result = dao.insertSchool(schoolName, tournamentId);
            if (result > 0) {
                // 登録成功
                response.sendRedirect("tournamentRegistrationCompletion.jsp");
            } else {
                // 登録失敗
                response.sendRedirect("error.jsp");
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
