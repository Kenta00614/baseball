package customer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Spectator;
import dao.DAO;

public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mail = request.getParameter("email");
        String password = request.getParameter("password");

        DAO dao = new DAO();
        try (Connection conn = dao.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Spectator WHERE mail = ? AND password = ?");
            ps.setString(1, mail);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Spectator spectator = new Spectator();
                spectator.setSpectatorId(rs.getInt("spectatorId"));
                spectator.setName(rs.getString("name"));
                spectator.setPassword(rs.getString("password"));
                spectator.setTel(rs.getString("tel"));
                spectator.setPoint(rs.getInt("point"));
                spectator.setMail(rs.getString("mail"));

                HttpSession session = request.getSession();
                session.setAttribute("spectator", spectator);
                response.sendRedirect("/customer/loginWelcome.jsp");
            } else {
                response.sendRedirect("loginWelcome.jsp?error=true");
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
