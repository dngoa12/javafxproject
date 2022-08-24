package dao;

import java.util.List;

import bookmanagement.Admin;
import bookmanagement.Book;
import bookmanagement.User;

public interface DAO {
	
	public boolean insertAdmin(Admin a); // db에 관리자 가입시 내용을 추가하는 메서드
	public boolean insertUser(User u); // db에 유저 가입시 내용을 추가하는 메서드
	public boolean checkLogin(String id, String pw);
	public boolean checkUserLogin(String id, String pw);
	
	
	public boolean insertBook(Book b); // db에 도서정보를 추가하는 메서드
	public boolean deleteBook(Book b); // db에 도서정보를 삭제하는 메서드

	public List<Book> allBook(); // 도서목록 테이블뷰에 db에 저장된 모든 책정보를 표시하는 메서드
	public List<User> allUser(); // 회원목록 테이블뷰에 db에 저장된 모든 유저정보를 표시하는 메서드

	
	public Book eachBook(int book_num); // 도서목록 테이블뷰의 특정 로우를 클릭했을 때 각 도서의 정보를 보여주는 메서드
	
	
}
