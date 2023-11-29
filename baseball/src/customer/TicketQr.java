package customer;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

@WebServlet("/customer/TicketQr")
public class TicketQr extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
//    	チケットID
    	String ticketsId = request.getParameter("ticketsId");
//    	第何回
    	int ordinalNum = Integer.parseInt(request.getParameter("ordinalNum"));
//    	大会名
    	String tournamentName = request.getParameter("tournamentName");
//    	日にち
    	String dateStr = request.getParameter("dateStr");
//    	曜日
    	String eventDayOfWeek = request.getParameter("eventDayOfWeek");
//    	QRコードに埋め込む情報
        String text = ticketsId;

        BitMatrix bitMatrix = null;
        BufferedImage image;

        if(true){
        // エンコーディング設定
        HashMap<EncodeHintType, String> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		try {
			 // QRコード生成
            bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, 200, 200, hints);
		} catch (WriterException e) {
				e.printStackTrace();
		}
		// BitMatrixからBufferedImageに変換
		image = MatrixToImageWriter.toBufferedImage(bitMatrix);
        }

		request.setAttribute("bImage", image);
    	request.setAttribute("ticketsId", ticketsId);
    	request.setAttribute("ordinalNum", ordinalNum);
    	request.setAttribute("tournamentName", tournamentName);
    	request.setAttribute("dateStr", dateStr);
    	request.setAttribute("eventDayOfWeek", eventDayOfWeek);
        request.getRequestDispatcher("/customer/ticketQr.jsp").forward(request, response);
    }
}
