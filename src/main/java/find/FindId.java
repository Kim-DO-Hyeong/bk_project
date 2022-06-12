package find;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import util.DatabaseManager;

@WebServlet("/find/id")
public class FindId extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
				
		try {
			conn = DatabaseManager.getConnection();
			
			// 실행할 쿼리 작성
			String sql = "SELECT * FROM memberinfotbl WHERE memberName = ? AND tel = ?";
			
			// 쿼리를 실행하고 결과를 가져올 객체(PreparedStatement) 생성
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, tel);
			
			// 쿼리를 실행하고 결과를 가져옴
			
			rs = pstmt.executeQuery();
			
			// rs의 포인터 이름
			if(rs.next()) {
				response.setContentType("application/json;charset=UTF-8");
				
				PrintWriter output = response.getWriter();
				
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("loginUserId", rs.getString("memberID"));
				jsonObject.put("loginUserName", name);
				
				output.print(jsonObject);
				
				output.close();
			} else {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// DB와 관련된 자원 해제
			DatabaseManager.closeResultset(rs);
			DatabaseManager.closePstmt(pstmt);
			DatabaseManager.closeConn(conn);
		}
		
		
	}

}
