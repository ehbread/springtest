$(function () {

	var $searchForm = $("#searchForm");

	var viewTemplate = function (data) {
		var template = $('#search-list-template').html();
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
		target: "all"
	}

	var getSearchList = function () {

		var param = $searchForm.serializeObject();
		param = $.extend(param, condition);

		if(param.query.length <=0){
			alert("검색어를 입력해주세요")
			return false;
		}

		$.ajax({
			url: "/booksearch/list?_csrf=" + $("#_csrf").val(),
			type: "json",
			contentType: "application/json",
			data: JSON.stringify(param)
		}).success(function (data) {

			$(data.content).each(function(){
				this.json = JSON.stringify(this);
			})
			$("#search-list").html(viewTemplate(data));
			resetPagination(data);
		})
	}

	var addBookmark = function (data) {


		$.ajax({
			url: "/my/bookmark/add?_csrf=" + $("#_csrf").val(),
			type: "json",
			contentType: "application/json",
			data: JSON.stringify(data)
		}).success(function (data) {
			alert("저장완료")
		})
	}


	objPagination().on("page", function (event, num) {
		condition.page = num;
		getSearchList();
	});


	$("#searchForm").on("keyup", "input", function (e) {
		if (e.which === 13) {
			$("#search-btn").click();
		}
	});

	$("body").on("click", "#search-btn", function () {

		condition.page = 1;
		getSearchList()


	});

	$("#search-list").on("click", ".btn-title", function () {

		$("#detail-modal").find(".modal-body").html($(this).find(".book-detail").html())
		$('#detail-modal').modal('show')
	}).on("click", ".btn-bookmark", function () {
		addBookmark($(this).data("json"));
	});



});