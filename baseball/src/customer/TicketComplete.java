package customer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Match;
import bean.Point;
import bean.Spectator;
import bean.TicketsAndSeat;
import bean.Tournament;
import dao.DAO;
import dao.MatchDAO;
import dao.PointDAO;
import dao.PurchaseDAO;
import dao.TicketsDAO;
import dao.TournamentDAO;

@WebServlet("/customer/TicketComplete")
public class TicketComplete extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();

		List<Tournament> list=new ArrayList<>();
		List<Match> match1=new ArrayList<>();
		Tournament lastTour=null;

		if(session.getAttribute("match") !=null){
			session.removeAttribute("match");
		}

		try {
//			大会情報取得
			TournamentDAO tourDao=new TournamentDAO();
			list=tourDao.getTournamentDetail();
//			最後の大会情報
			for(Tournament tour1: list){
				lastTour=tour1;
			}

//			同じ大会の試合日情報を取得
			MatchDAO matDao=new MatchDAO();
			match1=matDao.searchMatchTournament(lastTour.getTournamentId());

			session.setAttribute("tour", lastTour);
			request.setAttribute("match",match1);
			request.setAttribute("canselPurchase","2");
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/customer/ticketPurchase.jsp").forward(request, response);
        return;
	}

    @SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	HttpSession session=request.getSession();

//		チケット購入のID
		String[] selTickets = (String[]) session.getAttribute("selTickets");
//		大人か子供か
		String[] selChils = (String[])session.getAttribute("selChils");
//		ログイン情報
		List<Spectator> spectator = (List<Spectator>)session.getAttribute("spectatorIds");
//		トーナメント情報
		Tournament tour = (Tournament)session.getAttribute("tour");
//		使ったポイント
		String point = request.getParameter("usePointNum");
		int usePoint=0;
		if(!point.equals("0")){
			usePoint = Integer.parseInt("-"+point);
		}

//    	セッション切れのとき購入画面へ
    	if (spectator == null || selTickets == null || selChils == null || tour == null) {
    		List<Tournament> list=new ArrayList<>();
    		List<Match> match=new ArrayList<>();
    		Tournament lastTour=null;

    		if(session.getAttribute("match") !=null){
    			session.removeAttribute("match");
    		}

    		try {
//    			大会情報取得
    			TournamentDAO tourDao=new TournamentDAO();
    			list=tourDao.getTournamentDetail();
//    			最後の大会情報
    			for(Tournament tour1: list){
    				lastTour=tour1;
    			}

//    			同じ大会の試合日情報を取得
    			MatchDAO matDao=new MatchDAO();
    			match=matDao.searchMatchTournament(lastTour.getTournamentId());

    			session.setAttribute("tour", lastTour);
    			request.setAttribute("match",match);
    			request.setAttribute("canselPurchase","1");
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    		request.getRequestDispatcher("/customer/ticketPurchase.jsp").forward(request, response);
            return;
    	}else{
	//		jspに渡す情報
			List<TicketsAndSeat> selTicketsData=new ArrayList<>();
			int  purchaseId;
			int updateNum=0;
			int pointNum = 0;
			boolean chilFlg = false;

	//		DAO
			TicketsDAO ticketDAO=new TicketsDAO();
			PurchaseDAO purchaseDAO = new PurchaseDAO();
			PointDAO pointDAO = new PointDAO();

			Connection con = null;
			try {
	//			トランザクション
				DAO d = new DAO();
				con=d.getConnection();
				con.setAutoCommit(false);

	//			購入情報登録
				purchaseId = purchaseDAO.insertPurchase(spectator.get(0).getSpectatorId(),con);

				if(purchaseId != 0){
		//			チケットのステータス変更
					for(int i=0; i<selTickets.length ; i++){
	//					子供の時はステータスを変える
						if(selChils[i].equals("子供")){
							chilFlg=true;
						}
						updateNum += ticketDAO.purchaseTickets(selTickets[i],purchaseId,chilFlg,con);
						chilFlg=false;
					}
	//				ポイント情報登録
					if(updateNum == selTickets.length){
						Point p = new Point();
						p.setSpectatorId(spectator.get(0).getSpectatorId());
						p.setFluctuation(usePoint);
						p.setPurchaseId(purchaseId);
						pointNum = pointDAO.insertUsePoint(p, con);
					}
				}
	//			ポイント情報まで正常に動くならコミット
				if(pointNum > 0){
					con.commit();
				}else{
					con.rollback();
				}
	//			トランザクション解除
				con.setAutoCommit(true);
				con.close();

				if(pointNum > 0){
	//				購入するチケットの情報取得
					selTicketsData = ticketDAO.getSelectTickets(selTickets);
				}

	//			セッションの削除
				session.removeAttribute("selTickets");
				session.removeAttribute("selChils");
				session.removeAttribute("seat");
				session.removeAttribute("block");
				session.removeAttribute("count");
				session.removeAttribute("match");
			} catch (Exception e) {
				if(con != null){
					try {
						con.rollback();
						con.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				e.printStackTrace();
			}

	//		チケットの情報
			request.setAttribute("selTicketsData", selTicketsData);
	        request.getRequestDispatcher("/customer/ticketComplete.jsp").forward(request, response);
    	}
    }
}