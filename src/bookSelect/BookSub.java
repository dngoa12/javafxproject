package bookSelect;

public class BookSub {
	Integer bno;
    String bname;
    String author;
    String publisher;
    String b_coment;
    String b_state;
    String img1;
    String img2;
    
	public BookSub(Integer bno, String bname, String author, String publisher, String b_coment,String b_state, String img1,String img2) {
		
		this.bno = bno;
		this.bname = bname;
		this.author = author;
		this.publisher = publisher;
		this.b_coment = b_coment;
		this.b_state = b_state;
		this.img1 = img1;
		this.img2 = img2;
	}


	public Integer getBno() {
		return bno;
	}

	public void setBno(Integer bno) {
		this.bno = bno;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
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

	public String getB_coment() {
		return b_coment;
	}

	public void setB_coment(String b_coment) {
		this.b_coment = b_coment;
	}


	public String getB_state() {
		return b_state;
	}


	public void setB_state(String b_state) {
		this.b_state = b_state;
	}


	public String getImg1() {
		return img1;
	}


	public void setImg1(String img1) {
		this.img1 = img1;
	}


	public String getImg2() {
		return img2;
	}


	public void setImg2(String img2) {
		this.img2 = img2;
	}
	
	
}
