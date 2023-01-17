package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.kadi;

/**
 * Servlet implementation class kadai1A
 */
@WebServlet("/kadai1A")
public class kadai1A extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public kadai1A() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String name = request.getParameter("name");
		String ages = request.getParameter("age");
		String seik = request.getParameter("sei");
		String numbers	= request.getParameter("number");
		String mail = request.getParameter("mail");
		String pass = request.getParameter("pass");
		
		
		String sei =  seik.equals("0") ? "男" : "女";
		
		int age = Integer.parseInt(ages);
		int number = Integer.parseInt(numbers);
		
		kadi kai = new kadi (name,age,sei, number , mail , pass,null,null);
		
		HttpSession session = request.getSession();
		
		session.setAttribute("input_data", kai);
		
		String view = "WEB-INF/view/kadai1A.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);	
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
