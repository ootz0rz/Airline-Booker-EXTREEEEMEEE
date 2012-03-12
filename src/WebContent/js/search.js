// init date picker
var oSeats = $("#seats");
var oTransact = $("#transactform");
var oEveMorn = $("#evemorn");
oSeats.hide();
oTransact.hide();
$(".content").addClass("search");

oDatev.datepicker({ 
	minDate: 1, 
	maxDate: 14,
	showButtonPanel: true,
	changeMonth: true,
	changeYear: true,
	numberOfMonths: 1
});

// hide search by default
var oSrch = $("#Search");
oSrch.hide();

var curVals = {
	'from' : $('input[name=from]:checked').val(),
	'on' : $.datepicker.parseDate('mm/dd/yy', oDatev.val())
};

// then show it if the form changes
var _onChange = function(e) {
	var nDate = $.datepicker.parseDate('mm/dd/yy', oDatev.val());

	if ( 
		$('input[name=from]:checked').val() != curVals['from'] 
		|| nDate.getTime() != curVals['on'].getTime()
	) {
		oSrch.fadeIn();
	}
	else {
		oSrch.fadeOut();
	}
};

$('input[name=from]').change(_onChange);
oDatev.change(_onChange);
oDatev.keypress(_onChange);
oDatev.datepicker({ onSelect: _onChange });

// even/morn rows
var oEve = $("td a.eve");
var oMorn = $("td a.morn");

/**
 * Hide seats, show other buy rows
 */
var _onClickCancel = function(e) {
	e.preventDefault();

	var $this = $(this);

	var other = null;
	if ( $this.hasClass('eve') ) {
		other = oMorn;
	} else {
		other = oEve;
	}
	
	if ( other != null ) {
		oSeats.fadeOut();
		oTransact.fadeOut();
		other.parent().parent().fadeIn();
	} else {
		other.parent().parent().fadeIn(function() { oSeats.fadeOut(); oTransact.fadeOut(); });
	}

	// change to "buy" button
	$this.html($this.data('oldhtml'));
	$this.unbind('click.cancel');

	$this.removeClass("s4").removeClass("sbold");
	$this.addClass("s5");
	$this.bind('click.buybtn', _onClickBuyBtn);
};

/**
 * Given the seats data arr, set the seats diagram
 */
var _setSeatsStuff = function(arr) {
	// reset to default...
	var s0 = $("#seat0");
	var s1 = $("#seat1");
	var s2 = $("#seat2");

	s0.attr('name', 'seats').attr('checked', false).removeClass('taken').removeAttr('disabled');
	s1.attr('name', 'seats').attr('checked', false).removeClass('taken').removeAttr('disabled');
	s2.attr('name', 'seats').attr('checked', false).removeClass('taken').removeAttr('disabled');

	// look at array and set appropriately
	var _setUnavail = function(sn) {
		sn
			.attr('checked', true)
			.attr('disabled', true)
			.addClass('taken')
			.attr('name', sn.attr('id'));
	}

	if ( arr[0] == true ) _setUnavail(s0);
	if ( arr[1] == true ) _setUnavail(s1);
	if ( arr[2] == true ) _setUnavail(s2);
};

/**
 * Show seats, hide other buy rows
 */
var _onClickBuyBtn = function(e) 
{
	e.preventDefault();

	// figure out which one we've clicked on
	var $this = $(this);

	// don't need to bother with what city since always limited to one of them
	var other = null;
	if ( $this.hasClass('eve') ) {
		other = oMorn;
	} else {
		other = oEve;
	}

	if ( other != null ) {
		other.parent().parent().fadeOut();
		oSeats.fadeIn();
		oTransact.fadeIn();
	} else {
		other.parent().parent().fadeOut(function() { oSeats.fadeIn(); oTransact.fadeIn(); });
	}

	// change to "cancel" button
	$this.data('oldhtml', $this.html());
	$this.unbind('click.buybtn');
	$this.html("Cancel");

	$this.removeClass("s5");
	$this.addClass("s4").addClass("sbold");
	$this.bind('click.cancel', _onClickCancel);

	_setSeatsStuff($this.data('seats'));

	if ( $this.hasClass('morn') ) {
		// set as morning
		oEveMorn.val(0);
	}
	else if ( $this.hasClass('eve') ) {
		// set as evening
		oEveMorn.val(1);
	}
};

$(".buybtn").bind('click.buybtn', _onClickBuyBtn);

// on click seat
var oSeatnum = $("#seatnum");
$("input.seat").bind('click', function(e) 
{
	var $this = $(this);

	oSeatnum.val($this.val());
});

// validate user info on submit
jQuery.validator.addMethod("phonenum", function(value, element) { 
  return /^\D?(\d{3})\D?\D?(\d{3})\D?(\d{4})/.test(value);
}, "Please specify a valid 10 digit phone number with area code, ex: (111) 222-3344.");

jQuery.validator.addMethod("postal", function(value, element) { 
  return /^([A-Za-z]\d[A-Za-z][-\s]?\d[A-Za-z]\d)/.test(value);
}, "Please specify a valid 6 character postal code, ex: A1A 1A1.");

jQuery.validator.addMethod("CCexp", function(value, element) { 
  return /^\d{1,2}\/\d{2}/.test(value);
}, "Please enter a valid Expiration Date in the format MM/YY.");

jQuery.validator.addMethod("CCnum", function(value, element) { 
  return /^\D?\d{4}\D?\d{4}\D?\d{4}\D?\d{4}/.test(value);
}, "Please enter a valid Credit Card number, ex: 1111-2222-3333-4444");

oTransact.validate();