<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ include file="_header.jsp" %>

                <div id="flights">
                    <div class="top">
                        <h2>Error! :(</h2>
                    </div>
                    <div class="bottom">
						<div class="part">
							One or more errors have occured, please go back in your browser to fix and try again.
						</div>
						
						<div class="part">
							<h3>Error List</h3>
							<ul class="errorlist">
							<%
								List<String> errs = new ArrayList<String>();
								errs = (ArrayList<String>)request.getAttribute("errorList");
									
								for ( String err : errs ) {
									%>
								<li><%= err %></li>
									<%
								}
							%>
							</ul>
						</div>
						
						<div class="part">
							<a class="button s6" href="index.html">Back to Search</a>
						</div>
                    </div>
                </div>

<%@ include file="_footer_baseJS.jsp" %>

        <script type="text/javascript" src="js/functions.js"></script>
        <script type="text/javascript" src="js/search.js"></script>
        
<%@ include file="_footer.jsp" %>