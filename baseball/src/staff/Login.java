package staff;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Staff;
import dao.StaffDAO;
@WebServlet("/staff/Login")
public class Login extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String id = request.getParameter("id");
        String password = request.getParameter("password");


        StaffDAO staffDAO = new StaffDAO();

        try {
        	Staff staff = staffDAO.loginStaff(id, password);
            if (staff != null) {
                // ログイン成功
                HttpSession session = request.getSession();
//                初期パスワード(生年月日8桁)のままの場合
                if(staff.getInitialPassFlg().equals("1")){
                	request.setAttribute("id", id);
                	request.getRequestDispatcher("/staff/staffPass.jsp").forward(request, response);
                	return;
                }else{
	                session.setAttribute("staff", staff);
	                response.sendRedirect("/baseball/staff/loginWelcome.jsp"); // ログイン成功ページへリダイレクト
                }
            } else {
                // ログイン失敗
                HttpSession session = request.getSession();
                session.setAttribute("loginError", "無効なメールアドレスまたはパスワードです。");
                response.sendRedirect("/baseball/staff/loginError.jsp"); // ログインページへリダイレクト
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
