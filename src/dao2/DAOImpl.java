package dao2;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

import program.Admin;
import program.Book;
import program.Rental;
import program.User;

public class DAOImpl implements DAO{
	
	Connection con;
	
	public DAOImpl() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
			String user = "c##baek";
			String pwd = "1075";
			
			con = DriverManager.getConnection(url, user, pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean insertUser(User u) {
		try {
			String sql = "insert into bookuser values(?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, u.getName());
			ps.setString(2, u.getId());
			ps.setString(3, u.getPw());
			ps.setString(4, u.getEmail());
			ps.setString(5, u.getBirth());
			
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
			String sql = "select count(*) from bookuser where id=? and pw=?";
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
	public boolean insertAdmin(Admin a) {
		try {
			String sql = "insert into bookadmin values(?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, a.getName());
			ps.setString(2, a.getId());
			ps.setString(3, a.getPw());
			
			
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
	public boolean checkAdminLogin(String id, String pw) {
		try {
			String sql = "select count(*) from bookadmin where id=? and pw=?";
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
	public List<User> allUser() {
		List<User> list = new ArrayList<User>();
		try {
			String sql = "select * from bookuser";
			PreparedStatement ps = con.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				User u = new User();
				u.setName(rs.getString(1));
				u.setId(rs.getString(2));
				u.setPw(rs.getString(3));
				u.setEmail(rs.getString(4));
				u.setBirth(rs.getString(5));
				list.add(u);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean insertBook(Book b) {
		try {
			String sql = "insert into bookinfo values(?,?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, b.getTitle());
			ps.setInt(2, b.getBno());
			ps.setString(3, b.getAuthor());
			ps.setString(4, b.getPublisher());
			ps.setString(5, b.getGenre());
			ps.setString(6, b.getCategory());
			ps.setInt(7, b.getYear());
			ps.setInt(8, b.getBcnt());
			
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
	public boolean modifyBook(Book b) {
		try {
			String sql = "update bookinfo set year=?, bcnt=? where bno=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, b.getYear());
			ps.setInt(2, b.getBcnt());
			ps.setInt(3, b.getBno());
			
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
			String sql = "delete from bookinfo where bno=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, b.getBno());
			
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
			String sql = "select * from bookinfo";
			PreparedStatement ps = con.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Book b = new Book();
				b.setTitle(rs.getString(1));
				b.setBno(rs.getInt(2));
				b.setAuthor(rs.getString(3));
				b.setPublisher(rs.getString(4));
				b.setGenre(rs.getString(5));
				b.setCategory(rs.getString(6));
				b.setYear(rs.getInt(7));
				b.setBcnt(rs.getInt(8));
				
				list.add(b);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Book eachBook(int num) {
		try {
			String sql = "select * from bookinfo where bno=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, num);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				Book book = new Book();
				book.setTitle(rs.getString("title"));
				book.setBno(rs.getInt("bno"));
				book.setAuthor(rs.getString("author"));
				book.setPublisher(rs.getString("publisher"));
				book.setGenre(rs.getString("genre"));
				book.setCategory(rs.getString("category"));
				book.setYear(rs.getInt("year"));
				book.setBcnt(rs.getInt("bcnt"));
				
				return book;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Book> searchBook(String title) {
		List<Book> list = new ArrayList<Book>();
		try {
			String sql = "select * from bookinfo where title=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, title);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Book b = new Book();
				b.setTitle(rs.getString(1));
				b.setBno(rs.getInt(2));
				b.setAuthor(rs.getString(3));
				b.setPublisher(rs.getString(4));
				b.setGenre(rs.getString(5));
				b.setCategory(rs.getString(6));
				b.setYear(rs.getInt(7));
				b.setBcnt(rs.getInt(8));
				
				list.add(b);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean insertRental(int bno, String id5) {
		try {
			
			String sql = "insert into rental_book values(rno_seq.nextval,?,?,sysdate,sysdate+7,'')";
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1, bno);
			ps.setString(2, id5);
			
			
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
	public List<Rental> myRental(String id5) {
		List<Rental> list = new ArrayList<Rental>();
		try {
			String sql = "select * from rental_book where id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id5);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Rental r = new Rental();
				r.setRno(rs.getInt(1));
				r.setBno(rs.getInt(2));
				r.setId(id5);
				r.setOut_date(rs.getDate(4));
				r.setDue_date(rs.getDate(5));
				r.setIn_date(rs.getDate(6));
				
				list.add(r);
				
			
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	
	
	
	
	

}
