package customer;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Spectator;
import dao.SpectatorDAO;

@WebServlet("/customer/InformationChange")
public class InformationChange extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        HttpSession session = request.getSession();
        List<Spectator> spectatorIds = (List<Spectator>) session.getAttribute("spectatorIds");

        if (spectatorIds == null || spectatorIds.isEmpty()) {
            response.sendRedirect("/baseball/customer/error.jsp"); // エラーページへリダイレクト
            return;
        }

        // 現在のユーザーを特定するロジック（例えば、リストの最初の要素を使用）
        Spectator currentSpectator = spectatorIds.get(0);

        // 続いて、取得した情報を使用してユーザー情報を更新
        String name = request.getParameter("name");
        String tel = request.getParameter("tel");

        Spectator spectator = new Spectator();
        spectator.setName(name);
        spectator.setTel(tel);
        spectator.setSpectatorId(currentSpectator.getSpectatorId());

        SpectatorDAO dao = new SpectatorDAO();
        try {
            int result = dao.changeSpectatorInfo(spectator);
            if (result > 0) {
                response.sendRedirect("/baseball/customer/changeComplete.jsp");
            } else {
                response.sendRedirect("/baseball/customer/changeFailure.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/baseball/customer/error.jsp");
        }
    }
}
