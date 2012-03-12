<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="csc309a3.*" %>

<%
	request.setAttribute("_header_isSearch", true);
%>
<%@ include file="_header.jsp" %>
<%
	// get results from backend
	Date uDate = (Date)request.getAttribute("uDate");
	Calendar cc = (Calendar)request.getAttribute("uCC");
	int uFrom = (Integer)request.getAttribute("uFrom");
	
	FlightsToday sToday = (FlightsToday)request.getAttribute("sToday");
%>
<%@ include file="_footer_baseJS.jsp" %>

                <div id="flights" class="search">
                    <div class="top">
                        <h2>Results For</h2>
                    </div>
                    <div class="bottom">
                        <form id="searchform" class="main" method="post" action="search">
                            <fieldset>
                                <div class="part">
                                    <h3>To</h3>

                                    <input type="radio" id="toronto" name="from" value="1" class="btn" <%= uFrom == 1 ? "checked=\"checked\"" : "" %>  />
                                    <label for="toronto">Toronto</label>
                                    <input type="radio" id="montreal" name="from" value="2" class="btn" <%= uFrom == 2 ? "checked=\"checked\"" : "" %>  />
                                    <label for="montreal">Montreal</label>
                                </div>

                                <div class="part date">
                                    <h3>On</h3>
                                    <label for="date">Date:</label>
                                    <div id="date"></div>
                                    <input type="text" placeholder="date: mm/dd/yyyy" name="datev" id="datev" value="<%= String.format("%s/%s/%s", (cc.get(Calendar.MONTH) + 1), cc.get(Calendar.DATE), cc.get(Calendar.YEAR)) %>" />
                                    <small>format: mm/dd/yyyy</small>
                                </div>

                                <div class="part">
                                    <input class="disabled" type="submit" name="Search" id="Search" value="Search!" />
                                </div>
                            </fieldset>
                        </form>
                    </div>
                </div>
                <div id="results">
                    <div class="main">
                        <h2>Flights Available</h2>
                        
                        <div class="results">
							<script type="text/javascript">
								var seatdata = {
										toronto : {
											morn : {},
											eve : {},
										},
										montreal : {
											morn : {},
											eve : {},
										}
								};
							</script>
                            <table>
                                <thead>
                                    <tr>
                                        <th></th>
                                        <th>Departing</th>
                                        <th>Arriving</th>
                                        <th>Travel Time</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<%
                                		boolean display = false;
                                		if ( uFrom == 1 ) {
                                			// to toronto
                                			
                                			// morning
                                			if ( !sToday.morning_toronto.isFull() ) {
                                				display = true;
                                				%>
                                    <tr class="toronto morn">
                                        <td>
                                            <a href="#" class="button s5 buybtn toronto morn" id="btntorontomorn">Buy <span>$<%= sToday.morning_toronto.getPrice() %></span></a>
                                        </td>
                                        <td>10:00 am</td>
                                        <td>11:10 am</td>
                                        <td><%= sToday.morning_toronto.TravelTime %></td>
                                    </tr>
									<script type="text/javascript">
										seatdata["toronto"]["morn"] = <%= Search.JSON_GetSeatData(sToday.morning_toronto) %>;
										$("#btntorontomorn").data('seats', seatdata["toronto"]["morn"]);
									</script>
                                				<%
                                			}
                                			
                                			
                                			// evening
                                			if ( !sToday.evening_toronto.isFull() ) {
                                				display = true;
                                				%>
                                    <tr class="toronto eve">
                                        <td>
                                            <a href="#" class="button s5 buybtn toronto eve" id="btntorontoeve">Buy <span>$<%= sToday.evening_toronto.getPrice() %></span></a>
                                        </td>
                                        <td>8:00 pm</td>
                                        <td>9:10 pm</td>
                                        <td><%= sToday.evening_toronto.TravelTime %></td>
                                    </tr>
									<script type="text/javascript">
										seatdata["toronto"]["eve"] = <%= Search.JSON_GetSeatData(sToday.evening_toronto) %>;
										$("#btntorontoeve").data('seats', seatdata["toronto"]["eve"]);
									</script>
                                				<%
                                			}
                                		} else {
                                			// to montreal
                                			
                                			// morning
                                			if ( !sToday.morning_montreal.isFull() ) {
                                				display = true;
                                				%>
                                    <tr class="montreal morn">
                                        <td>
                                            <a href="#" class="button s5 buybtn montreal morn" id="btnmontrealmorn">Buy <span>$<%= sToday.morning_montreal.getPrice() %></span></a>
                                        </td>
                                        <td>10:00 am</td>
                                        <td>11:10 am</td>
                                        <td><%= sToday.morning_montreal.TravelTime %></td>
                                    </tr>
									<script type="text/javascript">
										seatdata["montreal"]["morn"] = <%= Search.JSON_GetSeatData(sToday.morning_montreal) %>;
										$("#btnmontrealmorn").data('seats', seatdata["montreal"]["morn"]);
									</script>
                                				<%
                                			}
                                			
                                			
                                			// evening
                                			if ( !sToday.evening_montreal.isFull() ) {
                                				display = true;
                                				%>
                                    <tr class="montreal eve">
                                        <td>
                                            <a href="#" class="button s5 buybtn montreal eve" id="btnmontrealeve">Buy <span>$<%= sToday.evening_montreal.getPrice() %></span></a>
                                        </td>
                                        <td>8:00 pm</td>
                                        <td>9:10 pm</td>
                                        <td><%= sToday.evening_montreal.TravelTime %></td>
                                    </tr>
									<script type="text/javascript">
										seatdata["montreal"]["eve"] = <%= Search.JSON_GetSeatData(sToday.evening_montreal) %>;
										$("#btnmontrealeve").data('seats', seatdata["montreal"]["eve"]);
									</script>
                                				<%
                                			}
                                		}
                                		
                                		if ( !display ) {
                            				%>
                                <tr>
                                    <td colspan="4">No results found.</td>
                                </tr>
                            				<%
                                		}
                                	%>
                                </tbody>
                            </table>
                            
                            <div id="seats">
                                <h2>Choose a free seat</h2>
                                
                                <ul id="thefreakingegg">
                                    <li id="lseat0" class="unbooked">
                                        <input type="radio" id="seat0" name="seat0" class="seat seat0 taken" checked="checked" disabled="disabled" value="0" />
                                        <label for="seat0">&nbsp;</label>
                                    </li>
                                    <li id="lseat1" class="unbooked">
                                        <input type="radio" id="seat1" name="seats" class="seat seat1" value="1" />
                                        <label for="seat1">&nbsp;</label>
                                    </li>
                                    <li id="lseat2" class="unbooked">
                                        <input type="radio" id="seat2" name="seats" class="seat seat2" value="2" />
                                        <label for="seat2">&nbsp;</label>
                                    </li>
                                </ul>

                                <div id="legend">
                                    <h2>Legend</h2>

                                    <ul>
                                        <li class="free">Available</li>
                                        <li class="booked">Unavailable</li>
                                        <li class="chosen">Your Selection</li>
                                    </ul>
                                </div>
		                    </div>
		                    
                            <form id="transactform" class="transact" method="get" action="save">
                                <fieldset>
                                    <div id="customer">
                                        <h2>Fill in your billing information</h2>

                                        <div class="part">
                                            <label for="fname">First Name</label>
                                            <input type="text" name="fname" id="fname" value="" placeholder="ex: John" class="required" />

                                            <label for="lname">Last Name</label>
                                            <input type="text" name="lname" id="lname" value="" placeholder="ex: Smith" class="required" />

                                            <label for="snum">Street Number</label>
                                            <input type="text" name="snum" id="snum" value="" class="snum required digits" placeholder="ex: 123" />

                                            <label for="saddr">Street Address</label>
                                            <input type="text" name="saddr" id="saddr" value="" placeholder="ex: Some St." class="required" />

                                            <label for="city">City</label>
                                            <input type="text" name="city" id="city" value="" placeholder="ex: My City" class="required" />

                                            <label for="prov">Province</label>
                                            <select name="prov" id="prov" class="required">
                                                <option value="0">Alberta</option>
                                                <option value="1">British Columbia</option>
                                                <option value="2">Manitoba</option>
                                                <option value="3">New Brunswick</option>
                                                <option value="4">Newfoundland and Labrador</option>
                                                <option value="5">Northwest Territories</option>
                                                <option value="6">Nova Scotia</option>
                                                <option value="7">Nunavut</option>
                                                <option value="8">Ontario</option>
                                                <option value="9">Prince Edward Island</option>
                                                <option value="10">Québec</option>
                                                <option value="11">Saskatchewan</option>
                                                <option value="12">Yukon</option>
                                            </select>

                                            <label for="postal">Postal Code</label>
                                            <input type="text" name="postal" id="postal" value="" placeholder="ex: A1A 1A1" class="required postal" />
                                            
                                            <label for="phone">Phone Number</label>
                                            <input type="text" name="phone" id="phone" value="" placeholder="ex: (111)222-3344" class="required phonenum" />
                                            
                                            <label for="CCnum">Credit Card Number</label>
                                            <input type="text" name="CCnum" id="CCnum" value="" placeholder="ex: 1111222233334444" class="required CCnum" minlength="16" />
                                            
                                            <label for="CCexp">Expiration Date</label>
                                            <input type="text" name="CCexp" id="CCexp" value="" class="snum required CCexp" placeholder="ex: MM/YY" />
                                        </div>
                                    </div>

                                    <div id="continue">
                                    	<input type="hidden" name="seatnum" id="seatnum" value="" />
                                    	<input type="hidden" name="from" value="<%= uFrom %>" />
                                    	<input type="hidden" name="evemorn" id="evemorn" value="-1" />
                                    	<input type="hidden" name="datev" value="<%= String.format("%s/%s/%s", (cc.get(Calendar.MONTH) + 1), cc.get(Calendar.DATE), cc.get(Calendar.YEAR)) %>" />
                                        <input type="submit" class="button s6" id="btnContinue" name="btnContinue" value="Continue" />
                                    </div>
                                </fieldset>
                            </form>
		                </div>
                    </div>
                </div>

		<script type="text/javascript" src="jquery/jquery-validation/jquery.validate.js"></script>
        <script type="text/javascript" src="js/index.shared.js"></script>
        <script type="text/javascript" src="js/search.js"></script>
        
<%@ include file="_footer.jsp" %>