package customer;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Provisional;
import dao.ProvisionalDAO;
import dao.SpectatorDAO;

@WebServlet("/customer/Signup")
public class Signup extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuidString = request.getParameter("uuid");
        try {
            if (uuidString != null) {
                UUID uuid = UUID.fromString(uuidString);

                // ProvisionalDAO を使用して仮登録されたユーザーの情報を検索
                ProvisionalDAO provisionalDAO = new ProvisionalDAO();
                Provisional provisionalUser = provisionalDAO.searchUuid(uuid);

                if (provisionalUser != null) {
                    // SpectatorDAO を使用して正規のユーザーとして登録
                    SpectatorDAO spectatorDAO = new SpectatorDAO();
                    int result = spectatorDAO.addNewSpec(uuid);

                    // 登録後、Provisional テーブルからユーザー情報を削除
                    provisionalDAO.delUuid(uuid);

                    // 登録完了ページへリダイレクト
                    response.sendRedirect("/baseball/customer/signupComplete.jsp");
                } else {
                    // 該当するユーザーが見つからない場合のエラーハンドリング
                    response.sendRedirect("/a/error.jsp");
                }
            } else {
                // UUID が無効な場合のエラーハンドリング
                response.sendRedirect("/b/error.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/c/error.jsp");
        }
    }
}