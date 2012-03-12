package csc309a3;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Search 
 */
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected static final String page_SearchResults = "/search.jsp";
	public static final String page_SearchError = "/searcherror.jsp";

    /**
     * Default constructor. 
     */
	public Search() {
		super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, 
	 * HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		// get is easier to test with...
		doPost(request, response);
	}

	/**
	 * Handle the Search Post
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, 
	 * HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		HashMap<String, FlightsToday> FlightsBooked = (HashMap<String, FlightsToday>)getServletContext().getAttribute(Initialization.attrFlightsBooked);
		
		List<String> errorList = new ArrayList<String>();
		int iFrom = -1;
		Date dDatev = new Date();
		FlightsToday today = null;
		Calendar cc = null;
		String sFrom = "";
		String sDatev = "";
		
		// start verify post data
		sFrom = request.getParameter("from");
		sDatev = request.getParameter("datev");
		
		// make sure we get something...
		if ( 	sFrom != null && sDatev != null &&
				sFrom.length() > 0 && sDatev.length() > 0 ) {
			
			// parse as required
			try {
				iFrom = Integer.parseInt(sFrom);
			} 
			catch (NumberFormatException e) {
				errorList.add("[Code: 0] Invalid location given.");
			}
			
			try {
				DateFormat df = new SimpleDateFormat("M/d/y");
				dDatev = (Date)df.parse(sDatev);
			} 
			catch (ParseException e) {
				errorList.add("[Code 1] Date format invalid.");
			}
			
			// check bounds
			if ( !(iFrom == 1 || iFrom == 2) ) {
				errorList.add("[Code: 2] Invalid location given.");
			}
			
			Date sDate = new Date();
			Date eDate = new Date();
			
			Calendar sc = getCalendarInstance(sDate);
			Calendar ec = getCalendarInstance(eDate);
			cc = getCalendarInstance(dDatev);
			
			sc.add(Calendar.DATE, 1);
			ec.add(Calendar.DATE, 14);
			
			long ccTime = cc.getTimeInMillis();
			if ( ccTime > ec.getTimeInMillis() || ccTime < sc.getTimeInMillis() ) {
				errorList.add("[Code: 3] Date given is out of range.");
			}
			
			// retrieve results
			String key = Initialization.getKey(cc);
			
			// see if the key exists
			if ( FlightsBooked.containsKey(key) ) {
				today = FlightsBooked.get(key);
			} else {
				today = new FlightsToday(cc);
			}
			
		} else {
			errorList.add("[Code: 4] Please provide all data in the form and try again.");
		}
		
		// dispatch jsp page
		if ( errorList.isEmpty() ) {
			// no errors
			request.setAttribute("uDate", dDatev);
			request.setAttribute("uCC", cc);
			request.setAttribute("uFrom", iFrom);
			request.setAttribute("sToday", today);
			
			RequestDispatcher dispatcher = 
					getServletContext().getRequestDispatcher(page_SearchResults);
			dispatcher.forward(request, response);
		} else {
			// errors...
			request.setAttribute("errorList", errorList);
			
			RequestDispatcher dispatcher = 
					getServletContext().getRequestDispatcher(page_SearchError);
			dispatcher.forward(request, response);
		}
	}

	/**
	 * Return a Calendar instance with hours, minutes and seconds set to 0
	 * 
	 * @param sDate
	 * @return
	 */
	public static Calendar getCalendarInstance(Date sDate) {
		Calendar sc = Calendar.getInstance();
		sc.setTime(sDate);
		sc.set(Calendar.HOUR_OF_DAY, 0);
		sc.set(Calendar.MINUTE, 0);
		sc.set(Calendar.SECOND, 0);
		sc.set(Calendar.MILLISECOND, 0);
		return sc;
	}

	/**
	 * Get a JSON string representing each of the seats and their status
	 * 
	 * @param today
	 * @return
	 */
	public static String JSON_GetSeatData(Flight today) {
		StringBuilder o = new StringBuilder();
		
		o.append("{");
		o.append(String.format("0: %s,\n", today.isSeatBooked(0)));
		o.append(String.format("1: %s,\n", today.isSeatBooked(1)));
		o.append(String.format("2: %s\n", today.isSeatBooked(2)));
		o.append("}");
		
		return o.toString();
	}
}
