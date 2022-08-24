package bookmanagement;

public class Book {
	private String tltle; // 도서제목
	private int book_num; // 도서번호(중복허용x)
	private String author; // 작가
	private String publisher; // 출판사
	private String genre; // 장르
	private String category; // 카테고리
	private int year; // 출판년도
	private int book_count; // 책의 재고수량
	
	public String getTltle() {
		return tltle;
	}
	public void setTltle(String tltle) {
		this.tltle = tltle;
	}
	public int getBook_num() {
		return book_num;
	}
	public void setBook_num(int book_num) {
		this.book_num = book_num;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getBook_count() {
		return book_count;
	}
	public void setBook_count(int book_count) {
		this.book_count = book_count;
	}
	
	

}
