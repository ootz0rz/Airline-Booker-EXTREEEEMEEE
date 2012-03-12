var oDatev = $("#datev");
var oDate = $("#date");

// set up validation
var oSrchForm = $("#searchform");

var onSubmit = function(e) 
{
	var valid = true;
	var errors = [];

	// check validation
	var valFrom = $("input[name=from]:checked", oSrchForm).val();
	var valDate = $.datepicker.parseDate('mm/dd/yy', oDatev.val());

	if ( !(valFrom == 1 || valFrom == 2 ) ) {
		valid = false;
		errors.push("Please choose a valid destination.");
	}

	var sDate = new Date();
	var eDate = new Date();
	sDate.setDate(sDate.getDate() + 1);
	eDate.setDate(eDate.getDate() + 14);

	removeDateTime(sDate);
	removeDateTime(eDate);

	if (
		// date range = [today+1 : today+14]
		valDate < sDate ||
		valDate > eDate
	) {
		valid = false;
		errors.push("Please choose a valid date.");
	}

	// if we're good, then submit
	if ( !valid ) {
	    var m = new Modal(
	        'Errors :(', 
	        'There were errors with your search submission. Please fix them and try again:'
	        + '<ul class="errorlist">'
	        + '</ul>'
	    );
	    m.addbutton('Close');

	    var oUL = $("ul.errorlist", m.div);
	    console.log('oUL', oUL, 'm.div', m.div);

	    for (var index in errors) {
	    	oUL.append(
	    		$("<li />").html(errors[index])
	    	);
	    }

	    m.show();

	    e.preventDefault();
	} 
	else {
		console.log("Yay, no errors!");
		return true;
	}

	return false;
};

oSrchForm.submit(onSubmit);
$("#Search").click(onSubmit);