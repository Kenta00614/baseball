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

@WebServlet("/customer/EmailChangeConfirm")
public class EmailChangeConfirm extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuidString = request.getParameter("uuid");
        try {
            if (uuidString != null) {
                UUID uuid = UUID.fromString(uuidString);
                ProvisionalDAO provisionalDAO = new ProvisionalDAO();
                SpectatorDAO spectatorDAO = new SpectatorDAO();

                // UUIDを使用して仮登録されたメールアドレスの情報を検索
                Provisional provisional = provisionalDAO.getIdAndNewMail(uuid);

                if (provisional != null && provisional.getSpectatorId() > 0) {
                    // メールアドレスを更新
                    spectatorDAO.updateMail(provisional.getSpectatorId(), provisional.getMail(), uuid);

                    // 更新後、Provisional テーブルから情報を削除
                    provisionalDAO.delUuid(uuid);

                    // メールアドレス変更完了ページへリダイレクト
                    response.sendRedirect("/baseball/customer/emailChangeComplete.jsp");
                } else {
                    response.sendRedirect("/baseball/customer/error.jsp");
                }
            } else {
                response.sendRedirect("/baseball/customer/error.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/baseball/customer/error.jsp");
        }
    }
}
