package customer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/customer/InformationChangeDisplay")
public class InformationChangeDisplay extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
        // リクエストから名前と電話番号を取得
        String name = request.getParameter("name");
        String tel = request.getParameter("tel");

        // リクエストスコープに名前と電話番号を設定
        request.setAttribute("name", name);
        request.setAttribute("tel", tel);

        // JSPへフォワード
        request.getRequestDispatcher("/customer/informationChange.jsp").forward(request, response);
    }
}
