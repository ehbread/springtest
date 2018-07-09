var viewTemplate = function (data) {
	var template = $('#bookmark-list-template').html();
	Mustache.parse(template);   // optional, speeds up future uses
	return Mustache.render(template, data);

}

var objPagination = function () {

	return $('#pagination-here').bootpag({
		total: 1,          // total pages
		page: condition.page,            // default page
		maxVisible: condition.size,     // visible pagination
		leaps: true         // next/prev leaps through maxVisible
	})
}

var resetPagination = function (data) {
	var totalPage = 1;
	var pageNumber = (data.number + 1)
	if (data.totalPages > 0) {
		totalPage = data.totalPages
	}
	objPagination().bootpag({ total: totalPage, page: pageNumber, maxVisible: data.size });
	$('#pagination-info').html("Page " + pageNumber + " of " + totalPage + " / Count " + data.totalElements)

}

var condition = {
	page: 1,
	size: 10,
}

var getList = function () {

	var param = condition;
	param.sort = $("#sort").val();

	$.ajax({
		url: "/my/bookmark/list?_csrf=" + $("#_csrf").val(),
		type: "json",
		contentType: "application/json",
		data: JSON.stringify(param)
	}).success(function (data) {
		$("#bookmark-list").html(viewTemplate(data));
		resetPagination(data);
	})
}

var removeBookmark = function (bookmarkId) {

	$.ajax({
		url: "/my/bookmark/remove?_csrf=" + $("#_csrf").val(),
		data: { bookmarkId: bookmarkId }
	}).success(function (data) {
		getList();
	})
}


$(function () {

	objPagination().on("page", function (event, num) {
		condition.page = num;
		getList();
	});

	$("#bookmark-list").on("click", ".btn-bookmark-remove", function () {
		removeBookmark($(this).data("bookmarkid"))
	});


	$("#sort").change(function () {
		getList();
	});


	getList();

});