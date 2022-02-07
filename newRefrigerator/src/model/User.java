package model;

import java.io.Serializable;

public class User implements Serializable {
		private String id;
		private String pass;
		
		public User(String id, String pass) {
			this.id = id;
			this.pass = pass;
		}

		public String getId() {	return id; }

		public String getPass() { return pass; }
}
