/**
 * 
 */
$(function() {
	$('.modify_review').click(function() {
		if($(this).val() == "modify") {
			var contents = $(this).parents('.review_item').find('p').html();
			
			$(this).parents('.review_item').find('.review_contents p').replaceWith("<textarea>"+contents);
			
			$(this).val("submit");
			$(this).html("저장");
		} else {
			var id =  $(this).parents('.review_item').find('.review_id').val();
			var contents = $(this).parents('.review_item').find('textarea').val();
			
			$(this).parents('.review_item').find('.review_contents textarea').replaceWith("<p>"+contents+"</p>");
			
			$(this).val("modify");
			$(this).html("수정");
			
			$.ajax({
				type : 'POST',
				data : "id="+id+"&contents="+contents,
				dataType : 'text',
				url : '/home/store/review/update',
				success : function(data) {
					alert("수정되었습니다.");
				},
				error : function(e) {
					alert("error: " + e);
				}
			})
		}

	});
});

$(function() {
	$('.delete_review').click(function() {
		var id =  $(this).parents('.review_item').find('.review_id').val();
		$(this).parents('.review_item').remove();
		$.ajax({
			type : 'POST',
			data : "id="+id,
			dataType : 'text',
			url : '/home/store/review/delete',
			success : function(data) {
				
			},
			error : function(e) {
				alert("error: " + e);
			}
		})
	});
});

function postUpdateForm(params) {
	var form = document.createElement("form");
	form.setAttribute("method", "post");
	form.setAttribute("action", "/home/store/review/update");

	for ( var key in params) {
		var input = document.createElement("input");
		input.setAttribute("type", "hidden");
		input.setAttribute("name", key);
		input.setAttribute("value", params[key]);
		form.appendChild(input);
	}

	form.submit();
}

