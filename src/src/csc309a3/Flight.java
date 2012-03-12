package csc309a3;

/**
 * Instance of a single flight
 * @author Hardeep
 *
 */
public class Flight {
	/**
	 * Departure times
	 * @author Hardeep
	 *
	 */
	public static enum DEPART_TIME {
		morn_10,
		eve_8
	}
	
	/**
	 * Flight destinations
	 * @author Hardeep
	 *
	 */
	public static enum DESTINATION {
		montreal,
		toronto
	}
	
	/**
	 * Text used to display how long travel time is...
	 */
	public final String TravelTime = "1h 10m";
	
	/**
	 * The time this flight departs
	 */
	public DEPART_TIME DepartTime = Flight.DEPART_TIME.morn_10;
	
	/**
	 * The location this flight is going to
	 */
	public DESTINATION Destination = DESTINATION.montreal;
	
	/**
	 * Seats currently occupied marked as "true"
	 * 
	 * Seats[0] = first seat on left of image
	 */
	protected boolean[] Seats = {
		false, 
		false, 
		false
	};
	
	/**
	 * Transactions corresponding to each seat, if available
	 */
	protected TransactionRecord[] Transactions = {
			new TransactionRecord(),
			new TransactionRecord(),
			new TransactionRecord()
	};
	
	public Flight(DEPART_TIME DepartTime, DESTINATION Destination) {
		this.DepartTime = DepartTime;
		this.Destination = Destination;
	}
	
	public Flight() {
		
	}
	
	/**
	 * Return the transactions array
	 * @return
	 */
	public TransactionRecord[] getTransactions() {
		return Transactions;
	}
	
	/**
	 * Set the ith element of the transaction array to tr
	 * @param i
	 * @param tr
	 */
	public void setTransaction(int i, TransactionRecord tr) {
		Transactions[i] = tr;
	}
	
	/**
	 * true iff at least one seat is booked
	 * @return
	 */
	public Boolean isBooked() {
		return Seats[0] || Seats[1] || Seats[2];
	}
	
	/**
	 * true iff all seats are booked
	 * @return
	 */
	public Boolean isFull() {
		return Seats[0] && Seats[1] && Seats[2];
	}
	
	/**
	 * Get the price depending on flight characteristics
	 * @return
	 */
	public int getPrice() {
//		if ( DepartTime == DEPART_TIME.morn_10 ) {
//			return 369;
//		}
//		
//		return 379;
		return 100;
	}
	
	/**
	 * Mark given seat as booked
	 * 
	 * @param seatID 0-based index of seat, starting from left side
	 */
	public void bookSeat(int seatID) {
		if ( seatID < Seats.length ) { 
			Seats[seatID] = true;
		}
	}
	
	/**
	 * true iff the seat is booked
	 * 
	 * @param seatID 0-based index of seat, starting from left side
	 * @return seat status
	 */
	public Boolean isSeatBooked(int seatID) {
		if ( seatID < Seats.length ) { 
			return Seats[seatID];
		}
		
		return false;
	}
}
