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

@WebServlet("/customer/MemberInformation")
public class MemberInformation extends HttpServlet {

    @SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        List<Spectator> spectatorIds = (List<Spectator>) session.getAttribute("spectatorIds");

        if (spectatorIds == null || spectatorIds.isEmpty()) {
            // ユーザーがログインしていない場合
            response.sendRedirect("/baseball/customer/loginError.jsp");
        } else {
            // 最後にログインしたユーザーの情報を取得
            Spectator spectator = spectatorIds.get(spectatorIds.size() - 1);

            int spectatorId = spectator.getSpectatorId();

            SpectatorDAO SD = new SpectatorDAO();
            Spectator SP = new Spectator();

            try {
				SP = SD.serchSpec(spectatorId);
			} catch (Exception e) {
				e.printStackTrace();
			}

            request.setAttribute("spectator", SP);
            request.getRequestDispatcher("/customer/memberInformation.jsp").forward(request, response);
        }
    }
}
