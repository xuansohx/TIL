package chat;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({ "/ChatServlet", "/chat" }) // 객체가 실행될 때 한 번만 동작되는 부분
public class ChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Client client;
	
    public ChatServlet() { // 내가 갈 곳
    	try {
    		client = new Client("70.12.60.110",1234);
		} catch (IOException e) {
			e.printStackTrace();
		}       
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 메시지가 여기로 오면
		String msg = request.getParameter("msg");
		client.sendMsg(msg);
	}


}
