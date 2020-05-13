/**
 * 
 */
$(function() {
	$('.submit').click(
			function() {
				var nickname = $('#nickname').val();
				var password = $('#password').val();
				var sectionId = $('#sectionId').val();
				if (nickname == '' || password == '') {
					alert("아이디와 비밀번호를 입력해주세요.");
				} else {
					$.ajax({
						type : 'POST',
						data : "sectionId=" + sectionId + "&nickname="
								+ nickname + "&password=" + password,
						dataType : 'text',
						url : '/login/done',
						success : function(data) {
							if (data == "true") {
								document.location.href = "/home";
							} else {
								alert("로그인에 실패하였습니다.");
							}
						},
						error : function(xhr, status, e) {
							alert("error: " + e);
						}
					})
				}
			});

});