function showTost(txt) {
	let tostMessage = $("#tost_message");
	
	tostMessage.text(txt);
	tostMessage.addClass("active");
	
	setTimeout(function() {
		tostMessage.removeClass("active");
	}, 1000);
	
	throw new Error("stopExecution");
}
