package ikari;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.InsertData;

@WebServlet(urlPatterns={"/chapter4"})
public class Hello2 extends HttpServlet{

	public void doGet(HttpServletRequest request,HttpServletResponse response)
		throws IOException{
		System.out.println("chapter4");
		InsertData data = new InsertData();
		data.insertTicketsData();
	}
}
