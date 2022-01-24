package model;

import java.io.Serializable;
import java.util.LinkedHashMap;

public class FoodLists implements Serializable {
		private LinkedHashMap<String, LinkedHashMap<String, String>> foodLists;
		
		public FoodLists() { }

		public LinkedHashMap<String, LinkedHashMap<String, String>> getFoodLists() {
			return foodLists;
		}

		public void setFoodLists(LinkedHashMap<String, LinkedHashMap<String, String>> foodLists) {
			this.foodLists = foodLists;
		};
}
