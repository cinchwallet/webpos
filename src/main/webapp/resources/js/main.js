jQuery.support.cors = true;

//DATE FUNCTION - START
/*
 * Date Format 1.2.3
 * (c) 2007-2009 Steven Levithan <stevenlevithan.com>
 * MIT license
 *
 * Includes enhancements by Scott Trenda <scott.trenda.net>
 * and Kris Kowal <cixar.com/~kris.kowal/>
 *
 * Accepts a date, a mask, or a date and a mask.
 * Returns a formatted version of the given date.
 * The date defaults to the current date/time.
 * The mask defaults to dateFormat.masks.default.
 */

var dateFormat = function () {
	var	token = /d{1,4}|m{1,4}|yy(?:yy)?|([HhMsTt])\1?|[LloSZ]|"[^"]*"|'[^']*'/g,
		timezone = /\b(?:[PMCEA][SDP]T|(?:Pacific|Mountain|Central|Eastern|Atlantic) (?:Standard|Daylight|Prevailing) Time|(?:GMT|UTC)(?:[-+]\d{4})?)\b/g,
		timezoneClip = /[^-+\dA-Z]/g,
		pad = function (val, len) {
			val = String(val);
			len = len || 2;
			while (val.length < len) val = "0" + val;
			return val;
		};

	// Regexes and supporting functions are cached through closure
	return function (date, mask, utc) {
		var dF = dateFormat;

		// You can't provide utc if you skip other args (use the "UTC:" mask prefix)
		if (arguments.length == 1 && Object.prototype.toString.call(date) == "[object String]" && !/\d/.test(date)) {
			mask = date;
			date = undefined;
		}

		// Passing date through Date applies Date.parse, if necessary
		date = date ? new Date(date) : new Date;
		if (isNaN(date)) throw SyntaxError("invalid date");

		mask = String(dF.masks[mask] || mask || dF.masks["default"]);

		// Allow setting the utc argument via the mask
		if (mask.slice(0, 4) == "UTC:") {
			mask = mask.slice(4);
			utc = true;
		}

		var	_ = utc ? "getUTC" : "get",
			d = date[_ + "Date"](),
			D = date[_ + "Day"](),
			m = date[_ + "Month"](),
			y = date[_ + "FullYear"](),
			H = date[_ + "Hours"](),
			M = date[_ + "Minutes"](),
			s = date[_ + "Seconds"](),
			L = date[_ + "Milliseconds"](),
			o = utc ? 0 : date.getTimezoneOffset(),
			flags = {
				d:    d,
				dd:   pad(d),
				ddd:  dF.i18n.dayNames[D],
				dddd: dF.i18n.dayNames[D + 7],
				m:    m + 1,
				mm:   pad(m + 1),
				mmm:  dF.i18n.monthNames[m],
				mmmm: dF.i18n.monthNames[m + 12],
				yy:   String(y).slice(2),
				yyyy: y,
				h:    H % 12 || 12,
				hh:   pad(H % 12 || 12),
				H:    H,
				HH:   pad(H),
				M:    M,
				MM:   pad(M),
				s:    s,
				ss:   pad(s),
				l:    pad(L, 3),
				L:    pad(L > 99 ? Math.round(L / 10) : L),
				t:    H < 12 ? "a"  : "p",
				tt:   H < 12 ? "am" : "pm",
				T:    H < 12 ? "A"  : "P",
				TT:   H < 12 ? "AM" : "PM",
				Z:    utc ? "UTC" : (String(date).match(timezone) || [""]).pop().replace(timezoneClip, ""),
				o:    (o > 0 ? "-" : "+") + pad(Math.floor(Math.abs(o) / 60) * 100 + Math.abs(o) % 60, 4),
				S:    ["th", "st", "nd", "rd"][d % 10 > 3 ? 0 : (d % 100 - d % 10 != 10) * d % 10]
			};

		return mask.replace(token, function ($0) {
			return $0 in flags ? flags[$0] : $0.slice(1, $0.length - 1);
		});
	};
}();

// Some common format strings
dateFormat.masks = {
	"default":      "ddd mmm dd yyyy HH:MM:ss",
	shortDate:      "m/d/yy",
	mediumDate:     "mmm d, yyyy",
	longDate:       "mmmm d, yyyy",
	fullDate:       "dddd, mmmm d, yyyy",
	shortTime:      "h:MM TT",
	mediumTime:     "h:MM:ss TT",
	longTime:       "h:MM:ss TT Z",
	isoDate:        "yyyy-mm-dd",
	isoTime:        "HH:MM:ss",
	isoDateTime:    "yyyy-mm-dd'T'HH:MM:ss",
	isoUtcDateTime: "UTC:yyyy-mm-dd'T'HH:MM:ss'Z'",
	txnDate:        "yymmddHHMMss"
};

// Internationalization strings
dateFormat.i18n = {
	dayNames: [
		"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat",
		"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
	],
	monthNames: [
		"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec",
		"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"
	]
};

// For convenience...
Date.prototype.format = function (mask, utc) {
	return dateFormat(this, mask, utc);
};

//DATE FUNCTION - END

function replacer(key, value) {
	  if ( value == '') {
	    return undefined;
	  }
	  return value;
	}


// The root URL for the RESTful services
var rootURL = "http://localhost:7001/cinchwallet/services/loyalty/api/v1.0";

/*

This can be used to display switch health status at webpos

	$.ajax({
		type: 'GET',
		url: rootURL + '/serverstatus' ,
		dataType: "json",
		success: function(data){
			alert(data.respMsg);
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert(' error: ' + textStatus);
		}
	});

*/

/*
 * REGISTRATION FUNCTION - START
 */

$('#btnSave').click(function() {
	$('#phoneDiv').hide();
	$('#cardDiv').hide();
	if ($('#phone').val() == '' && $('#cardNumber').val() == ''){
		//show error message
		$('#phoneDiv').show();
		return false;
	}
	if($('#phone').val() != '' && $('#phone').val().length<10){
		$('#phoneError').html('Incorrect phone number');
		$('#phoneDiv').show();
		return false;
	}
	if($('#cardNumber').val() != '' && $('#cardNumber').val().length<16){
		$('#cardError').html('Incorrect Card number');
		$('#cardDiv').show();
		return false;
	}
	registerUser();
	return false;

});

function registerUser() {

	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		url: rootURL + '/registeruser',
        crossDomain: true,  
		dataType: "json",
		data: formToRegJSON(),
		success: function(data, textStatus, jqXHR){
			$('#registration').hide();
			$('#reg-success').show();
			populateField(data.respMsg);	
			alert(data.respMsg);
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('addWine error: ' + textStatus);
		}
	});
	//formToRegJSON()
}
function populateField(membershipId) {
	$('#reg-success-name').html($('#fname').val() +" "+$('#lname').val());
	if ($('#cardNumber').val() != ''){
		$('#reg-success-card').html($('#cardNumber').val());
	}
	$('#reg-success-phone').html($('#phone').val());
	$('#reg-success-membershipid').html(membershipId);
}
function formToRegJSON() {
	
	//{"merchantID":"10000000001","terminalID":"20130603000","merchantTxnID":"201508101781","cardNumber":"4375674812346789",
	//"txnDate":"2015-08-17T18:18:21.305+08:00",
	//"profile":{"firstName":"Manoj","lastName":"Singh","gender":"M","dateOfBirth":"1982-06-29","phoneNumber":"9810403543",
	//"email":"m.manojsingh@gmail.com","address":"Noida","city":"Noida","state":"UP","zip":"201301","country":"India"}}

	var now = new Date();
	var userprofile = {
		"firstName": $('#fname').val(),
		"lastName": $('#lname').val(),
		"dateOfBirth": $('#dob').val(),
		"phoneNumber": $('#phone').val(),
		"email": $('#email').val(),
		"address": $('#address').val()
		};

	var data= {
		"merchantID": $('#mid').val(),
		"merchantTxnID": now.format("txnDate"),
		"cardNumber": $('#cardNumber').val()
		};
	data.profile = userprofile;
	
	var jsonString = JSON.stringify(data, replacer);
	//alert(jsonString);
	return jsonString;
}

/*
 * REGISTRATION FUNCTION - END
 */



/*
 * BALANCE INQUIRY FUNCTION - START
 */
$('#btnBI').click(function() {
	if ($('#cardnumBI').val() == ''){
		//show error message
		alert('provide card number or phone number');
		return false;
	}
	if($('#cardnumBI').val().length<10){
		alert('please provide 10 digit phone number OR 16 digit card number');
		return false;
	}
	var phone="";
	var cardNumber="";
	if($('#cardnumBI').val().length==10){
		phone = $('#cardnumBI').val()
	} else if($('#cardnumBI').val().length==16){
		cardNumber = $('#cardnumBI').val()
	}else{
		alert('please provide 10 digit phone number OR 16 digit card number');
		return false;
	}
	checkBalance(phone, cardNumber);
	return false;

});

function checkBalance(phone, cardNumber) {

	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		url: rootURL + '/carddetail',
        crossDomain: true,  
		dataType: "json",
		data: earnPointReq(phone, cardNumber),
		success: function(data, textStatus, jqXHR){
			alert(data.pointBalance);
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('addWine error: ' + textStatus);
		}
	});
}

function checkBalanceReq(phone, cardNumber) {
	
	//{"merchantID":"10000000001","terminalID":"20130603000","merchantTxnID":"201508101781","cardNumber":"4375674812346789",
	//"txnDate":"2015-08-17T18:18:21.305+08:00", "txnAmount":20}

	var now = new Date();
	//alert(phone +' '+cardNumber);
	var data= {
		"merchantID": $('#mid').val(),
		"merchantTxnID": now.format("txnDate"),
		"cardNumber": cardNumber,
		"phoneNumber": phone
		};
		
	var jsonString = JSON.stringify(data, replacer);
	//alert(jsonString);
	return jsonString;
}

/*
 * BALANCE INQUIRY FUNCTION - END
 */


/*
 * EARN POINT FUNCTION - START
 */

$('#btnEarnPoint').click(function() {
	if ($('#cardnum').val() == ''){
		//show error message
		alert('provide card number or phone number');
		return false;
	}
	if($('#cardnum').val().length<10){
		alert('please provide 10 digit phone number OR 16 digit card number');
		return false;
	}
	var phone="";
	var cardNumber="";
	if($('#cardnum').val().length==10){
		phone = $('#cardnum').val()
	} else if($('#cardnum').val().length==16){
		cardNumber = $('#cardnum').val()
	}else{
		alert('please provide 10 digit phone number OR 16 digit card number');
		return false;
	}
	earnPoint(phone, cardNumber);
	return false;

});


function earnPoint(phone, cardNumber) {

	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		url: rootURL + '/earnpoint',
        crossDomain: true,  
		dataType: "json",
		data: earnPointReq(phone, cardNumber),
		success: function(data, textStatus, jqXHR){
			alert(data.respMsg);
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('addWine error: ' + textStatus);
		}
	});
	//formToRegJSON()
}

function earnPointReq(phone, cardNumber) {
	
	//{"merchantID":"10000000001","terminalID":"20130603000","merchantTxnID":"201508101781","cardNumber":"4375674812346789",
	//"txnDate":"2015-08-17T18:18:21.305+08:00", "txnAmount":20}

	var now = new Date();
	//alert(phone +' '+cardNumber);
	var data= {
		"merchantID": $('#mid').val(),
		"merchantTxnID": now.format("txnDate"),
		"cardNumber": cardNumber,
		"phoneNumber": phone,
		"txnAmount": $('#ammount').val()
		
		};
		
	var jsonString = JSON.stringify(data, replacer);
	//alert(jsonString);
	return jsonString;
}
/*
 * EARN POINT FUNCTION - END
 */


/*
 * RESET PASSWORD FUNCTION - START
 */

$('#btnResetSubmit').click(function() {
	$('#btnResetSubmit').html("Processing....")
	$(this).attr("disabled","disabled");
	return true;
});
/*
 * RESET PASSWORD FUNCTION - END
 */

