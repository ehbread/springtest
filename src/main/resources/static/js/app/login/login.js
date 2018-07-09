var repository = function (url, param) {

	return $.ajax({
		url: url,
		dataType: "json",
		data: param
	});

}

var $createForm = $("#create-form");


$(function () {

	$("body").on("click", ".action-btn", function () {
		repository("/member/lo", $createForm.serialize())
			.success(function (data) {
					location.href = "/login"
				}
			);

	});

});