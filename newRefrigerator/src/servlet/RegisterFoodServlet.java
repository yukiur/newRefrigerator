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
import model.Login;
import model.OverFood;
import model.RegisterFoodLogic;

@WebServlet("/RegisterFoodServlet")
public class RegisterFoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		// セッションスコープの有効期間を設定
		if (session == null) {
			System.out.println("セッションがありません");
			} else {
			session.setMaxInactiveInterval(1800);
		}	
		// フォワード先
		String forwardPath = null;
		Login login = (Login) session.getAttribute("login");
		try {
			if(login.getLogin().equals("success")) {
				forwardPath = "/WEB-INF/jsp/refrigerator.jsp";
			} else {
				forwardPath = "/WEB-INF/jsp/loginFail.jsp";
			}
		} catch (NullPointerException e) {
			forwardPath = "/WEB-INF/jsp/loginFail.jsp";
		}
		// フォワード
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// RegisterFoodLogicのインスタンスを生成
		RegisterFoodLogic registerFoodLogic = new RegisterFoodLogic();
		
		
		// リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String name_meat = request.getParameter("branch_meat");
		String expiration_meat = request.getParameter("expiration_meat");

		String name_vegetable = request.getParameter("branch_vegetable");
		String expiration_vegetable = request.getParameter("expiration_vegetable");

		String name_fruit = request.getParameter("branch_fruit");
		String expiration_fruit = request.getParameter("expiration_fruit");

		String name_fish = request.getParameter("branch_fish");
		String expiration_fish = request.getParameter("expiration_fish");

		String name_dry = request.getParameter("branch_dry");
		String expiration_dry = request.getParameter("expiration_dry");

		String name_mushroom = request.getParameter("branch_mushroom");
		String expiration_mushroom = request.getParameter("expiration_mushroom");

		String name_egg = request.getParameter("branch_egg");
		String expiration_egg = request.getParameter("expiration_egg");

		String name_potato = request.getParameter("branch_potato");
		String expiration_potato = request.getParameter("expiration_potato");

		String name_bread = request.getParameter("branch_bread");
		String expiration_bread = request.getParameter("expiration_bread");

		String name_rice = request.getParameter("branch_rice");
		String expiration_rice = request.getParameter("expiration_rice");

		String name_milk = request.getParameter("branch_milk");
		String expiration_milk = request.getParameter("expiration_milk");

		String name_bean = request.getParameter("branch_bean");
		String expiration_bean = request.getParameter("expiration_bean");

		String name_noodles = request.getParameter("branch_noodles");
		String expiration_noodles = request.getParameter("expiration_noodles");
		
		// 保存用パラメータを作成
		ArrayList<String[]> parameter = registerFoodLogic.parameterMaker(name_meat,expiration_meat,name_vegetable,expiration_vegetable,name_fruit,expiration_fruit,name_fish,expiration_fish,name_dry,expiration_dry,name_mushroom,expiration_mushroom,name_egg,expiration_egg,name_potato,expiration_potato,name_bread,expiration_bread,name_rice,expiration_rice,name_milk,expiration_milk,name_bean,expiration_bean,name_noodles,expiration_noodles);
		
		
		// セッションスコープを取得
		HttpSession session = request.getSession();
		// セッションスコープの有効期間を設定
		if (session == null) {
			System.out.println("セッションがありません");
			} else {
			session.setMaxInactiveInterval(1800);
		}
		

// AllListを編集
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
		//　AllListに取得した食べ物を追加して更新
		if (pastAllList == null) {
			pastAllList = new ArrayList<Food>();
		}
		ArrayList<Food> newAllList = registerFoodLogic.newAllListMaker(parameter, pastAllList);
		allList.setAllList(newAllList);
		
		// セッションスコープに更新したAllListを保存
		session.setAttribute("allList", allList);
		
// FoodListsを編集
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
		// FoodListsに取得した食べ物を追加して更新する
		if (newFoodLists == null) {
			newFoodLists = new LinkedHashMap<String, LinkedHashMap<String, String>>();
			foodLists.setFoodLists(newFoodLists);
		}
		registerFoodLogic.foodListsMaker (newFoodLists, parameter);
		// セッションスコープに更新したFoodListsを保存
		session.setAttribute("foodLists", foodLists);
		
// FoodAndDeadlineの編集
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
		// FoodAndDeadlineに取得した食べ物を追加して更新する
		registerFoodLogic.MapListOfPreDeadlineSetter(newAllList, fad);
		// セッションスコープに更新したFoodAndDeadline(賞味期限内の食べ物の【名前】と【期限】)を保存
		foodAndDeadline.setFoodAndDeadline(fad);
		session.setAttribute("foodAndDeadline", foodAndDeadline);
		
// OverFoodの編集
		// セッションスコープに保存されたOverFood(賞味期限切れの食べ物の【名前】)を取得
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
		// OverFoodに取得した食べ物を追加して更新する
		registerFoodLogic.ListOfOverFoodSetter(newAllList, ovf);
		// セッションスコープに更新したOverFood(賞味期限切れの食べ物の【名前】)を保存
		overFood.setOverFood(ovf);
		session.setAttribute("overFood", overFood);
				
		// フォワード
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("/WEB-INF/jsp/refrigerator.jsp");
		dispatcher.forward(request, response);
		
	}
}
