package customer;

// 必要なインポート
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

        try {
            String hashedPassword = hashPassword(password);

            SpectatorDAO spectatorDAO = new SpectatorDAO();
            Spectator spectator = spectatorDAO.loginSpec(mail, hashedPassword);

            if (spectator != null) {
                // ログイン成功の処理
                HttpSession session = request.getSession();
                session.setAttribute("spectator", spectator);
                response.sendRedirect("/baseball/customer/loginWelcome.jsp"); // ログイン成功ページへリダイレクト
            } else {
                // ログイン失敗の処理
                HttpSession session = request.getSession();
                session.setAttribute("loginError", "無効なメールアドレスまたはパスワードです。");
                response.sendRedirect("/baseball/customer/loginError.jsp"); // エラーページへリダイレクト
            }
        } catch (Exception e) {
            // 例外が発生した場合の処理
            throw new ServletException(e);
        }
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));

        StringBuilder hexString = new StringBuilder(2 * encodedhash.length);
        for (int i = 0; i < encodedhash.length; i++) {
            String hex = Integer.toHexString(0xff & encodedhash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
