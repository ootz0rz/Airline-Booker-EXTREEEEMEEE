<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="csc309a3.*" %>
<%@ include file="_header.jsp" %>
<%
	HashMap<String, FlightsToday> FlightsBooked = (HashMap<String, FlightsToday>)request.getAttribute("FlightsBooked");
%>
<%!
	/**
	 * Print a single row for the given flight+key
	 */
	String getRow(Flight thisFlight, String key) {
		String o = "";
		for ( int i = 0; i < 3; i++ ) {
			if ( thisFlight.isSeatBooked(i) ) {
				TransactionRecord tr = thisFlight.getTransactions()[i];
				Calendar trCC = tr.trCC;

				o += String.format(""
						+ "<tr>"
						+ "    <td>%s</td>"
						+ "    <td>%s</td>"
						+ "    <td>%s</td>"
						+ "    <td>%s</td>"
						+ "    <td>$%s.00</td>"
						+ "    <td>%s</td>"
						+ ""
						+ "    <td>%s</td>"
						+ "    <td>%s</td>"
						+ "    <td>%s</td>"
						+ "    <td>%s</td>"
						+ "    <td>%s</td>"
						+ "    <td>%s</td>"
						+ "    <td>%s</td>"
						+ "    <td>%s</td>"
						+ "    <td>%s</td>"
						+ "    <td>%s</td>"
						+ "</tr>",
						
						String.format("%s/%s/%s", trCC.get(Calendar.MONTH) + 1, trCC.get(Calendar.DATE), trCC.get(Calendar.YEAR)),
						thisFlight.Destination == Flight.DESTINATION.montreal ? "Montreal" : "Toronto",
						key,
						thisFlight.DepartTime == Flight.DEPART_TIME.eve_8 ? "8:00pm" : "10:00am",
						thisFlight.getPrice(),
						i,
						
						tr.FirstName,
						tr.LastName,
						tr.StreetNum,
						tr.StreetAddr,
						tr.City,
						TransactionRecord.intToProvince.get(tr.Province),
						tr.Postal,
						tr.Phone,
						tr.CCnum,
						tr.CCexp
					);
			}
		}
		
		return o;
	}
%>
                <div id="flights" class="history">
                    <div class="top">
                        <h2>Transaction History</h2>
                    </div>
                    <div class="bottom">
						<table class="tablesorter">
							<thead>
								<tr>
									<th class="{sorter: 'shortDate'}">Date</th>
                                    <th>Destination</th>
                                    <th class="{sorter: 'shortDate'}">Departure Date</th>
                                    <th>Departure Time</th>
                                    <th>Fee</th>
                                    <th>Seat #</th>

                                    <th>First Name</th>
                                    <th>Last Name</th>
                                    <th>Street Num</th>
                                    <th>Street Address</th>
                                    <th>City</th>
                                    <th>Province</th>
                                    <th>Postal Code</th>
                                    <th>Phone #</th>
                                    <th>CC #</th>
                                    <th>CC Expiry</th>
								</tr>
							</thead>
                            <tbody>
                           		<%
                           			Set<String> keys = FlightsBooked.keySet();
									List<String> keyslist = new ArrayList<String>(keys);
									java.util.Collections.sort(keyslist);
                           		
                           			for ( String key : keyslist ) {
                           				FlightsToday today = FlightsBooked.get(key);
                           				out.println(this.getRow(today.morning_montreal, key));
                           				out.println(this.getRow(today.morning_toronto, key));
                           				out.println(this.getRow(today.evening_montreal, key));
                           				out.println(this.getRow(today.evening_toronto, key));
                           			}
                           		%>
                            </tbody>
						</table>
                    </div>
                </div>

<%@ include file="_footer_baseJS.jsp" %>

        <script type="text/javascript" src="js/functions.js"></script>
        <script type="text/javascript" src="jquery/jquery.tablesorter/jquery.tablesorter.min.js"></script>
        <script type="text/javascript" src="jquery/jquery.tablesorter/jquery.metadata.js"></script>
        <link rel="stylesheet" type="text/css" href="jquery/jquery.tablesorter/style.css" />
        <script tye="text/javascript">
            $("body").addClass("history");
            $("table.tablesorter").tablesorter({sortList: [[0,0]]});
        </script>
        
<%@ include file="_footer.jsp" %>