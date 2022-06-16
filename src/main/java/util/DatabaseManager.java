package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseManager {
	public static Connection getConnection() {
		
		Connection conn = null;		
		
		try {
			// JDBC 드라이버를 로딩
			Class.forName("org.mariadb.jdbc.Driver");
			
			// DB에 접속, getConnection 연결 정보를 리턴하므로 Connection conn에 저장
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/burgerking?user=root&password=1234");
						
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
		
	}
	
	public static PreparedStatement getPstmt(Connection conn, String sql) {
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return pstmt;
	}
	
	public static void closeResultset(ResultSet rs) {
		try {
			if(rs != null) {
				rs.close();
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void closePstmt(PreparedStatement pstmt) {
		try {
			if(pstmt != null) {
				pstmt.close();
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void closeConn(Connection conn) {
		try {
			if(conn != null) {
				conn.close();
			}				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
