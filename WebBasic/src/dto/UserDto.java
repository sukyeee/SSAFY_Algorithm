package dto;

public class UserDto {
	private int userSeq;
	private String userName;
	private String userEmail;
	
	public UserDto() {}
	public UserDto(int userSeq, String userName, String userEmail) {
		super();
		this.userSeq = userSeq;
		this.userName = userName;
		this.userEmail = userEmail;
	}
	
	public int getUserSeq() {
		return userSeq;
	}
	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	
	
}
