package staff;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Staff;
import dao.TicketsDAO;

@WebServlet("/staff/SaleStopComplete")
public class SaleStopComplete extends HttpServlet {

	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	HttpSession session=request.getSession();

//	    	ログインしているか
	    	Staff staffData = (Staff) session.getAttribute("staff");
	    	if(staffData == null){
	    		request.setAttribute("sessionOut","1");
	    		request.getRequestDispatcher("login.jsp").forward(request, response);
	    		return;
	    	}else{
		    	try {
		            // 現在の日付を取得
		            Date today = new Date(System.currentTimeMillis());

		            // TicketsDAO のインスタンス化
		            TicketsDAO dao = new TicketsDAO();

		            // 現在の日付で販売停止処理を行う
		            int num = dao.changeStopSales(today);

		            if (num == 0) {
		                request.getRequestDispatcher("/staff/saleStopError.jsp").forward(request, response);
		            } else {
		                request.getRequestDispatcher("/staff/saleStopComplete.jsp").forward(request, response);
		            }

		    	} catch (Exception e) {
		            // 予期せぬ例外のハンドリング
		            throw new ServletException("サーブレット内でエラーが発生しました: " + e.getMessage(), e);
		        }
	    	}
	    }
}

