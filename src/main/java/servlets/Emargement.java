package servlets;

import java.io.IOException;
import java.sql.Date;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.HalfDay;

/**
 * Servlet implementation class Emargement
 */
@WebServlet("/emargement")
public class Emargement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Emargement() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.getServletContext().getRequestDispatcher("/jsp/emargement.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
//		HttpSession session = request.getSession();
		
		Calendar c = Calendar.getInstance();
		c.setTime(Date.valueOf(LocalDate.now()));
		System.out.println(c.getTime());
		
		
		if(request.getParameter("action") != null && request.getParameter("action").equals("AM")) {
			if(request.getParameter("AM") != null) {
				HalfDay matin = new HalfDay();
				Format formatter = new SimpleDateFormat("yyyy-MM-dd");
				String s = formatter.format(c.getTime());
				
				System.out.println(s);
				Date date = Date.valueOf(s);
				
				matin.setMorning(true);
				matin.setDate(date);
//				f.setIdUser((int) session.getAttribute("id_user"));
				matin.setIdUser(1);
				matin.insert();
			}
		} else if(request.getParameter("action") != null && request.getParameter("action").equals("PM")) {
			if(request.getParameter("PM") != null) {
				HalfDay aprem = new HalfDay();
				Format formatter = new SimpleDateFormat("yyyy-MM-dd");
				String s = formatter.format(c.getTime());
				
				System.out.println(s);
				Date date = Date.valueOf(s);
				
				aprem.setMorning(false);
				aprem.setDate(date);
//				f.setIdUser((int) session.getAttribute("id_user"));
				aprem.setIdUser(1);
				aprem.insert();
			}
		}
			
		doGet(request, response);
	}

}
