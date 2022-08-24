package Login.DAO;

import Login.User;

public interface UserDAO {
	public boolean insertMember(User u);
	public boolean checkLogin(String id, String pw);
	public boolean idokCheck(String id);
	
}