<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%
	boolean _isSearch = false;
	try {
		_isSearch = (Boolean)request.getAttribute("_header_isSearch");
	} catch (Exception e) {}
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Airline Booker EXTREEEEMEEE!</title>

        <link rel="stylesheet" type="text/css" media="screen" href="css/index.css" />
		<link rel="stylesheet" type="text/css" media="print" href="css/print.css" />
        <link rel="stylesheet" type="text/css" href="jquery/css/custom-theme/jquery-ui-1.8.18.custom.css" />
	</head>
	<body>
        <div class="container"> 
            <div class="content<% if ( _isSearch ) { %> search<% } %>">
                <h1 id="title"><a href="index.html">Airline Booker <span>EXTREEEEMEEE!</span></a></h1>