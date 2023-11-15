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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            SchoolDAO schoolDAO = new SchoolDAO();
            String[] schoolNames = new String[49];

            for (int i = 0; i < 49; i++) {
                schoolNames[i] = request.getParameter("schoolName" + (i + 1));
            }

            if (sch > 0) {
                // 登録成功時の処理
            } else {
                // 登録失敗時の処理
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
