package servlets;

import java.io.IOException;
import java.sql.Date;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Day;
import beans.Formation;

/**
 * Servlet implementation class Menu_Formations
 */
@WebServlet("/menu_formations")
public class Menu_Formations extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Menu_Formations() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		
		Formation formation = new Formation();
		
		ArrayList<Formation> formations = (ArrayList<Formation>) formation.selectAll();
		System.out.println(formations);
		request.setAttribute("formations", formations);
		
		System.out.println(formations.get(0).getName());
		System.out.println(formations.get(1).getName());
		System.out.println(formations.get(2).getName());
		
		this.getServletContext().getRequestDispatcher("/jsp/menu_formations.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("action") != null && request.getParameter("action").equals("add_formation")) {
			
//			
//			HttpSession session = request.getSession();
			
			Calendar c = Calendar.getInstance();
			
			Formation f = new Formation();
			
			
			f.setName(request.getParameter("intitule"));
			f.setDateStart(Date.valueOf(request.getParameter("date_start")));
			f.setDateEnd(Date.valueOf(request.getParameter("date_end")));
			f.setDescription(request.getParameter("description"));
//			f.setIdUser((int) session.getAttribute("id_user"));
			f.setIdUser(1);
			f.insert();
			
			Date d_start = Date.valueOf(request.getParameter("date_start"));
			Date d_end = Date.valueOf(request.getParameter("date_end"));
				
			for(Date date = d_start; !date.equals(d_end);) {
				System.out.println(date);
				Day d = new Day();
				d.setDate(date);
				d.setActivity("-");
				d.setIdFormer(1);
				d.setIdFormation(f.getIdFormation());
				d.insert();
				System.out.println(d.getIdFormation());
				
				
				System.out.println(d.getDate());
				c.setTime(date);
				c.add(Calendar.DATE, 1);
				Format formatter = new SimpleDateFormat("yyyy-MM-dd");
				String s = formatter.format(c.getTime());

				System.out.println(s);
				date = Date.valueOf(s);
			}
			
		}
		
		doGet(request, response);
		
		
	}

}
