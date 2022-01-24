package model;

import java.io.Serializable;
import java.util.LinkedHashMap;

//JAVABEANS　refrigerator.jspで使用
public class FoodAndDeadline implements Serializable {
	private LinkedHashMap<String, Integer> foodAndDeadline;	

	public FoodAndDeadline() { }

	
	
	public LinkedHashMap<String, Integer> getFoodAndDeadline() {
		return foodAndDeadline;
	}

	public void setFoodAndDeadline(LinkedHashMap<String, Integer> foodAndDeadline) {
		this.foodAndDeadline = foodAndDeadline;
	}
	
}
