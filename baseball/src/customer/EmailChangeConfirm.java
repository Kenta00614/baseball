package customer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Provisional;
import bean.Spectator;
import dao.ProvisionalDAO;
import dao.SpectatorDAO;

@WebServlet("/customer/EmailChangeConfirm")
public class EmailChangeConfirm extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	String uuidString = request.getParameter("uuid");
        List<Spectator> spectatorIds = new ArrayList<>();
        try {
            if (uuidString != null && uuidString.length() == 36) {
                UUID uuid = UUID.fromString(uuidString);
                Spectator specData = null;
                ProvisionalDAO provisionalDAO = new ProvisionalDAO();
                SpectatorDAO spectatorDAO = new SpectatorDAO();

                // UUIDを使用して仮登録されたメールアドレスの情報を検索
                Provisional provisional = provisionalDAO.getIdAndNewMail(uuid);

                if (provisional != null && provisional.getSpectatorId() > 0) {
                    // メールアドレスを更新
                    spectatorDAO.updateMail(provisional.getSpectatorId(), provisional.getMail(), uuid);

                    // 更新後、Provisional テーブルから情報を削除
                    provisionalDAO.delUuid(uuid);

//                  観戦客情報を取得
                    specData = spectatorDAO.serchSpec(provisional.getSpectatorId());
//                  セッション更新
                    session.removeAttribute("spectatorIds");
                    spectatorIds.add(specData);
                    session.setAttribute("spectatorIds",spectatorIds);

                    // メールアドレス変更完了ページへ
                    request.setAttribute("comfilmFlg","0");
                } else {
                	request.setAttribute("comfilmFlg","1");
                }
            } else {
            	request.setAttribute("comfilmFlg","2");
            }
            request.getRequestDispatcher("/customer/emailChangeComplete.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/baseball/customer/error.jsp");
        }
    }
}
