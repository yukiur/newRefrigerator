package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;

@WebServlet("/RegisterUser")
public class RegisterUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// フォワード先
		String forwardPath = null;
		
		// サーブレットクラスの動作を決定する「action」の値をリクエストパラメータから取得
		String action = request.getParameter("action");
		
		// 「登録の開始」をリクエストされたときの処理
		if(action == null) {
			// フォワード先を決定
			forwardPath = "/WEB-INF/jsp/registerForm.jsp";
		}
		// 登録確認画面から「登録実行」をリクエストされたときの処理
		else if(action.equals("done")) {
			//セッションスコープに保存された登録ユーザーを取得
//			HttpSession session = request.getSession();
//			User registerUser =(User) session.getAttribute("registerUser");
			// 登録処理の呼び出し
//			RegisterUserLogic logic = new RegisterUserLogic();
//			logic.execute(registerUser);
			// 登録後のフォワード先を設定
			forwardPath = "/WEB-INF/jsp/registerDone.jsp";
		}
		else if(action.equals("no")) {
			// 不要となったセッションスコープ内のインスタンスを削除
			HttpSession session = request.getSession();
			// セッションスコープの有効期間を設定
			if (session == null) {
				System.out.println("セッションがありません");
				} else {
				session.setMaxInactiveInterval(1800);
			}
			session.removeAttribute("registerUser");
			// フォワード先を決定
			forwardPath = "/WEB-INF/jsp/registerForm.jsp";
		}
		
		// 設定されたフォワード先にフォワード
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		
		// フォワード先
		String forwardPath = null;
		// 登録するユーザーの情報を設定
		if(id.length() > 0 && pass.length() > 0) {
			
			User registerUser = new User(id, pass);
			// セッションスコープに登録ユーザーを保存
			HttpSession session = request.getSession();
			// セッションスコープの有効期間を設定
			if (session == null) {
				System.out.println("セッションがありません");
				} else {
				session.setMaxInactiveInterval(1800);
			}
			session.setAttribute("registerUser", registerUser);
			// フォワード先の設定
			forwardPath = "/WEB-INF/jsp/registerConfirm.jsp";
		} else {
			// フォワード先の設定
			forwardPath = "/WEB-INF/jsp/registerFail.jsp";
		}
		// フォワード
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
			
	}

}
