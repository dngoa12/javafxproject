package dao3;

import java.sql.ResultSet;
import java.util.List;

import login.Admin;
import login.Book;
import login.Rental;
import login.User;

public interface DAO {

	public boolean insertUser(User u, int level);
	public boolean checkLogin(String id, String pw);
	
	public boolean checkAdminLogin(String id, String pw);
	
	public boolean insertBook(Book b);
	public boolean modifyBook(Book b);
	public boolean deleteBook(Book b);
	
	public User mypageLogin(String id, String pw);
	public boolean mypageModify(User u, String id5);
	public boolean deleteUser(String id, String pw);
	
	public List<User> allUser();
	public List<Book> allBook();
	public List<Book> searchBook(String title);
	
	public Book eachBook(int num);
	
	public boolean insertRental(int bno, String id5);
	
	public List<Rental> myRental(String id5);
	
	public boolean idOk(String id);// 회원목록 회원가입의 DB에서 아이디 중복확인 메서드
	
	public boolean modifyBannap(int rno, String id5);
}
