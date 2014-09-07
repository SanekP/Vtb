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
		var data = {};
		$("form").serializeArray().forEach(function(i) {
			data[i.name] = i.value;
		});
		$.post("convert", JSON.stringify(data), success).fail(function() {
			alert("Something went wrong :(");
			button.slideDown();
		});
	}

	button.click(convert);
});
