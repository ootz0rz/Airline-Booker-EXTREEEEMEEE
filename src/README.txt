Partners
=====================
I did not have a partner for this assignment. All code was written by myself:
	Hardeep Singh, student # ---------

Overall
=====================
	The website is created using HTML5, CSS2/CSS3 and javascript with the
libraries jQuery, jQUery ui, jQuery.tablesorter and jQuery-validation.

	For any JSP pages, the pages are split into several parts. A global
_header.jsp for the top portion of the HTML, _footer_baseJS.jsp for the standard
javascript files to include with each page, and _footer.jsp to close off the
HTML document. Parts between _header and _footer make up the body of each page.

	There are two main CSS documents, css/index.css which controls the look for
all screen content and css/print.css which is used for when the user is printing
their transaction details.

	Finally, the initial hashmap that stores all the data is set up in the file
"Initialization.java".

JSP/HTML Pages
=====================
index.html: (Path: /)
--------------------------------------------------------------------------------
	This is the entry point for the website. It simply presents the user with a
search form which will then send data to the /search servlet.


Search.java: (Path: /search)
--------------------------------------------------------------------------------
	This is the /search servlet. It will first verify all data from the user. If
the data is incorrect, it displays the "searcherror.jsp" page with the error
messages. If the data is correct, it displays "search.jsp".

searcherror.jsp: (Path: /search, /save)
--------------------------------------------------------------------------------
	Simply lists/displays errors to the user after a form submit by looping
through the errorsList.

search.jsp: (Path: /search)
--------------------------------------------------------------------------------
	After successful search, this page is shown with any relevant results. The
user can then click on one of the "buy" buttons to show the seat selection
diagram. The seat selection diagram will only allow seats to be chosen that have
not already been booked. Below the diagram, they can fill in their billing
information and hit continue to purchase the ticket. Client-side validation is
done using jQuery-validation.

Save.java: (Path: /save)
--------------------------------------------------------------------------------
	Verifies all data, then saves to the servlet context Hash Map. Once again,
if errors are detected in the data, they are shown in the searcherror.jsp page.
Otherwise, the user is shown savesuccess.jsp.

savesuccess.jsp: (Path: /save)
--------------------------------------------------------------------------------
	Shown on a successful ticket purchase, lists all relevant data for the
purchase. The user may then print the page in a printer-friendly format thanks
to print.css. 

history.java, history.jsp: (Path: /history)
--------------------------------------------------------------------------------
	Display a list of all transactions that have occured in the website. The
table headers can be clicked on to sort the transaction list. If one header is
already clicked on to sort with, the user can hold shift to add a secondary sort
on another column. The library jquery.tablesorter is used for this 
functionality.

JavaScript Files
=====================

functions.js
--------------------------------------------------------------------------------
	Some helpful functions I use through the site.
	
modal.js
--------------------------------------------------------------------------------
	A modal dialog library I created in A2, updated to use jQuery for A3.
	
index.shared.js
--------------------------------------------------------------------------------
	Functionality that is shared between multiple pages. Namely, the index.html
page and the /search page. This mostly controls validation for when the search
form is submitted in either page.

index.js
--------------------------------------------------------------------------------
	Settings specifid to index.html, mostly to do with the date picker widget.
	
search.js
--------------------------------------------------------------------------------
	Settings specifid to /search, partially to do with the date picker widget.
The rest controls hiding/showing the "Search!" button when it detects changes, 
and showing/hiding elements when a user clicks the BUY button for a particular
ticket. It also handles setting the correct hidden data for the form submit once
they've filled in their information. And finally, it sets up client-side 
validation for the user details form.