/**
 * 
 */
var store_id = '';

$(document).ready(function() {
	store_id = $('.storeId').val();
	var cookie = $('.user_name').html();
	var button = document.getElementById('userButton');
	if (cookie == '') {
		button.value = 'login';
	} else {
		button.value = 'mypage';
	}
});

$(function() {
	$('#title_button').click(function() {

		var type = $('#title_button').val();
		var data = $('.store_name').find('.name span').html();
		
		var params = {
			"type" : type,
			"id" : store_id,
			"data" : data
		}
		postUpdateForm(params)
	});
});

$(function() {
	$('.modify_menu_button').click(function() {
		var type = $('.modify_menu_button').val();
		
		var menu_tag = $('.menu_tag span').html();
		var params = {
			"type" : type,
			"id" : store_id,
			"data" : menu_tag
		}
		postUpdateForm(params)

	});
});

$(function() {
	$('#keyword_tag_button').click(function() {
		var type = $('#keyword_tag_button').val();
		
		var data = $('.keyword_tag span').html();
		var params = {
			"type" : type,
			"id" : store_id,
			"data" : data
		}
		postUpdateForm(params)
	});
});

$(function() {
	$('#service_tag_button').click(function() {
		var type = $('#service_tag_button').val();
		
		var data = $('.service_tag span').html();
		var params = {
			"type" : type,
			"id" : store_id,
			"data" : data
		}
		postUpdateForm(params)
	});
});

$(function() {
	$('#address_button').click(function() {
		var type = $('#adress_button').val();
		
		var data = $('.address .address2').html();
		var subData = $('.address .address1').html();
		var params = {
			"type" : type,
			"id" : store_id,
			"data" : data,
			"subData" : subData
		}
		postUpdateForm(params)
	});
});

$(function() {
	$('#tel_main_button').click(function() {
		var type = $('#tel_main_button').val();
		var data = $('.tel_main span').html();
		var params = {
			"type" : type,
			"id" : store_id,
			"data" : data
		}
		postUpdateForm(params)
	});
});

$(function() {
	$('#tel_sub_button').click(function() {
		var type = $('#tel_sub_button').val();
		var data =  $('.tel_sub span').html();
		var params = {
			"type" : type,
			"id" : store_id,
			"data" : data
		}
		postUpdateForm(params)
	});
});

function postUpdateForm(params) {
	var form = document.createElement("form");
	form.setAttribute("method", "post");
	form.setAttribute("action", "/home/store/update");

	for ( var key in params) {
		var input = document.createElement("input");
		input.setAttribute("type", "hidden");
		input.setAttribute("name", key);
		input.setAttribute("value", params[key]);
		form.appendChild(input);
	}

	form.submit();
}


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
	$('.search_button').click(function() {

		var word = $('#search').val();
		var url = "/home/search?word=" + word;
		$(location).attr('href', url);
	});
});

$(function() {
	$('.closure_button').click(function() {
		//var storeId = $(this).parents('.wrap').find('.storeId').val();
		//var storeName = $(this).parents('.store_title_top').find('.name span').html();
		var storeName = $('.store_name').find('.name').html();
		alert(storeName);
		var cookie = $('.user_name').html();
		if(cookie == '') {
			document.location.href='/login';
			return ;
		}
		if($(this).val() == "Y") {
			$(this).parents('.store_title_top').find('.name').html('[폐업] <span>'+storeName+'</span>');
			$(this).html('개업신고');
			$(this).val("N");
			$.ajax({
				type : 'POST',
				data : 'storeId='+store_id+'&open=N', 
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
			$(this).parents('.store_title_top').find('.name').html('<span>'+storeName+'</span>');
			$(this).html('폐업신고');
			$(this).val("Y");
			$.ajax({
				type : 'POST',
				data : 'storeId='+store_id+'&open=Y', 
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


$(function() {
	$('.mark_button').click(function() {
		var nickname = $('.user_name').html();
		var point;
		if($(this).val() == "up") {
			point =1;
		} else {
			point =-1;
		}
		var sum = parseInt($('.mark_point').html()) +point;
		$('.mark_point').html(sum);
		$.ajax({
			type : 'POST',
			data : 'storeId='+store_id+'&nickname='+nickname+'&point='+point, 
			dataType : 'text',
			url : '/home/store/mark',
			error : function(e) {
				alert("error: " + e);
			}
		})
	});
})


