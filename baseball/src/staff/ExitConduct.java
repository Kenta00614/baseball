package staff;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.TicketsExp;
import common.Constants;
import dao.DAO;
import dao.SpectatorDAO;
import dao.TicketsDAO;

@WebServlet("/staff/ExitConduct")
public class ExitConduct extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String message = null;
    	String status = null;
    	String classStr = null;
    	DAO dao = new DAO();
    	Connection con = null;
    	String soundStr = null;

    	//値の受け取り
    	String ticketId = request.getParameter("ticketId");
    	System.out.println("ID:"+ticketId);

    	//DBへ照合
    	TicketsDAO tDao = new TicketsDAO();
    	SpectatorDAO sDao = new SpectatorDAO();
    	long miliseconds = System.currentTimeMillis();
    	Date date = Date.valueOf("1900-01-01");
    	date.setTime(miliseconds);
    	try {
    		con = dao.getConnection();
    		con.setAutoCommit(false);
    		//照合
			status = tDao.checkTickets(ticketId,date,con);
			System.out.println("status:"+status);
	    	//処理分岐
	    	if(Objects.isNull(status) || !status.equals("4")){//当日のチケット以外の場合
				//退場できないメッセージを送る
	    		message = "チケットをお間違えです。ご確認ください。";
	    		classStr = "emergency";
	    		soundStr = "../staff/sound/error.mp3";
	    	}else{//当日のチケットだった場合
	    		//チケットのステータスを退場済みにする
	    		TicketsExp tickets = tDao.statusLeave(ticketId,con);
	    		//途中退場チケットの購入者にポイントの付与をする
	    		int price = 0;
	    		if (tickets.isChild()){
	    			price = Constants.CHILD_SEAT_PRICE.get(tickets.getType());
	    		}else{
	    			price = Constants.SEAT_PRICE.get(tickets.getType());
	    		}
	    		sDao.addPoint(tickets.getSpectatorId(),price);
				//入場メッセージを送る
	    		message = "ご来場ありがとうございました。";
	    		classStr = "allGreen";
	    		soundStr = "../staff/sound/ok.mp3";
	    		//コミット処理
	    		con.commit();
	    	}
		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
//			トランザクション解除
			try {
				con.setAutoCommit(true);
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
    	request.setAttribute("massage", message);
    	request.setAttribute("classStr", classStr);
    	request.setAttribute("sound", soundStr);

        request.getRequestDispatcher("/staff/exit.jsp").forward(request, response);
    }
}
