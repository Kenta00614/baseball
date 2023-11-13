package customer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Spectator;
import dao.SpectatorDAO;
@WebServlet("/customer/Login")
public class Login extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String mail = request.getParameter("mail");
        String password = request.getParameter("password");

        SpectatorDAO spectatorDAO = new SpectatorDAO();

        try {
            Spectator spectator = spectatorDAO.loginSpec(mail, password);
            if (spectator != null) {
                // ログイン成功
                HttpSession session = request.getSession();
                session.setAttribute("spectator", spectator);
                response.sendRedirect("/customer/loginWelcome.jsp"); // ログイン成功ページへリダイレクト
            } else {
                // ログイン失敗
                HttpSession session = request.getSession();
                session.setAttribute("loginError", "無効なメールアドレスまたはパスワードです。");
                response.sendRedirect("/baseball/customer/loginError.jsp"); // ログインページへリダイレクト
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
