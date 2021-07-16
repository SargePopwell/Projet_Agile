package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.User;

/**
 * Servlet implementation class Inscription
 */
@WebServlet("/inscription")
public class Inscription extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Inscription() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.getServletContext().getRequestDispatcher("/jsp/inscription.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("to") != null && request.getParameter("to").equals("inscription")) {
			User user = new User();
			
			user.setSurname(request.getParameter("surname"));
			System.out.println(user.getSurname());
			user.setName(request.getParameter("name"));
			System.out.println(user.getName());
			user.setEmail(request.getParameter("email"));
			System.out.println(user.getEmail());
			user.setPassword(request.getParameter("password"));
			System.out.println(user.getPassword());
			user.setPhone(request.getParameter("phone"));
			System.out.println(user.getPhone());
			user.setAdress(request.getParameter("adress"));
			System.out.println(user.getAdress());
			if (request.getParameter("homme") != null)
				user.setGender(request.getParameter("homme"));
			else if (request.getParameter("femme") != null)
				user.setGender(request.getParameter("femme"));
			else if (request.getParameter("autre") != null)
				user.setGender(request.getParameter("autre"));
			System.out.println(user.getGender());
			user.setIntern(false);
			System.out.println(user.isIntern());
			user.setIdFormation(1);
			System.out.println(user.getIdFormation());
			
			user.insert();

		}

		doGet(request, response);
	}

}
