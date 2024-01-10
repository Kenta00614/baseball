package staff;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TicketsDAO;

@WebServlet("/staff/SaleCancelComplete")
public class SaleCancelComplete extends HttpServlet {

	    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        try {
	            // 現在の日付を取得
	            Date today = new Date(System.currentTimeMillis());

	            // TicketsDAO のインスタンス化
	            TicketsDAO dao = new TicketsDAO();

	            // 現在の日付で販売停止処理を行う
	            int num = dao.changePostpone(today);

	            // 処理結果の出力
	            response.setContentType("text/html;charset=UTF-8");
	            response.getWriter().println(num + "件のチケットが販売停止になりました。");

	        } catch (Exception e) {
	            // 予期せぬ例外のハンドリング
	            throw new ServletException("サーブレット内でエラーが発生しました: " + e.getMessage(), e);
	        }
	    }
}

