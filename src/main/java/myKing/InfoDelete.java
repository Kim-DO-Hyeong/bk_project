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

@WebServlet("/info/delete")
public class InfoDelete extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DatabaseManager.getConnection();
			
			String sql = "DELETE FROM memberinfotbl WHERE memberID = ?";
			
			pstmt = DatabaseManager.getPstmt(conn, sql);
			pstmt.setString(1, "아이디");
			
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
