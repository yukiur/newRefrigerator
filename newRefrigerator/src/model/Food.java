package model;

import java.util.Date;
import java.util.Objects;

public class Food implements Comparable<Food>{
		private String kinds;
		private String name;
		private Date expiryDate;
		private int deadline;
		
		
		public boolean equals(Object o) {
			if(this == o) { return true; }
			if(o == null) { return false; }
			if(!(o instanceof Food)) { return false; }
			
			Food f = (Food)o;
			if(this.kinds.equals(f.kinds) && this.name.equals(f.name)) {
				return true; }
			return false;
		}
		
		public int hashCode() {
			return Objects.hash(this.kinds, this.name, this.expiryDate);
		}
		
		public int compareTo(Food obj) {
			return this.expiryDate.compareTo(obj.expiryDate);
		}
		
		
		public String getKinds() { return this.kinds; }
		public void setKinds(String k) { this.kinds = k; }
		public String getName() { return this.name; }
		public void setName(String n) { this.name = n; }
		public Date getExpiryDate() { return this.expiryDate; }
		public void setExpiryDate(Date e) { this.expiryDate = e; }
		public int getDeadline() { return this.deadline; }
		public void setDeadline(int d) { this.deadline = d; }

}
