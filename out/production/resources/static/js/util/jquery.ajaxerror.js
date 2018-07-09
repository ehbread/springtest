$(document).ajaxComplete(
	function (event, jqxhr, settings) {
		if (jqxhr.status == 200) {

		} else {
			alert(JSON.parse(jqxhr.responseText).message)
		}
	});
