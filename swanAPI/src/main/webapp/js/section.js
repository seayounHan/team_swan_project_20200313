/**
 * 
 */

$(function() {
	$('.modify_button').click(function() {
		var sectionNameInput = $(this).parents('.section_info').find('#section_name_input');
		var sectionId = $(this).parents('.section_info').find('.sectionId').val();
		if($(this).html()=='수정') {
			sectionNameInput.attr('readOnly',false);
			sectionNameInput.focus();
			sectionNameInput.css("border","2px inset");
			$(this).html('저장');
		} else {
			sectionNameInput.attr('readOnly',true);
			sectionNameInput.css("border","none");
			var sectionName = sectionNameInput.val();
			var nickname = $(this).parents('.section_info').find('.nickname').val();
			$.ajax({
				type : 'POST',
				data : 'id='+sectionId+'&sectionName='+sectionName+'&nickname='+nickname, 
				dataType : 'text',
				url : '/section/update',
				success : $.proxy(function(data) {
					if(data == 1) {
						alert("수정되었습니다.");
						$(this).html('수정');
					} else {
						alert("수정에 실패하였습니다. 다시 시도해 주세요.");
					}
				},this),
				error : function(e) {
					alert("error: " + e);
				}
			})
			
		}

	});
});

$(function() {
	$('.delete_button').click(function(e) {
		var sectionId = $(this).parents('.section_info').find('.sectionId').val();

		 e.preventDefault();
		$.ajax({
			type : 'POST',
			data : 'sectionId='+sectionId, 
			dataType : 'text',
			url : '/section/delete',
			success : $.proxy(function(data) {
				
				if(data == 1) {
					$(this).parents('.section_info').remove();
					alert("삭제되었습니다.");
					
				} else {
					alert("삭제에 실패하였습니다. 다시 시도해 주세요.");
				}
			}, this),
			error : function(e) {
				alert("error: " + e);
			}
		})
	});
	
});