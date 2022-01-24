package model;

import java.io.Serializable;
import java.util.ArrayList;

//JAVABEANS　refrigerator.jspで使用
public class OverFood implements Serializable {
	private  ArrayList<String> overFood;
	
	public OverFood() { }

	public ArrayList<String> getOverFood() {
		return overFood;
	}

	public void setOverFood(ArrayList<String> overFood) {
		this.overFood = overFood;
	}
}
