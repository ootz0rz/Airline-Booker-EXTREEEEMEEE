/* tools and other randomness */

/**
 * Define a function func as the given name for a class.
 **/
Function.prototype.method = function (name, func) {
    this.prototype[name] = func;
    return this;
};

/**
 * True iff the browser is chrome
 */
var isChrome = function() {
    return navigator.userAgent.toLowerCase().indexOf('chrome') > -1;
}

/**
 * Set hours/minutes/seconds/milliseconds to 0 for the given DateTime object d
 */
var removeDateTime = function(d) {
	d.setHours(0);
	d.setMinutes(0);
	d.setSeconds(0);
	d.setMilliseconds(0);
}