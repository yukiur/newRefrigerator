package model;

public class RegisterUserLogic {
		
		public boolean confirm(String id, String pass, User registerUser) {
			return registerUser.getId().equals(id) && registerUser.getPass().equals(pass);
		}
		
		public boolean testConfirm(String id, String pass) {
			return id.equals("test") && pass.equals("1234");
		}

}
