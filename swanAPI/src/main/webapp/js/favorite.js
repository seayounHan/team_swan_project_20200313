/**
 * 
 */

$(function() {
	$('.favorite').click(function() {
		var store_id = $(this).parent().find('.storeId').val();
		var nickname = $('.user_name').html();
		
		if(nickname == '') {
			document.location.href = "/login";
		} else {
			if($(this).find('i').attr('class') == 'fa fa-heart-o') {
				$(this).find('i').replaceWith('<i class="fa fa-heart" aria-hidden="true"></i>');
			} else {
				$(this).find('i').replaceWith('<i class="fa fa-heart-o" aria-hidden="true"></i>');
			}
			
			$.ajax({
				type : 'POST',
				data : "nickname="+ nickname + "&store_id=" + store_id,
				dataType : 'text',
				url : '/favorite',
				error : function(e) {
					alert("error: " + e);
				}
			})
		}

	});
});

