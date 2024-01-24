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

import bean.Point;
import bean.Tickets;
import bean.TicketsExp;
import common.Constants;
import dao.DAO;
import dao.PointDAO;
import dao.SpectatorDAO;
import dao.TicketsDAO;

@WebServlet("/staff/ExitConduct")
public class ExitConduct extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String message = null;
    	Tickets ticket = null;
    	String classStr = null;
    	DAO dao = new DAO();
    	Connection con = null;
    	Point point = null;

    	//値の受け取り
    	String ticketId = request.getParameter("ticketId");

    	//DBへ照合
    	TicketsDAO tDao = new TicketsDAO();
    	SpectatorDAO sDao = new SpectatorDAO();
    	PointDAO pDao = new PointDAO();
    	long miliseconds = System.currentTimeMillis();
    	Date date = Date.valueOf("1900-01-01");
    	date.setTime(miliseconds);
    	try {
    		con = dao.getConnection();
    		con.setAutoCommit(false);
    		//照合
    		ticket = tDao.checkTickets(ticketId,date,con);
			//System.out.println("status:"+status);
	    	//処理分岐
	    	if(Objects.isNull(ticket.getStatus()) || !ticket.getStatus().equals("4")){//当日のチケット以外の場合
				//退場できないメッセージを送る
	    		message = "チケットをお間違えです。ご確認ください。";
	    		classStr = "emergency";
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
	    		point = new Point();
	    		point.setTicketsId(ticketId);
	    		point.setFluctuation((int)(price*0.2));
	    		point.setPurchaseId(tickets.getPurchaseId());
	    		point.setSpectatorId(tickets.getSpectatorId());
	    		pDao.insertSavePoint(point,con);
				//入場メッセージを送る
	    		message = "ご来場ありがとうございました。";
	    		classStr = "allGreen";
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

        request.getRequestDispatcher("/staff/exit.jsp").forward(request, response);
    }
}
