<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="csc309a3.*" %>
<%@ include file="_header.jsp" %>
<%
	FlightsToday today = (FlightsToday)request.getAttribute("sToday");
	TransactionRecord tr = (TransactionRecord)request.getAttribute("sTrans");
	Flight thisFlight = (Flight)request.getAttribute("sFlight");
	
	Calendar trCC = Search.getCalendarInstance(tr.trDate);
%>
                <div id="flights" class="printable">
                    <div class="top">
                        <h2>Success! :)</h2>
                    </div>
                    <div class="bottom savedetails">
						<div class="part">
							Your transaction has been saved. Please print out the information below for your own records.
						</div>
						
						<div class="part">
							<h3>Details</h3>
							
							<div>
								<h4>Destination</h4>
								<%= thisFlight.Destination == Flight.DESTINATION.montreal ? "Montreal" : "Toronto" %>
							</div>
							
							<div>
								<h4>Departure Time</h4>
								<%= thisFlight.DepartTime == Flight.DEPART_TIME.eve_8 ? "8:00pm" : "10:00am" %>
							</div>
							
							<div>
								<h4>Arrival Time</h4>
								<%= thisFlight.DepartTime == Flight.DEPART_TIME.eve_8 ? "9:10pm" : "11:10am" %>
							</div>
							
							<div>
								<h4>Travel Time</h4>
								<%= thisFlight.TravelTime %>
							</div>
							
							<div>
								<h4>Date of Transaction</h4>
								<%=
								String.format(
									"%s/%s/%s",
									trCC.get(Calendar.MONTH) + 1, trCC.get(Calendar.DATE), trCC.get(Calendar.YEAR))
								%>
							</div>
							
							<div>
								<h4>Date of Flight</h4>
								<%=
								String.format(
									"%s/%s/%s",
									today.cc.get(Calendar.MONTH) + 1, today.cc.get(Calendar.DATE), today.cc.get(Calendar.YEAR))
								%>
							</div>
							
							<div>
								<h4>Price</h4>
								$<%= thisFlight.getPrice() %>.00
							</div>
							
							<div>
								<h4>Name</h4>
								<%= tr.FirstName %>
								<%= tr.LastName %>
							</div>
							
							<div>
								<h4>Street #</h4>
								<%= tr.StreetNum %>
							</div>
							
							<div>
								<h4>Street Address</h4>
								<%= tr.StreetAddr %>
							</div>
							
							<div>
								<h4>City</h4>
								<%= tr.City %>
							</div>
							
							<div>
								<h4>Province</h4>
								<%= tr.Province %>
							</div>

							<div>
								<h4>Postal Code</h4>
								<%= tr.Postal %>
							</div>
							
							<div>
								<h4>Phone Number</h4>
								<%= tr.Phone %>
							</div>
							
							<div>
								<h4>Credit Card #</h4>
								<%= tr.CCnum %>
							</div>
							
							<div>
								<h4>Credit Card Expiry Date</h4>
								<%= tr.CCexp %>
							</div>
						</div>
						
						<div class="part">
							<a class="button s5" id="printpage" href="#">Print This Page</a>
							
							<a class="button s6" href="index.html">Back to Search</a>
						</div>
                    </div>
                </div>

<%@ include file="_footer_baseJS.jsp" %>

        <script type="text/javascript" src="js/functions.js"></script>
        
        <script type="text/javascript">
        	$("#printpage").click(function(e) 
        	{
        		e.preventDefault();
        		
        		window.print();
        	});
        </script>
        
<%@ include file="_footer.jsp" %>