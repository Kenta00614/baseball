package ticketsProduct;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.InsertData;

@WebServlet(urlPatterns={"/chapter3"})
public class Hello extends HttpServlet{

	public void doGet(HttpServletRequest request,HttpServletResponse response)
		throws IOException{
		InsertData data = new InsertData();
		data.insertSeatData();
	}
}
