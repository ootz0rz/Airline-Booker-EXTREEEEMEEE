package csc309a3;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InitSite
 */
public class Initialization extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String attrFlightsBooked = "flightsbooked";
	
	/**
	 * Create initial hashmap to store all the flights booked
	 */
    public void init() {
    	HashMap<String, FlightsToday> FlightsBooked = new HashMap<String, FlightsToday>(); 
    	this.getServletContext().setAttribute(attrFlightsBooked, FlightsBooked);
    }
    
	/**
	 * Given a Calendar, return the corresponding KEY for attrFlightsBooked
	 * @param cc
	 * @return
	 */
	public static String getKey(Calendar cc) {
		return String.format("%s/%s/%s", (cc.get(Calendar.MONTH) + 1), cc.get(Calendar.DATE), cc.get(Calendar.YEAR));
	}
}
