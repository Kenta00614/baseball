package customer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/customer/Logout")
public class Logout extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 現在のセッションを取得
        HttpSession session = request.getSession(false);

        // セッションが存在する場合は無効化
        if (session != null) {
            session.invalidate();
        }

        // ログアウト後にユーザーをリダイレクトするページ（例えばログインページなど）
        response.sendRedirect("/customer/login.jsp");
    }
}
