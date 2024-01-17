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
import utils.EmailUtility;

@WebServlet("/customer/PasswordChangeMail")
public class PasswordChangeMail extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8");
	    String mail = request.getParameter("newMail");
	    String contextPath = request.getContextPath();

	    try {
	        SpectatorDAO spectatorDAO = new SpectatorDAO();
	        String spectatorMail = spectatorDAO.searchSameMail(mail);

	        if (spectatorMail != null && spectatorMail.equals(mail)) {
	            ProvisionalDAO provisionalDAO = new ProvisionalDAO();
	            UUID uuid = provisionalDAO.insertIdAndUuid(mail);

	            if (uuid != null) {
	                String verificationLink = "http://" + request.getServerName() + ":" + request.getServerPort() +
	                        contextPath + "/customer/PasswordChangeDisplay?uuid=" + uuid.toString();

	                String subject = "パスワードリセットリンク";
	                String content = "以下のリンクからパスワードのリセットを行ってください。\n" + verificationLink;

	                EmailUtility.sendEmail(mail, subject, content);

	                // 成功ページへリダイレクト
	                response.sendRedirect(contextPath + "/customer/passwordResetEmailSent.jsp");
	            } else {
	                // UUIDの生成または登録に失敗した場合
	                response.sendRedirect(contextPath + "/customer/error1.jsp");
	            }
	        } else {
	            // メールアドレスが見つからない場合の処理
	            response.sendRedirect(contextPath + "/customer/emailNotFound.jsp");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        response.sendRedirect("/baseball/customer/error2.jsp");
	    }
	}
}
