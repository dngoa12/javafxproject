package bookmanagement;

public class User {
	private String name; // 회원 이름
	private String id; // 회원 아이디
	private String pw; // 회원 비밀번호
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}

}
