package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class DatabaseDAO {

	static private DatabaseDAO instance;
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private DatabaseDAO() {
		try {
			String dbURL = "jdbc:mysql://localhost:3306/java"
					+ "?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=utf8";
			String dbID = "root";
			String dbPassword = "0000";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID,  dbPassword);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static DatabaseDAO getInstance() {
		if(instance == null) {
			instance = new DatabaseDAO();
		}	
		return instance;
	}
	
	public boolean checkUser(String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "SELECT * FROM user where id=?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return true; 
			} else {
				return false; 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false; 
	}
	
	public int join(String id, String password) {
		String SQL = "INSERT INTO user VALUES(?, ?)";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
	
			return pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1; 
	}
	
	public int addNewPath(String startPoint, String endPoint, String distance, String path, String userID) {
		String SQL = "INSERT INTO path VALUES(?, ?, ?, ?,now(), ?)";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, startPoint);
			pstmt.setString(2, endPoint);
			pstmt.setString(3, distance);
			pstmt.setString(4, path);
			pstmt.setString(5, userID);
			
			return pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1; 
	}
	
	public boolean login(String id, String password) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "SELECT * FROM user where id=? and password=?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return true; 
			} else {
				return false; 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false; 
	}
	
	
	
	public boolean isPresence() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "SELECT * FROM path";
		try {
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
		
			if(rs.next()) {
				return true; 
			} else {
				return false; 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false; 
	}
	
	public boolean checkPath(String startPoint, String endPoint, String id) {		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "SELECT * FROM path WHERE startPoint=? and endPoint=? and id=?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, startPoint);
			pstmt.setString(2, endPoint);
			pstmt.setString(3, id);
			rs = pstmt.executeQuery();
		
			if(rs.next()) {
				return true; 
			} else {
				return false; 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false; 
	}
	
	public ArrayList<Data> getUser(){
		String SQL = "SELECT * FROM user";
		ArrayList<Data> datas = new ArrayList<>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				datas.add(
							new Data(
										rs.getString("id"),
										rs.getString("password")
									)
						 );
			}
			return datas;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	public String getPath(String startPoint, String endPoint, String id) {
		String SQL = "select path from path where startPoint=? and endPoint=? and id=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, startPoint);
			pstmt.setString(2, endPoint);
			pstmt.setString(3, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getString("path");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Data> getData(String id) {
		String SQL = "SELECT * FROM path WHERE id=?";
		ArrayList<Data> datas = new ArrayList<>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				datas.add(
							new Data
									( 
										rs.getString("startPoint"),
										rs.getString("endPoint"),
										rs.getString("distance"),
										rs.getString("path"),
										rs.getDate("searchDate"),
										rs.getString("id")
									)
						 );
			}
			return datas;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	public int deletePath(String startPoint, String endPoint, String id) {		
		PreparedStatement pstmt = null;
		String SQL = "delete FROM path WHERE startPoint=? and endPoint=? and id=?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, startPoint);
			pstmt.setString(2, endPoint);
			pstmt.setString(3, id);
			
			return pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; 
	}
	
	public int deleteUser(String id) {		
		PreparedStatement pstmt = null;
		String SQL = "delete FROM user WHERE id=?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, id);
			
			return pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; 
	}
	
	public int resetPath(String id) {		
		PreparedStatement pstmt = null;
		String SQL = "delete FROM path WHERE id=?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, id);
			
			return pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; 
	}
	
	public int updateUser(String id, String password) {
		PreparedStatement pstmt = null;
		String SQL = "update user set password=? where id=?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, password);
			pstmt.setString(2, id);			
			return pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; 
	}
}
