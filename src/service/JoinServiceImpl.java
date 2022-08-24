package service;

public class JoinServiceImpl implements JoinService{

	

	@Override
	public boolean comparePw(String pw1, String pw2) {
		if (pw1.equals(pw2)) {
			return false;
		}
		return true;
	}

}
