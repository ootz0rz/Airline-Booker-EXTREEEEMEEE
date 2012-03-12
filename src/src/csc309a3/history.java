package csc309a3;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class history
 */
public class history extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String page_ShowHistory = "/history.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public history() {
        super();
    }

	/**
	 * Display transaction history
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		request.setAttribute("FlightsBooked", (HashMap<String, FlightsToday>)getServletContext().getAttribute(Initialization.attrFlightsBooked));
		RequestDispatcher dispatcher = 
				getServletContext().getRequestDispatcher(page_ShowHistory);
		dispatcher.forward(request, response);
	}
}
