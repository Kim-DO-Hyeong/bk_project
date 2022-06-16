package myKing;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

@WebServlet("/loginstatus")
public class LoginStatus extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 세션에서 로그인 정보를 가져옴
		HttpSession session = request.getSession();
		
		String loginName = (String) session.getAttribute("loginName");
		String memberID = (String) session.getAttribute("memberID");
		
		
		
		JSONObject json = new JSONObject();
		
		// 세션에서 가져온 로그인 정보를 JSON객체를 통해 전달 
		json.put("loginName", "hi");
		json.put("memberID", "id@asd.com");
		
		response.setContentType("application/json;charset=UTF-8");
		
		PrintWriter output = response.getWriter();
		output.print(json);
		output.close();
	
	}
}
