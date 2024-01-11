package customer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/customer/InformationChangeConfirm")
public class InformationChangeConfirm extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // JSPから送られてきたパラメータを取得
        String name = request.getParameter("name");
        String tel = request.getParameter("tel");

        // 取得したパラメータをリクエスト属性にセット
        request.setAttribute("name", name);
        request.setAttribute("tel", tel);

        // 確認ページにフォワード
        request.getRequestDispatcher("/customer/informationChangeConfirm.jsp").forward(request, response);
    }
}
