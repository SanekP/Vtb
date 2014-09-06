$(function() {
	var convertor = $("#convertor");
	var button = $('input[type="button"]', convertor);

	function success(data) {
		if (data.result) {
			$('input[name="result"]', convertor).val(data.result);
		}
		button.fadeIn();
	}

	function convert() {
		button.fadeOut();
		data = $("form", convertor).serialize();
		$.post("convert", data, success).fail(function() {
			alert("Something went wrong :(");
			button.slideDown();
		});
	}

	button.click(convert);
});
