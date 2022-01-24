package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.AllList;
import model.Food;
import model.FoodAndDeadline;
import model.FoodLists;
import model.OverFood;
import model.RegisterFoodLogic;

@WebServlet("/DeleteFoodServlet")
public class DeleteFoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		// RegisterFoodLogicのインスタンスを生成
		RegisterFoodLogic registerFoodLogic = new RegisterFoodLogic();
		
		// 削除用のリクエストパラメータを取得
		request.setCharacterEncoding("UTF-8");
		String name_deleteFood = request.getParameter("branch_delete");
		
		// セッションスコープに保存されたAllListを取得
		AllList allList = (AllList) session.getAttribute("allList");
		if (allList == null) {
			allList = new AllList();
		}
		ArrayList<Food> pastAllList = null;
		try {
			pastAllList = allList.getAllList();
		} catch (NullPointerException e) {
			System.out.println("nullエラーです");
		}
		if (pastAllList == null) {
			pastAllList = new ArrayList<Food>();
			allList.setAllList(pastAllList);
		}
		// AllListから選択した食べ物を削除する
		registerFoodLogic.deleteFromAllList(pastAllList, name_deleteFood);
		//　セッションスコープに更新したAllListを保存
		session.setAttribute("allList", allList);
		
		// セッションスコープに保存されたFoodListsを取得
		FoodLists foodLists = (FoodLists) session.getAttribute("foodLists");
		if (foodLists == null) {
			foodLists = new FoodLists();
		}
		LinkedHashMap<String, LinkedHashMap<String, String>> newFoodLists = null;
		try {
			newFoodLists = foodLists.getFoodLists();
		} catch (NullPointerException e) {
			System.out.println("nullエラーです");
		}		
		if (newFoodLists == null) {
			newFoodLists = new LinkedHashMap<String, LinkedHashMap<String, String>>();
			foodLists.setFoodLists(newFoodLists);
		}
		// FoodListsから選択した食べ物を削除する
		registerFoodLogic.deleteFromfoodLists(newFoodLists, name_deleteFood);
		// セッションスコープに更新したFoodListsを保存
		session.setAttribute("foodLists", foodLists);

		// セッションスコープに保存されたFoodAndDeadline(賞味期限内の食べ物の【名前】と【期限】)を取得	
		FoodAndDeadline foodAndDeadline = (FoodAndDeadline) session.getAttribute("foodAndDeadline");
		if (foodAndDeadline == null) {
			foodAndDeadline = new FoodAndDeadline();
		}
		LinkedHashMap<String, Integer> fad = null;
		try {
			fad = foodAndDeadline.getFoodAndDeadline(); 
		} catch(NullPointerException e) {
			System.out.println("nullエラーです");
		}
		if (fad == null) {
			fad = new LinkedHashMap<String, Integer>();
		}
		// FoodAndDeadlineから選択した食べ物を削除
		registerFoodLogic.deleteFromFoodAndDeadline(fad, name_deleteFood);
		// セッションスコープに更新したFoodAndDeadline(賞味期限内の食べ物の【名前】と【期限】)を保存
		foodAndDeadline.setFoodAndDeadline(fad);
		session.setAttribute("foodAndDeadline", foodAndDeadline);
				
		// セッションスコープに保存されたOverFood(賞味期限切れの食べ物の【名前】)を取得し新しく設定
		OverFood overFood = (OverFood) session.getAttribute("overFood");
		if (overFood == null) {
			overFood = new OverFood();
		}
		ArrayList<String> ovf = null;
		try {
			ovf = overFood.getOverFood();
		} catch(NullPointerException e) {
			System.out.println("nullエラーです");
		}
		if (ovf == null) {
			ovf = new ArrayList<String>();
		}
		// OverFoodから選択した食べ物を削除する
		registerFoodLogic.deleteFromOverFood(ovf, name_deleteFood);
		// セッションスコープに更新したOverFood(賞味期限切れの食べ物の【名前】)を保存
		overFood.setOverFood(ovf);
		session.setAttribute("overFood", overFood);
		
		// フォワード
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("/WEB-INF/jsp/refrigerator.jsp");
		dispatcher.forward(request, response);
		
		
	}

}
