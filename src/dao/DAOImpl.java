package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bookmanagement.Admin;
import bookmanagement.Book;
import bookmanagement.User;



public class DAOImpl implements DAO{
	
	Connection con;
	
	
	public DAOImpl() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@192.168.0.10:1521:xe";
			String user = "system";
			String pass = "oracle";
			
			con = DriverManager.getConnection(url, user, pass);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public boolean insertAdmin(Admin a) {
		try {
			String sql = "insert into admin values(?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, a.getName());
			ps.setString(2, a.getId());
			ps.setString(3, a.getPw());
			ps.setString(4, a.getBirth());
			ps.setString(5, a.getEmail());	
			int res = ps.executeUpdate();
			
			if (res>=1) {
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}


	@Override
	public boolean checkLogin(String id, String pw) {
		try {
			String sql = "select count(*) from admin where id=? and pw=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pw);
			
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			int res = rs.getInt(1);
			if (res==1) {
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}


	@Override
	public boolean insertBook(Book b) {
		try {
			String sql = "insert into book values(?,?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, b.getTltle());
			ps.setInt(2, b.getBook_num());
			ps.setString(3, b.getAuthor());
			ps.setString(4, b.getPublisher());
			ps.setString(5, b.getGenre());
			ps.setString(6, b.getCategory());
			ps.setInt(7, b.getYear());
			ps.setInt(8, b.getBook_count());
			
			int res = ps.executeUpdate();
			
			if (res>=1) {
				return true;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}


	@Override
	public boolean deleteBook(Book b) {
		try {
			String sql = "delete from book where book_num=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, b.getBook_num());
			
			int res = ps.executeUpdate();
			
			if (res>=1) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}


	@Override
	public List<Book> allBook() {
		List<Book> list = new ArrayList<Book>();
		
		try {
			String sql = "select * from book";
			PreparedStatement ps = con.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Book b = new Book();
				b.setTltle(rs.getString(1));
				b.setBook_num(rs.getInt(2));
				b.setAuthor(rs.getString(3));
				b.setPublisher(rs.getString(4));
				b.setGenre(rs.getString(5));
				b.setCategory(rs.getString(6));
				b.setYear(rs.getInt(7));
				b.setBook_count(rs.getInt(8));
				
				list.add(b);
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	


	@Override
	public boolean insertUser(User u) {
		try {
			String sql = "insert into member values(?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, u.getName());
			ps.setString(2, u.getId());
			ps.setString(3, u.getPw());
			ps.setString(4, u.getBirth());
			ps.setString(5, u.getEmail());
			int res = ps.executeUpdate();
			
			if (res>=1) {
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}


	


	@Override
	public List<User> allUser() {
		List<User> list = new ArrayList<User>();
		try {
			String sql = "select * from member";
			PreparedStatement ps = con.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				User u = new User();
				u.setName(rs.getString(1));
				u.setId(rs.getString(2));
				u.setPw(rs.getString(3));
				u.setBirth(rs.getString(4));
				u.setEmail(rs.getString(5));
				
				list.add(u);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	@Override
	public Book eachBook(int book_num) {
		try {
			String sql = "select * from Book where book_num=?";
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1, book_num);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				
				Book book = new Book();
				book.setTltle(rs.getString("title"));
				book.setBook_num(rs.getInt("book_num"));
				book.setAuthor(rs.getString("author"));
				book.setPublisher(rs.getString("publisher"));
				book.setGenre(rs.getString("genre"));
				book.setCategory(rs.getString("category"));
				book.setYear(rs.getInt("year"));
				book.setBook_count(rs.getInt("book_count"));
				
				return book;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public boolean checkUserLogin(String id, String pw) {
		try {
			String sql = "select count(*) from member where id=? and pw=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pw);
			
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			int res = rs.getInt(1);
			if (res==1) {
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}


	@Override
	public boolean idOk(String id) {	//관리자,사용자 회원가입시 id 중복체크
		// TODO Auto-generated method stub

		try {
			String sql = "select count(*) from 	member where id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);

			ResultSet rs = ps.executeQuery();

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


