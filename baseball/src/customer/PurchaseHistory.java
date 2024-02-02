package customer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.PurchaseExp;
import bean.Spectator;
import common.Constants;
import dao.PurchaseDAO;

@WebServlet("/customer/PurchaseHistory")
public class PurchaseHistory extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
    }

    @SuppressWarnings({ "unchecked" })
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session=request.getSession();
//    	会員情報
    	List<Spectator> spectator = (List<Spectator>)session.getAttribute("spectatorIds");

//    	全ての購入チケット
    	List<PurchaseExp> purchaseAllList=new ArrayList<>();
//    	購入情報ごとのチケット枚数
    	List<Integer> ticketNumList = new ArrayList<>();
//    	購入情報ごとの情報
    	List<PurchaseExp> purchaseList = new ArrayList<>();
//    	チケットの値段(座種大人の値段、合計金額ではない)
    	List<Integer> ticketPrice = new ArrayList<>();

    	// 観戦客IDがある場合
    	if (spectator != null) {
    	    PurchaseDAO purchaseDAO = new PurchaseDAO();
    	    try {
    	        // チケットの履歴を取得
    	        purchaseAllList = purchaseDAO.getPurchaseHistory(spectator.get(0).getSpectatorId());
    	        int beforePurchaseId = -1;
    	        int ticketNum = 0;

    	        for (PurchaseExp purchase : purchaseAllList) {

    	            // 現在のpurchaseIdが前のものと異なるかを確認
    	            if (purchase.getPurchaseId() != beforePurchaseId) {
    	                if (beforePurchaseId != -1) {
    	                    ticketNumList.add(ticketNum);
    	                }
    	                beforePurchaseId = purchase.getPurchaseId();
    	                ticketNum = 1;
    	                purchaseList.add(purchase);
    	                ticketPrice.add(Constants.SEAT_PRICE.get(purchase.getSeatType()));
    	            } else{
    	                ticketNum++;
//    	                合計料金計算
    	                int nowPrice=ticketPrice.get(ticketPrice.size()-1);
//    	                子供チケットの時
    	                if(purchase.isChild()){
    	                	nowPrice += Constants.CHILD_SEAT_PRICE.get(purchase.getSeatType());
    	                }else{
    	                	nowPrice += Constants.SEAT_PRICE.get(purchase.getSeatType());
    	                }
    	                ticketPrice.set(ticketPrice.size()-1,nowPrice);
    	            }
    	        }

    	        // 最後のpurchaseIdのチケット枚数を追加
    	        if (beforePurchaseId != -1) {
    	            ticketNumList.add(ticketNum);
    	        }
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	    }
    	}

    	request.setAttribute("purchaseAllList", purchaseAllList);
    	request.setAttribute("purchaseList", purchaseList);
    	request.setAttribute("ticketNumList", ticketNumList);
    	request.setAttribute("ticketPrice", ticketPrice);
        request.getRequestDispatcher("/customer/purchaseHistory.jsp").forward(request, response);
    }
}
