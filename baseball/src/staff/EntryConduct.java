package staff;

import java.io.IOException;
import java.sql.Date;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Tickets;
import common.Constants;
import dao.TicketsDAO;

@WebServlet("/staff/EntryConduct")
public class EntryConduct extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String message = null;
    	Tickets ticket = null;
    	String classStr = null;

    	//値の受け取り
    	String ticketId = request.getParameter("ticketId");
    	String seatType = request.getParameter("seatType");
    	//System.out.println("ID:"+ticketId);

    	//DBへ照合
    	TicketsDAO dao = new TicketsDAO();
    	long miliseconds = System.currentTimeMillis();
    	Date date = Date.valueOf("1900-01-01");
    	date.setTime(miliseconds);
    	try {
    		//照合
    		ticket = dao.checkTickets(ticketId,date,null);
			//System.out.println("status:"+status);
	    	//処理分岐
	    	if(Objects.isNull(ticket.getStatus()) || !ticket.getStatus().equals("1")){//当日のチケット以外の場合
				//入場できないメッセージを送る
	    		message = "このチケットでは入場いただけません。ご確認ください。";
	    		classStr = "emergency";
	    	}else if(!ticket.getTicketsId().substring(0, 2).equals(seatType)){
	    		//場所が違うメッセージを送る
	    		message = "この入口では入場いただけません。係員にご確認ください。";
	    		classStr = "emergency";
	    	}else{//当日のチケットだった場合
	    		//チケットのステータスを入場済みにする
				int num = dao.statusAdmission(ticketId);
				//入場メッセージを送る
		    	message = "ご来場ありがとうございます。どうぞお楽しみください。";
		    	classStr = "allGreen";
	    	}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	request.setAttribute("massage", message);
    	request.setAttribute("classStr", classStr);
    	request.setAttribute("seatType", seatType);
    	request.setAttribute("seatTypeList", Constants.SEAT_TYPE);

        request.getRequestDispatcher("/staff/entry.jsp").forward(request, response);
    }
}
