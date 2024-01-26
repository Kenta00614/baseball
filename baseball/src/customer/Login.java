package customer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.DuelExp;
import bean.Match;
import bean.PurchaseExp;
import bean.Spectator;
import bean.Tournament;
import common.Constants;
import dao.DuelDAO;
import dao.MatchDAO;
import dao.PurchaseDAO;
import dao.SeatDAO;
import dao.SpectatorDAO;
import dao.TournamentDAO;
@WebServlet("/customer/Login")
public class Login extends HttpServlet {

    @SuppressWarnings({ "unchecked", "rawtypes" })
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String mail = request.getParameter("mail");
        String password = request.getParameter("password");


        SpectatorDAO spectatorDAO = new SpectatorDAO();

        try {

        	String hashedPassword = hashPassword(password);
            Spectator spectator = spectatorDAO.loginSpec(mail, hashedPassword);

            if (spectator != null) {
                // ログイン成功
                HttpSession session = request.getSession();

                List<Spectator> spectatorIds = (List<Spectator>) session.getAttribute("spectatorIds");
                if (spectatorIds == null) {
                    spectatorIds = new ArrayList<>();
                }

                // SPECTATOR_IDをリストに追加
                spectatorIds.add(spectator);

                // 更新されたリストをセッションに保存
                session.setAttribute("spectatorIds", spectatorIds);

//              チケット購入の途中でログイン画面遷移されたらチケット購入の画面に戻す
                String seat = (String)session.getAttribute("seat");
                if(seat != null){
                	List<String> blocks=new ArrayList<>();
        	   	 	SeatDAO seatDAO=new SeatDAO();

//        			開催日の購入チケット取得
        	    	List<PurchaseExp> purchaseList=new ArrayList<>();
        	    	PurchaseDAO purchaseDAO=new PurchaseDAO();
        	    	Match match=(Match)session.getAttribute("match");
        	    	purchaseList = purchaseDAO.getDatePurchase(spectatorIds.get(spectatorIds.size()-1).getSpectatorId(),match.getEventDate());
        	    	int count = (int)session.getAttribute("count");
        	    	int remaining = (int)session.getAttribute("remaining");

        	    	if(count+purchaseList.size()>6 || remaining<count){

        	    		List<String> seatType = new ArrayList<>();

        				String[] seatOrder = {"0B","0F","0T","0R","0L"};

        		        for (String key : seatOrder) {
        		            String value = Constants.SEAT_TYPE.get(key);
        		            seatType.add(value);
        		        }
        		        request.setAttribute("seatOrder", seatOrder);
        				request.setAttribute("seatType",seatType );
        				if(remaining<count){
        					request.setAttribute("remaining", remaining);
        				}
        				if(count+purchaseList.size()>6){
        					request.setAttribute("countTic", 6-purchaseList.size());
        				}
        				session.removeAttribute("remaining");
        				request.getRequestDispatcher("/customer/ticketApplication.jsp").forward(request, response);
        				return;
        	    	}

                	blocks = seatDAO.getBlock(seat);
    				request.setAttribute("blocks", blocks);
    				request.setAttribute("remain", -1);
    				request.getRequestDispatcher("/customer/ticketSelectAll.jsp").forward(request, response);
    				return;
                }

                List<Match> matchList = new ArrayList();
            	List<Tournament> tournamentList = new ArrayList();
        		List<List<DuelExp>> duelList = new ArrayList<List<DuelExp>>();
        		Tournament tournament = null;

        		MatchDAO matchDAO = new MatchDAO();
        		DuelDAO duelDAO = new DuelDAO();
        		TournamentDAO tournamentDAO = new TournamentDAO();

//        			tournamntの情報取得
    			tournamentList = tournamentDAO.getTournamentDetail();
    			if(tournamentList.size() != 0){
	//        			最後に登録されているトーナメントのmatch情報を取得
	    			matchList = matchDAO.searchMatchTournament(tournamentList.get(tournamentList.size()-1).getTournamentId());

	//        			duelの情報をListに詰める
	    			for(Match match: matchList){
	    				List<DuelExp> array = new ArrayList<>();
	    				array.add(duelDAO.getDuelDetail(match.getDuel1()));
	    				array.add(duelDAO.getDuelDetail(match.getDuel2()));
	    				array.add(duelDAO.getDuelDetail(match.getDuel3()));
	    				array.add(duelDAO.getDuelDetail(match.getDuel4()));
	    				duelList.add(array);
	    			}
	    			tournament = tournamentList.get(tournamentList.size()-1);
    			}

        		request.setAttribute("tournament", tournament);
        		request.setAttribute("duelList",duelList);
        		request.setAttribute("matchList",matchList);
        		request.getRequestDispatcher("/customer/main.jsp").forward(request, response); // メイン画面へ

            } else {
                // ログイン失敗
                HttpSession session = request.getSession();
                session.setAttribute("loginError", "無効なメールアドレスまたはパスワードです。");
                response.sendRedirect("/baseball/customer/loginError.jsp"); // ログインページへリダイレクト
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));

        StringBuilder hexString = new StringBuilder(2 * encodedhash.length);
        for (int i = 0; i < encodedhash.length; i++) {
            String hex = Integer.toHexString(0xff & encodedhash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
