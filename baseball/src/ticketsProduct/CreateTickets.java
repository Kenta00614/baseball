package ticketsProduct;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Match;
import bean.Seat;
import bean.Tickets;
import dao.MatchDAO;
import dao.SeatDAO;
import dao.TicketsDAO;


@WebServlet("/ticketsProduct/create")
public class CreateTickets extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		次の日の販売開始情報がある時チケットを生成
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat compareDate = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

        // Date型の日時をCalendar型に変換
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        // 日時を加算する
        calendar.add(Calendar.DATE, 1);

        // Calendar型の日時をDate型に戻す
        Date d1 = calendar.getTime();

		MatchDAO matchDAO = new MatchDAO();
		List<Match> searchMatch=new ArrayList<>();

//		次の日に販売開始日が登録されているか
		try {
			searchMatch = matchDAO.searchSaleStartDate(java.sql.Date.valueOf(compareDate.format(d1)));
		} catch (Exception e) {
			e.printStackTrace();
		}

//		登録されているときだけチケット生成
		if(searchMatch.size() > 0){
			for(int i = 0; searchMatch.size()>i; i++){
		        ArrayList<Tickets> ticketsList = new ArrayList<>();

				//daoから席のIDを取ってくる(35000件ぐらいｗ)
		        SeatDAO seatDao = new SeatDAO();
				ArrayList<Seat> seatList = seatDao.getAllId();
				//System.out.println(seatList.size());

				//seatListの分だけ回す
				for(Seat seat:seatList){
					Tickets ticket = new Tickets();
					ticket.setSeatId(seat.getSeatId());
					ticket.setTicketsId(seat.getSeatId()+"r00"+sdf.format(searchMatch.get(i).getEventDate()));
					ticket.setMatchId(searchMatch.get(i).getMatchId());
					ticket.setPurchaseId(0);
					ticket.setShared(false);
					ticket.setStatus("2");
					ticket.setUuid(null);
					//System.out.println(ticket);
					ticketsList.add(ticket);
				}

				//SQLに流す
				TicketsDAO dao = new TicketsDAO();
				dao.insert(ticketsList);
			}
		}
	}
}
