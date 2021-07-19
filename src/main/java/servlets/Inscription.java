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
			user.setName(request.getParameter("name"));
			user.setEmail(request.getParameter("email"));
			user.setPassword(request.getParameter("password"));
			user.setPhone(request.getParameter("phone"));
			user.setAdress(request.getParameter("adress"));
			
			if (request.getParameter("homme") != null)
				user.setGender(request.getParameter("homme"));
			else if (request.getParameter("femme") != null)
				user.setGender(request.getParameter("femme"));
			else if (request.getParameter("autre") != null)
				user.setGender(request.getParameter("autre"));

			user.setIntern(false);
			user.setIdFormation(1);
			
			user.insert();

		}

		doGet(request, response);
	}

}
