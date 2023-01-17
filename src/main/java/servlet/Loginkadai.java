package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AccountKadai;
import dto.kadi;
import util.GenerateHashedPw;

/**
 * Servlet implementation class Loginkadai
 */
@WebServlet("/Loginkadai")
public class Loginkadai extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Loginkadai() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getParameter("UTF-8");
		
		String mail = request.getParameter("mail");
		String pass = request.getParameter("pass");
		
		String salt = AccountKadai.getSalt(mail);
		
		if(salt == null) {
			String view = "WEB-INF/view/kadailogin.jsp?error=1";
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
			return;
		}
		
		// 取得したソルトを使って入力したPWをハッシュ
		String hashedPw = GenerateHashedPw.getSafetyPassword(pass, salt);
		System.out.println("ログインチェックのソルト"+ salt);
		System.out.println("ログインチェックのハッシュPW"+ hashedPw);
		// 入力されたID、ハッシュしたPWに一致するユーザを検索する
		kadi account = AccountKadai.login(mail, hashedPw);
		
		// 一致するユーザがいなければ、ログイン失敗
		if(account == null) {
			String view = "WEB-INF/view/kadailogin.jsp?error=1";
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
		} else {
			String view = "WEB-INF/view/kadaiseikou.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
