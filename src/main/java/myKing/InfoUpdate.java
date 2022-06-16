package myKing;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.DatabaseManager;

@WebServlet("/info/update")
public class InfoUpdate extends HttpServlet {
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String phoneNumber = request.getParameter("phoneNumber");
		String gender = request.getParameter("gender");
		String birth = request.getParameter("birth");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DatabaseManager.getConnection();
			
			String sql = "UPDATE memberinfotbl SET phoneNumber = ?, gender = ?, birth = ? WHERE memberID = ?";
			
			pstmt = DatabaseManager.getPstmt(conn, sql);
			pstmt.setString(1, phoneNumber);
			pstmt.setString(2, gender);
			pstmt.setString(3, birth);
			pstmt.setString(4, "as@asd");
			
			int count = pstmt.executeUpdate();
			
			if(count == 1) {
				response.setStatus(HttpServletResponse.SC_OK);
			}else {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DatabaseManager.closePstmt(pstmt);
			DatabaseManager.closeConn(conn);
		}
	
	}

}
