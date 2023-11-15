package customer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    @SuppressWarnings("unchecked")
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

                List<Spectator> spectatorIds = (List<Spectator>) session.getAttribute("spectatorIds");
                if (spectatorIds == null) {
                    spectatorIds = new ArrayList<>();
                }

                // SPECTATOR_IDをリストに追加
                spectatorIds.add(spectator);

                // 更新されたリストをセッションに保存
                session.setAttribute("spectatorIds", spectatorIds);
                response.sendRedirect("/baseball/customer/loginWelcome.jsp"); // ログイン成功ページへリダイレクト

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
