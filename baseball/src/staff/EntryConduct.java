package staff;

import java.io.IOException;
import java.sql.Date;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TicketsDAO;

@WebServlet("/staff/EntryConduct")
public class EntryConduct extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String message = null;
    	String status = null;
    	String classStr = null;
    	String soundStr = null;

    	//値の受け取り
    	String ticketId = request.getParameter("ticketId");
    	System.out.println("ID:"+ticketId);

    	//DBへ照合
    	TicketsDAO dao = new TicketsDAO();
    	long miliseconds = System.currentTimeMillis();
    	Date date = Date.valueOf("1900-01-01");
    	date.setTime(miliseconds);
    	try {
    		//照合
			status = dao.checkTickets(ticketId,date,null);
			System.out.println("status:"+status);
	    	//処理分岐
	    	if(Objects.isNull(status) || !status.equals("1")){//当日のチケット以外の場合
				//入場できないメッセージを送る
	    		message = "このチケットでは入場いただけません。ご確認ください。";
	    		classStr = "emergency";
	    		soundStr = "ok.mp3";
	    	}else{//当日のチケットだった場合
	    		//チケットのステータスを入場済みにする
				int num = dao.statusAdmission(ticketId);
				//入場メッセージを送る
	    		message = "ご来場ありがとうございます。どうぞお楽しみください。";
	    		classStr = "allGreen";
	    		soundStr = "error.mp3";
	    	}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	request.setAttribute("massage", message);
    	request.setAttribute("classStr", classStr);
    	request.setAttribute("sound", soundStr);

        request.getRequestDispatcher("/staff/entry.jsp").forward(request, response);
    }
}
