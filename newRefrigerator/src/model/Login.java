package model;

import java.io.Serializable;

public class Login implements Serializable {
		private String login;
		
		public Login(String success) {
			login = success;
		}

		public String getLogin() {
			return login;
		}

		public void setLogin(String login) {
			this.login = login;
		}
}
