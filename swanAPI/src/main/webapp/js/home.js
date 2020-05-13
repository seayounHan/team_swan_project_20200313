/**
 * 
 */
$(document).ready(function() {
	var cookie = $('.user_name').html();
	var button = document.getElementById('userButton');
	if (cookie == '') {
		button.value = 'login';
	} else {
		button.value = 'mypage';
	}

});

$(function() {
	$('#userButton').click(function() {
		var buttonValue = $('#userButton').val();
		if (buttonValue == 'login') {
			document.location.href = "/login";
		} else if (buttonValue == 'mypage') {
			document.location.href = "/mypage";
		}
	});
});

$(function() {
	$('#addButton').click(function() {
		document.location.href = "/home/store/register";
	});
});

$(function() {
	$('.search_button').click(function() {
		
		var word = $('#search').val();
		var url = "/home/search?word="+word;
		$(location).attr('href',url);
	});
});

$(function() {
	$('.closure_button').click(function() {
		var storeId = $(this).parent().parent().find('.storeId').val();
		var storeName = $(this).parent().parent().find('.storeName').val();
		var cookie = $('.user_name').html();
		if(cookie == '') {
			document.location.href="/login";
			return ;
		}
		if($(this).val() == "Y") {
			$(this).parents('.list_item').find('.store_name').html('[폐업] <span>'+storeName+'</span>');
			$(this).html('개업신고');
			$(this).val("N");
			$.ajax({
				type : 'POST',
				data : 'storeId='+storeId+'&open=N', 
				dataType : 'text',
				url : '/home/store/closure',
				success : function(data) {
					alert("폐업 처리 되었습니다.");
				},
				error : function(e) {
					alert("error: " + e.value);
				}
			})
		} else {
			$(this).parent().parent().parent().find('.store_name').html('<span>'+storeName+'</span>');
			$(this).html('폐업신고');
			$(this).val("Y");
			$.ajax({
				type : 'POST',
				data : 'storeId='+storeId+'&open=Y', 
				dataType : 'text',
				url : '/home/store/closure',
				success : function(data) {
					alert("개업 처리 되었습니다.");
				},
				error : function(e) {
					alert("error: " + e.value);
				}
			})
		}
		
	});
});
