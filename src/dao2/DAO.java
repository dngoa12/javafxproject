package dao2;

import java.sql.ResultSet;
import java.util.List;

import program.Admin;
import program.Book;
import program.Rental;
import program.User;

public interface DAO {

	public boolean insertUser(User u);
	public boolean checkLogin(String id, String pw);
	
	public boolean insertAdmin(Admin a);
	public boolean checkAdminLogin(String id, String pw);
	
	public boolean insertBook(Book b);
	public boolean modifyBook(Book b);
	public boolean deleteBook(Book b);
	
	public List<User> allUser();
	public List<Book> allBook();
	public List<Book> searchBook(String title);
	
	public Book eachBook(int num);
	
	public boolean insertRental(int bno, String id5);
	
	public List<Rental> myRental(String id5);
}
