// init date picker
oDate.datepicker({ 
	minDate: 1, 
	maxDate: 14,
	// showButtonPanel: true,
	// changeMonth: true,
	// changeYear: true,
	numberOfMonths: 2,

	onSelect : function(dateText, inst) {
		oDatev.val(dateText);
    }
});

// set initial date
var curDate = oDate.datepicker("getDate");
var defDate = (curDate.getMonth() + 1) + "/" + curDate.getDate() + "/" + curDate.getFullYear();
oDatev.val(defDate);