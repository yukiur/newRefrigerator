package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Login;
import model.RegisterUserLogic;
import model.User;

@WebServlet("/LoginUserServlet")
public class LoginUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
					
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("/WEB-INF/jsp/loginForm.jsp");
			dispatcher.forward(request, response);
		}
		
		
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// リクエストパラメータの取得
			request.setCharacterEncoding("UTF-8");
			String id = request.getParameter("id");
			String pass = request.getParameter("pass");
			
			// セッションスコープに保存されている登録ユーザーを取得
			HttpSession session = request.getSession();
			// セッションスコープの有効期間を設定
			if (session == null) {
				System.out.println("セッションがありません");
				} else {
				session.setMaxInactiveInterval(1800);
			}
			User registerUser = (User) session.getAttribute("registerUser");
			
			// ログイン入力値とセッションスコープに登録されているユーザーの情報があっているが確認
			RegisterUserLogic logic = new RegisterUserLogic();
			
			// フォワード先
			String forwardPath = null;
			if(registerUser != null) {
				boolean access = logic.confirm(id,pass,registerUser);
				
				if(access) {
					Login login = new Login("success");
					session.setAttribute("login", login);
					// フォワード先を決定(ログイン成功)
					forwardPath = "/WEB-INF/jsp/refrigerator.jsp";
				} else {
					// フォワード先を決定(ログイン失敗)
					forwardPath = "/WEB-INF/jsp/loginFail.jsp";
				}
			} else {
				boolean access = logic.testConfirm(id,pass);
				if(access) {
					Login login = new Login("success");
					session.setAttribute("login", login);
					registerUser = new User("test", "1234");
					session.setAttribute("registerUser", registerUser);
					// フォワード先を決定(ログイン成功)
					forwardPath = "/WEB-INF/jsp/refrigerator.jsp";
					
				} else {
					// フォワード先を決定(ログイン失敗)
					forwardPath = "/WEB-INF/jsp/loginFail.jsp";
				}
			} 
			// フォワード
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher(forwardPath);
			dispatcher.forward(request, response);
				
		}

	}
