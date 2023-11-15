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

@WebServlet("/customer/MemberInformation")
public class MemberInformation extends HttpServlet {

    @SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        HttpSession session = request.getSession();
        List<Spectator> spectatorIds = (List<Spectator>) session.getAttribute("spectatorIds");

        if (spectatorIds == null || spectatorIds.isEmpty()) {
            // ユーザーがログインしていない場合
            response.sendRedirect("/baseball/customer/loginError.jsp");
        } else {
            // 最後にログインしたユーザーの情報を取得
            Spectator spectator = spectatorIds.get(spectatorIds.size() - 1);
            request.setAttribute("spectator", spectator);
            request.getRequestDispatcher("/customer/memberInformation.jsp").forward(request, response);
        }
    }
}
