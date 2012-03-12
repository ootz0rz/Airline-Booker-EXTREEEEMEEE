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
 * Servlet implementation class Save
 */
public class Save extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/* --- regex checkers --- */
	protected final String _regex_phone = "^\\D?(\\d{3})\\D?\\D?(\\d{3})\\D?(\\d{4})";
	protected final String _regex_postal = "^([A-Za-z]\\d[A-Za-z][-\\s]?\\d[A-Za-z]\\d)";
	protected final String _regex_CCexp = "^\\d{1,2}\\/\\d{2}";
	protected final String _regex_CCnum = "^\\D?\\d{4}\\D?\\d{4}\\D?\\d{4}\\D?\\d{4}";
	
	protected final String _page_SaveSuccess = "/savesuccess.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Save() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get is easier to test with...
		doPost(request, response);
	}

	/**
	 * Save transaction after verification
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		@SuppressWarnings("unchecked")
		HashMap<String, FlightsToday> FlightsBooked = (HashMap<String, FlightsToday>)getServletContext().getAttribute(Initialization.attrFlightsBooked);
		
		List<String> errorList = new ArrayList<String>();
		
		// get all parameters
		String from = request.getParameter("from");
		String datev = request.getParameter("datev");
		String evemorn = request.getParameter("evemorn");
		String seatnum = request.getParameter("seatnum");
		
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		
		String snum = request.getParameter("snum");
		String saddr = request.getParameter("saddr");
		String city = request.getParameter("city");
		String prov = request.getParameter("prov");
		String postal = request.getParameter("postal");
		
		String phone = request.getParameter("phone");
		
		String CCnum = request.getParameter("CCnum");
		String CCexp = request.getParameter("CCexp");
		
		// make sure every one of them is set to something
		if (
			isSet(from) 
			|| isSet(datev)
			|| isSet(evemorn)
			|| isSet(seatnum)
			
			|| isSet(fname)
			|| isSet(lname)
			
			|| isSet(snum)
			|| isSet(saddr)
			|| isSet(city)
			|| isSet(prov)
			|| isSet(postal)
			
			|| isSet(phone)
			
			|| isSet(CCnum)
			|| isSet(CCexp)
		) {
			// verify formats
			int uFrom = asInt(from, "[Code: 0] Invalid location given.", errorList);
			int iEveMorn = asInt(evemorn, "[Code: 0] Invalid time chosen.", errorList);
			int iSeatnum = asInt(seatnum, "[Code: 0] Invalid seat chosen.", errorList);
			Date uDatev = asDate(datev, "[Code 1] Date format invalid.", errorList);
			
			// check bounds
			if ( !(uFrom == 1 || uFrom == 2) ) {
				errorList.add("[Code: 2] Invalid location given.");
			}
			
			Date sDate = new Date();
			Date eDate = new Date();
			
			Calendar sc = Search.getCalendarInstance(sDate);
			Calendar ec = Search.getCalendarInstance(eDate);
			Calendar cc2 = Search.getCalendarInstance(uDatev);
			
			sc.add(Calendar.DATE, 1);
			ec.add(Calendar.DATE, 14);
			
			long ccTime = cc2.getTimeInMillis();
			if ( ccTime > ec.getTimeInMillis() || ccTime < sc.getTimeInMillis() ) {
				errorList.add("[Code: 3] Date given is out of range.");
			}
			
			// retrieve todays flights if any
			Calendar cc = Search.getCalendarInstance(uDatev);
			String key = Initialization.getKey(cc);
			
			// see if the key exists
			FlightsToday today = null;
			if ( FlightsBooked.containsKey(key) ) {
				today = FlightsBooked.get(key);
			} else {
				today = new FlightsToday(cc);
			}
			
			// get specific flight
			Flight thisFlight = null;
			if ( uFrom == 1 ) { // toronto
				if ( iEveMorn == 0 ) { // morning
					thisFlight = today.morning_toronto; 
				} else { // evening
					thisFlight = today.evening_toronto;
				}
			} else { // montreal
				if ( iEveMorn == 0 ) { // morning
					thisFlight = today.morning_montreal;
				} else { // evening
					thisFlight = today.evening_montreal;
				}
			}
			
			int uNum = asInt(snum, "[Code: 0] Invalid street number given.", errorList);
			int uProv = asInt(prov, "[Code: 0] Invalid province given.", errorList);
			
			// check formats for postal code, phone number, CC number, and CC expiry similar to the javascript equivalents
			if ( !phone.matches(_regex_phone) ) errorList.add("[Code: 5] Phone number not valid.");
			if ( !postal.matches(_regex_postal) ) errorList.add("[Code: 5] Postal code not valid.");
			if ( !CCnum.matches(_regex_CCnum) ) errorList.add("[Code: 5] Credit Card number not valid.");
			if ( !CCexp.matches(_regex_CCexp) ) errorList.add("[Code: 5] Credit Card expiry not valid.");
			
			// verify province is valid
			if ( !TransactionRecord.intToProvinceEnum.containsKey(uProv) ) {
				errorList.add("[Code: 6] Province selected is invalid.");
			}
			
			// check seat valid
			if ( iSeatnum >= 0 && iSeatnum <= 2 ) {
				// check seat not already booked
				if ( thisFlight.isSeatBooked(iSeatnum) ) {
					errorList.add("[Code: 6] Seat is already booked.");
				}
			} else {
				errorList.add("[Code: 0] Invalid seat number given.");
			}
			
			// if we get to this point without errors, we're probably in good shape
			if ( errorList.isEmpty() ) {
				// no errors
				TransactionRecord tr = new TransactionRecord();
				tr.FirstName = fname;
				tr.LastName = lname;
				
				tr.StreetNum = uNum;
				tr.StreetAddr = saddr;
				
				tr.City = city;
				tr.Province = TransactionRecord.intToProvinceEnum.get(uProv);
				tr.Postal = postal;
				tr.Phone = phone;
				
				tr.CCnum = CCnum;
				tr.CCexp = CCexp;
				
				tr.trDate = new Date();
				tr.trCC = Search.getCalendarInstance(new Date());
				
				// book the seat
				if ( !FlightsBooked.containsKey(key) ) {
					FlightsBooked.put(key, today);
				}
				
				thisFlight.bookSeat(iSeatnum);
				thisFlight.setTransaction(iSeatnum, tr);
				
				request.setAttribute("sToday", today);
				request.setAttribute("sTrans", tr);
				request.setAttribute("sFlight", thisFlight);
				
				RequestDispatcher dispatcher = 
						getServletContext().getRequestDispatcher(_page_SaveSuccess);
				dispatcher.forward(request, response);
				
				return;
			}
		} else {
			errorList.add("[Code: 4] Please provide all data in the form and try again.");
		}
		
		request.setAttribute("errorList", errorList);
		
		RequestDispatcher dispatcher = 
				getServletContext().getRequestDispatcher(Search.page_SearchError);
		dispatcher.forward(request, response);
	}
	
	/**
	 * true iff s != null && s.length > 0
	 * @param s
	 * @return
	 */
	protected boolean isSet(String s) {
		return s != null && s.length() > 0;
	}
	
	/**
	 * return s as an int if possible...if an error occured, put it in errorList with given errorMessage
	 * @param i
	 * @return Integer.MIN_VALUE if can't parse, Integer.parseInt(s) otherwise
	 */
	protected int asInt(String s, String errorMessage, List<String> errorList) {
		try {
			return Integer.parseInt(s);
		} 
		catch (NumberFormatException e) {
			errorList.add(errorMessage);
		}
		
		return Integer.MIN_VALUE;
	}
	
	/**
	 * return s as a Date if possible...if an error occured, put it in errorList with given errorMessage
	 * @param s
	 * @param errorMessage
	 * @param errorList
	 * @return null if can't parse, DateFormat.parse(s) otherwise
	 */
	protected Date asDate(String s, String errorMessage, List<String> errorList) {
		try {
			DateFormat df = new SimpleDateFormat("M/d/y");
			return (Date)df.parse(s);
		} 
		catch (ParseException e) {
			errorList.add("[Code 1] Date format invalid.");
		}
		
		return null;
	}
}
