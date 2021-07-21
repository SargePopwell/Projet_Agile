package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Day;
import beans.Formation;
import beans.HalfDay;
import beans.User;

/**
 * Servlet implementation class ServFormation
 */
@WebServlet("/formation")
public class ServFormation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServFormation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
		if(request.getParameter("to") != null) {
			
			Formation f = new Formation();
			Day d = new Day();
			User u = new User();
			HalfDay hd = new HalfDay();
			User former = new User();
			
			f.setIdFormation(Integer.parseInt(request.getParameter("to")));
			f.select();
			d.setIdFormation(f.getIdFormation());
			u.setIdFormation(f.getIdFormation());
			
			ArrayList<Day> days = d.selectAllByFormation();
			ArrayList<User> interns = u.selectAllInternsByFormation();
			
			for (User i : interns){
				hd.setIdUser(i.getIdUser());
				i.setHalfdays(hd.selectAllByIntern());
			}
			
			ArrayList<HalfDay> hds = interns.get(0).getHalfdays();
			
			System.out.println(hds.get(0).isIchecked());
			
			request.setAttribute("d1", hds.get(0));
			request.setAttribute("test", true);
			request.setAttribute("formation", f);
			request.setAttribute("days", days);
			request.setAttribute("interns", interns);	
		}
		
		
		this.getServletContext().getRequestDispatcher("/jsp/formation.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
