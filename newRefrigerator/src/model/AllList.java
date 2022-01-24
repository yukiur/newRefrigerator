package model;

import java.io.Serializable;
import java.util.ArrayList;

//JAVABEANS　refrigerator.jspで使用
public class AllList implements Serializable {
	private  ArrayList<Food> allList;
	
	public AllList() { }

	public ArrayList<Food> getAllList() {
		return allList;
	}

	public void setAllList(ArrayList<Food> allList) {
		this.allList = allList;
	}
	
}
