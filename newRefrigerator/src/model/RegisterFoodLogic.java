package model;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class RegisterFoodLogic {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy/MM/dd");

// AllListから食べ物を削除するメソッド
	public void deleteFromAllList(ArrayList<Food> allList, String name) {
		if (!(allList.isEmpty())) {
			ArrayList<Integer> index = new ArrayList<Integer>();
			for(Food food: allList) {
				if(food.getName().matches(name)) {
					int i = allList.indexOf(food);
					index.add(i);
				}
			}
			for(int i: index) {
				allList.remove(i);
			}
		}
	}
// FoodListsから食べ物を削除するメソッド
	public void deleteFromfoodLists (LinkedHashMap<String, LinkedHashMap<String, String>> foodLists, String name) {
		if (!(foodLists.isEmpty())) {
			for(String kind: foodLists.keySet())	{
				LinkedHashMap<String, String> name_expiration = null;
				try {
					name_expiration = foodLists.get(kind);
				} catch (NullPointerException e) {
					System.out.println("nullエラーです");
				}
				if (name_expiration != null) {
					if(name_expiration.containsKey(name)) {
						name_expiration.remove(name);
					}
				}
			}		
		}
	}
	public void deleteFromFoodAndDeadline (LinkedHashMap<String, Integer> fad, String name) {
		if(!(fad.isEmpty())) {
			if(fad.containsKey(name)) {
				fad.remove(name);
			}
		}
	}
	public void deleteFromOverFood (ArrayList<String> ovf, String name) {
		if(!(ovf.isEmpty())) {
			ArrayList<Integer> index = new ArrayList<Integer>();
			for(String name_OverFood: ovf) {
				if(name_OverFood.equals(name)) {
					int i = ovf.indexOf(name_OverFood);
					index.add(i);
				}
			}
			for(int i: index) {
				ovf.remove(i);
			}
		}
	}
	
// 入力された食べ物を追加して、新しいArrayList<Food> allListをつくる
	
	// 入力された値をパラメータ配列にして配列を格納するArrayList<String>をつくる

	public ArrayList<String[]> parameterMaker(String name_meat, String expiration_meat, String name_vegetable, String expiration_vegetable, String name_fruit, String expiration_fruit, String name_fish, String expiration_fish, String name_dry, String expiration_dry, String name_mushroom, String expiration_mushroom, String name_egg, String expiration_egg, String name_potato, String expiration_potato, String name_bread, String expiration_bread, String name_rice, String expiration_rice, String name_milk, String expiration_milk, String name_bean, String expiration_bean, String name_noodles, String expiration_noodles) {
		
		ArrayList<String[]> parameter = new ArrayList<String[]>();
		String[] para1 = {"肉",name_meat,expiration_meat };
		parameter.add(para1);
		String[] para2 = { "野菜",name_vegetable,expiration_vegetable };
		parameter.add(para2);
		String[] para3 ={ "果物",name_fruit,expiration_fruit };
		parameter.add(para3);
		String[] para4 ={ "魚介類",name_fish,expiration_fish };
		parameter.add(para4);
		String[] para5 ={ "乾物",name_dry,expiration_dry };
		parameter.add(para5);
		String[] para6 ={ "きのこ",name_mushroom,expiration_mushroom };
		parameter.add(para6);
		String[] para7 ={ "卵",name_egg,expiration_egg };
		parameter.add(para7);
		String[] para8 ={ "いも類",name_potato,expiration_potato };
		parameter.add(para8);
		String[] para9 ={ "パン",name_bread,expiration_bread };
		parameter.add(para9);
		String[] para10 ={ "米類",name_rice,expiration_rice };
		parameter.add(para10);
		String[] para11 ={ "乳製品",name_milk,expiration_milk };
		parameter.add(para11);
		String[] para12 ={ "豆類",name_bean,expiration_bean };
		parameter.add(para12);
		String[] para13 ={ "麺類",name_noodles,expiration_noodles };
		parameter.add(para13);
		
		return parameter;
	}

	//　食材の賞味期限の入力ミスがないか確認するメソッド
		public boolean isValidDate(String date) {
			if (date == null) { return false; }
			boolean b = date.matches("[0-9]{4}/[0-1][0-9]/[0-3][0-9]");
			if (b) {	
				String m1 = date.substring(5,6);
				String m2 = date.substring(6,7);
				String m3 = date.substring(8,9);
				String m4 = date.substring(9,10);
				if(m1.matches("0")) {
					if(m2.matches("[13578]")) {
						switch (m3) {
						case "0":
							return m4.matches("[1-9]");
							
						case "1":
						case "2":
							return m4.matches("[0-9]");
						
						case "3":
							return m4.matches("[0-1]");
						}
					}
					if(m2.matches("[469]")) {
						switch (m3) {
						case "0":
							return m4.matches("[1-9]");
						
						case "1":
						case "2":
							return m4.matches("[0-9]");
						
						case "3":
							return m4.matches("[0]");
						}
					}
					if(m2.matches("[2]")) {
						switch (m3) {
						case "0":
							return m4.matches("[1-9]");
						
						case "1":
						case "2":
							return m4.matches("[0-9]");
						case "3":
							return false;
						}
					}
				}
				if(m1.matches("1")) {
					if(m2.matches("[02]")) {
						switch (m3) {
						case "0":
							return m4.matches("[1-9]");
							
						case "1":
						case "2":
							return m4.matches("[0-9]");
						
						case "3":
							return m4.matches("[0-1]");
						}
					}
					
					if(m2.matches("[1]")) {
						switch (m3) {
						case "0":
							return m4.matches("[1-9]");
							
						case "1":
						case "2":
							return m4.matches("[0-9]");
						
						case "3":
							return m4.matches("[0]");
						}
					}
				}
			}
			return false;
		}
		
		// 消費期限の残りの日数を計算するメソッド
		public int calcLogic(String time) {
			LocalDate now  = LocalDate.now();
			LocalDate exp = LocalDate.parse(time, fmt);
			long l = ChronoUnit.DAYS.between(now, exp);
			int i = (int)l;
			if(now.isAfter(exp)) {
				return 0;
			} else {
				return i;
			}
		}

	
		// 新しいAllList（全ての食べ物リスト）を作るメソッド
		public ArrayList<Food> newAllListMaker(ArrayList<String[]> parameter, ArrayList<Food> pastAllList) {
			ArrayList<Food> newAllList = new ArrayList<Food>();
			for(String[] p: parameter) {
				Food f1 = new Food();
				f1.setKinds(p[0]);
				f1.setName(p[1]);
				try {
				if(isValidDate(p[2])) {
					f1.setExpiryDate(sdf.parse(p[2]));
				} 
				} catch (Exception e) {
					System.out.println("日付の入力方法を見直してください");
				}
				if (f1.getExpiryDate() != null) {
					int deadline = calcLogic(p[2]);
					f1.setDeadline(deadline);
				}
				if (f1.getName() != null && f1.getKinds() != null && f1.getExpiryDate() != null) {
					if (!(pastAllList.contains(f1))) {
						pastAllList.add(f1);
					}
				}
				
			}
			newAllList = pastAllList;
			
			return newAllList;
		}
		
// FoodAndDeadline(賞味期限内の食べ物の【名前】と【期限】)を保存するメソッド
	public void MapListOfPreDeadlineSetter(ArrayList<Food> allList, LinkedHashMap<String, Integer> fad) {
		ArrayList<Food> preDeadlineFood = null; 
		try {
			preDeadlineFood = (ArrayList<Food>) allList.stream().filter(f -> f.getDeadline() > 0).sorted((x,y) -> x.getExpiryDate().compareTo(y.getExpiryDate())).collect(Collectors.toList());	
		} catch (NullPointerException e) {
			System.out.println("nullエラーです");
		}
		if (preDeadlineFood == null) {
			preDeadlineFood = new ArrayList<Food>();
		}
		for(Food food: preDeadlineFood) {
			try {
			fad.put(food.getName(), food.getDeadline());
			} catch(NullPointerException e) {
				System.out.println("nullエラーです");
			}
		}
		LinkedHashMap<String, Integer> newFad = fad.entrySet().stream().sorted(Entry.comparingByValue()).collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		fad = newFad;
		
	}
	
// OverFood(賞味期限切れの食べ物の【名前】)を保存するメソッド
	public void ListOfOverFoodSetter(ArrayList<Food> allList, ArrayList<String> ovf) {
		ArrayList<Food> overDeadlineFood = null;
		try {
			overDeadlineFood = (ArrayList<Food>) allList.stream().filter(f -> f.getDeadline() == 0).sorted((x,y) -> x.getExpiryDate().compareTo(y.getExpiryDate())).collect(Collectors.toList());
		} catch (NullPointerException e) {
			System.out.println("nullエラーです");
		}
		if (overDeadlineFood == null) {
			overDeadlineFood = new ArrayList<Food>();
		}
		for(Food food: overDeadlineFood) {
			try {
				if (!(ovf.contains(food.getName()))) {
					ovf.add(food.getName());
				}
			} catch(NullPointerException e) {
				System.out.println("nullエラーです");
			}
		}
	}
	
// 新しいFoodLists（種類ごとの食べ物リスト）をつくるメソッド
	public void foodListsMaker (LinkedHashMap<String, LinkedHashMap<String, String>> foodLists, ArrayList<String[]> parameter) {
		for(String[] para: parameter) {
			String kind = para[0];
			String name = para[1];
			String expiration = para[2];
			if(isValidDate(expiration)) {
				LinkedHashMap<String, String> name_expiration = null;
				try {
					name_expiration = foodLists.get(kind);
				} catch (NullPointerException e) {
					System.out.println("nullエラーです");
				}
				if (name_expiration == null) {
					name_expiration = new LinkedHashMap<String, String>();
					foodLists.put(kind, name_expiration);
				}
				name_expiration.put(name, expiration);
			}	
		}		
	}
}

