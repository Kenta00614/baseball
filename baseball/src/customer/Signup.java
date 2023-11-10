package customer;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProvisionalDAO;
import dao.SpectatorDAO;

@WebServlet("/customer/Signup")
public class Signup extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // メールからのUUIDパラメータを取得
        String uuidString = request.getParameter("uuid");
        if (uuidString == null) {
            // エラーメッセージを表示したり、ログを記録したりする
            response.sendRedirect("signupError.jsp"); // 適切なエラーページにリダイレクト
            return;
        }

        try {
            // UUIDをチェックし、有効であれば仮会員情報を本会員テーブルに移行
            UUID uuid = UUID.fromString(uuidString);
            ProvisionalDAO provisionalDAO = new ProvisionalDAO();
            SpectatorDAO spectatorDAO = new SpectatorDAO();

            // 仮会員情報を取得し、本会員テーブルに情報を追加
            int result = spectatorDAO.addNewSpec(uuid);

            if (result > 0) {
                // 本会員登録が成功した場合、成功ページにリダイレクト
                response.sendRedirect("signupComplete.jsp"); // 登録完了ページへのリダイレクト
            } else {
                // 本会員登録が失敗した場合、エラーページにリダイレクト
                response.sendRedirect("signupError.jsp"); // エラーページにリダイレクト
            }
        } catch (Exception e) {
            // エラーハンドリング
            e.printStackTrace();
            response.sendRedirect("signupError.jsp"); // エラーページにリダイレクト
        }
    }
}
