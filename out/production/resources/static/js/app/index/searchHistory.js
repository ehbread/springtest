$(function () {

	var getSearchHisotry = function (param) {

		$.ajax({
			url: "/my/searchhistory/list?_csrf=" + $("#_csrf").val(),
			type: "json",
			contentType: "application/json",
			data: JSON.stringify({
				page: 1,
				size: 10
			})
		}).success(function (data) {
			$("#search-history").html(viewTemplate(data.content));
		});

	}

	var viewTemplate = function (data) {

		var template = $('#search-history-template').html();
		Mustache.parse(template);   // optional, speeds up future uses
		return Mustache.render(template, data);

	}

	var $searchForm = $("#searchForm");

	getSearchHisotry($searchForm.serializeObject());

	$("#search-history").on("click", "span.label", function () {
		$searchForm.find("input[name=query]").val($(this).text())
	});

});