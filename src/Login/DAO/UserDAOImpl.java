package Login.DAO;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Login.User;
import Login.service.CommonService;
import Login.service.CommonServiceImpl;

public class UserDAOImpl implements UserDAO{
	Connection con;
	Login.service.CommonService comServ;
	
	public UserDAOImpl() {
		// TODO Auto-generated constructor stub
		comServ = new Login.service.CommonServiceImpl();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@192.168.0.10:1521:xe";
			String users = "system";
			String pass = "oracle";
			
			con = DriverManager.getConnection(url, users, pass);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean insertMember(User u) {
		// TODO Auto-generated method stub
		try {
			String sql = "insert into Users values(?,?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, u.getName());
			pstmt.setString(2, u.getId());
			pstmt.setString(3, u.getPw());
			pstmt.setString(4, u.getBirth());
			pstmt.setString(5, u.getEmail());
			
			int result = pstmt.executeUpdate();

			if(result >= 1) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean checkLogin(String id, String pw) {
		// TODO Auto-generated method stub
		try {
			String sql = "select count(*) from Users where id=? and pw=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			int result = rs.getInt(1);
			if(result == 1) {
				return true;
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public User select(String id) {
		// TODO Auto-generated method stub
		try {
			String sql = "select * from Users where id=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				User u = new User();
				u.setName(rs.getString("name"));
				u.setId(rs.getString("id"));
				u.setPw(rs.getString("pw"));
				u.setBirth(rs.getString("birth"));
				u.setEmail(rs.getString("email"));
				return u;
			}			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean IdCheck(String id) {
		// TODO Auto-generated method stub
		try {
			String sql = "select count(*) from Users where id=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			int result = rs.getInt(1);
			if(result == 1) {
				return true;
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
}






