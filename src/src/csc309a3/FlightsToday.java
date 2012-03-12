package csc309a3;

import java.util.Calendar;

/**
 * The flight data for the given day
 * 
 * @author Hardeep
 *
 */
public class FlightsToday {
	/**
	 * The date for "today"
	 */
	public Calendar cc;
	
	/**
	 * Flight to montreal, 10am
	 */
	public Flight morning_montreal;
	
	/**
	 * Flight to toronto, 10am
	 */
	public Flight morning_toronto;
	
	/**
	 * Flight to montreal, 8pm
	 */
	public Flight evening_montreal;
	
	/**
	 * Flight to toronto, 8pm
	 */
	public Flight evening_toronto;
	
	public FlightsToday(Calendar cc, 
			Flight morning_montreal, Flight morning_toronto, 
			Flight evening_montreal, Flight evening_toronto) {
		this.cc = cc;
		
		this.morning_montreal = morning_montreal;
		this.morning_toronto = morning_toronto;
		
		this.evening_montreal = evening_montreal;
		this.evening_toronto = evening_toronto;
	}
	
	public FlightsToday(Calendar cc) {
		this.cc = cc;
		
		this.morning_montreal = new Flight(Flight.DEPART_TIME.morn_10, Flight.DESTINATION.montreal);
		this.morning_toronto = new Flight(Flight.DEPART_TIME.morn_10, Flight.DESTINATION.toronto);
		
		this.evening_montreal = new Flight(Flight.DEPART_TIME.eve_8, Flight.DESTINATION.montreal);
		this.evening_toronto = new Flight(Flight.DEPART_TIME.eve_8, Flight.DESTINATION.toronto);
	}
}
