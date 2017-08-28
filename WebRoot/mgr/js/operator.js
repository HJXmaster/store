$(document).ready(function() {
	var ops =title_operator;
	var operator = ops.eq(0);
	
	var class_name = 'fouse_bt';

	ops.each(function(index) {
		this.setAttribute("data-id",ids[index]);
		$(this).click(function() {
			if (operator[0] == this)
				return;
			operator.removeClass(class_name);
			$('#' + operator[0].getAttribute('data-id')).hide();
			operator = $(this);
			operator.addClass(class_name);
			$('#' + operator[0].getAttribute('data-id')).show();
		});

	});

});