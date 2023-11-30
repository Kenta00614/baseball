package customer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProvisionalDAO;
import dao.SpectatorDAO;


@WebServlet("/customer/PasswordChange")
public class PasswordChange extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uuidString = request.getParameter("uuid");
        String newPassword = request.getParameter("newPassword");

        try {
            UUID uuid = UUID.fromString(uuidString);
            SpectatorDAO spectatorDAO = new SpectatorDAO();
            ProvisionalDAO provisionalDAO = new ProvisionalDAO();
            String hashedPassword = hashPassword(newPassword);

            int result = spectatorDAO.updatePassword(uuid, hashedPassword);
            provisionalDAO.delUuid(uuid);



            if (result > 0) {
                // パスワード更新成功ページへリダイレクト
                response.sendRedirect(request.getContextPath() + "/customer/passwordResetSuccess.jsp");
            } else {
                // 更新失敗ページへリダイレクト
                response.sendRedirect(request.getContextPath() + "/customer/passwordResetFail.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/baseball/customer/error.jsp");
        }
    }

    // パスワードハッシュ化メソッド（ProvisionalSignupからコピー）
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
